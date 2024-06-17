package com.ktdsuniversity.edu.pms.memo.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.service.EmployeeService;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.memo.service.ReceiveMemoService;
import com.ktdsuniversity.edu.pms.memo.service.SendMemoService;
import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.ReceiveMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SearchMemoVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoListVO;
import com.ktdsuniversity.edu.pms.memo.vo.SendMemoVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;

@RestController
@RequestMapping("/api/memo")
public class ApiMemoController {

    @Autowired
    private SendMemoService sendMemoService;
    @Autowired
    private ReceiveMemoService receiveMemoService;
    @Autowired
    private EmployeeService employeeService;
    
    
    /**
     * 부서 아이디로 부서원 조회
     */
    @GetMapping("/member/{deptId}")
    public ApiResponse getEmployeeByDeptId(@PathVariable String deptId, Authentication authentication) {
    	List<EmployeeVO> employeeListVO = this.employeeService.getEmployeeByDeptId(deptId);
    	return ApiResponse.Ok(employeeListVO);
    }
    

    /**
     * 파일 다운로드
     */
    @GetMapping("/receive/downloadFile/{rcvMemoId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String rcvMemoId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();																				  
        ReceiveMemoVO receiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        return this.receiveMemoService.getDownloadFile(receiveMemoVO);
    }
    
    @GetMapping("/send/downloadFile/{sendMemoId}")
    public ResponseEntity<Resource> sendDownloadFile(@PathVariable String sendMemoId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();																				  
        SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMemo(sendMemoId);
        return this.sendMemoService.getDownloadFile(sendMemoVO);
    }

    // ---------- 발신 ----------
    /**
     * 로그인한 사원의 발신 쪽지 전체 조회
     * @Todo Authentication으로 사원아이디 넘겨주기(searchMemoVO에 empId 추가)
     */
    @GetMapping("/send")
    public ApiResponse searchSendMemoList(SearchMemoVO searchMemoVO, Authentication authentication) {
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
        searchMemoVO.setEmpId(employeeVO.getEmpId());
        
        SendMemoListVO sendMemoListVO = this.sendMemoService.searchAllSendMemo(searchMemoVO);
        return ApiResponse.Ok(sendMemoListVO.getSendMemoList(), sendMemoListVO.getSendMenoCnt(),
                                searchMemoVO.getPageCount(), searchMemoVO.getPageNo() < searchMemoVO.getPageCount() - 1);
    }
    
    /**
     * 보관처리한 발신 쪽지 전체 조회
     */
    @GetMapping("/send/save")
    public ApiResponse searchSaveSendMemoList(SearchMemoVO searchMemoVO, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        searchMemoVO.setEmpId(employeeVO.getEmpId());
        searchMemoVO.setSearchKeyword("Y");
        searchMemoVO.setSearchType("send_save");
        SendMemoListVO saveSendMemoListVO = this.sendMemoService.searchAllSendMemo(searchMemoVO);

        return ApiResponse.Ok(saveSendMemoListVO.getSendMemoList(), saveSendMemoListVO.getSendMenoCnt(),
                searchMemoVO.getPageCount(), searchMemoVO.getPageNo() < searchMemoVO.getPageCount() - 1);
    }
    
    /**
     * 한 개의 발신 쪽지 조회
     */
    @GetMapping("/send/{sendMemoId}")
    public ApiResponse viewSendMemo(@PathVariable String sendMemoId) {
    	SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMemo(sendMemoId);
        return ApiResponse.Ok(sendMemoVO, sendMemoVO == null ? 0 : 1);
    }

    /**
     * 쪽지 발신
     * @TODO 수신자 유효성체크 확인 / id 확인
     */
    @PostMapping("/send")
    public ApiResponse doSendMemo( SendMemoVO sendMemoVO,
                                  @RequestParam(required = false) MultipartFile file,
                                  Authentication authentication) {
    	
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        // 1.validation check
        boolean isNotEmptyTitle = ValidationUtils.notEmpty(sendMemoVO.getMemoTtl());
        boolean isNotEmptyContent = ValidationUtils.notEmpty(sendMemoVO.getMemoCntnt());
        
        List<String> errorMessage = null;

        if(!isNotEmptyTitle) {
            if(errorMessage == null) {
                errorMessage = new ArrayList<>();
            }
            errorMessage.add("제목을 입력해주세요");
        }
        if(!isNotEmptyContent) {
            if(errorMessage == null) {
                errorMessage = new ArrayList<>();
            }
            errorMessage.add("내용을 입력해주세요");
        }
        if(errorMessage != null) {
            return ApiResponse.BAD_REQUEST(errorMessage);
        }


        // 2.insert
        sendMemoVO.setSendId(employeeVO.getEmpId());
        boolean isCreateSuccess = this.sendMemoService.createSendMemo(sendMemoVO, file);

        return ApiResponse.Ok(isCreateSuccess);
    }

    /**
     * 발신 취소
     * @TODO 수신쪽지 읽음상태 확인해서 읽은 쪽지 있으면 취소 불가 처리
     */
    @PutMapping("/send/cancel/{sendMemoId}")
    public ApiResponse cancelOneSendMemo(@PathVariable String sendMemoId, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        SendMemoVO originSendMemoVO = this.sendMemoService.getOneSendMemo(sendMemoId);
        if(!employeeVO.getEmpId().equals(originSendMemoVO.getSendId())) {
            return ApiResponse.FORBIDDEN("취소할 권한이 없습니다.");
        }

        // 발신 쪽지 개수
        int sendMemoCount = this.sendMemoService.getSendCountBySendMemoId(sendMemoId);
        System.out.println("ApiMemoController.cancelOneSendMemo: sendMemoCount > " + sendMemoCount);

        // 확인하지 않은 수신 쪽지 개수
        int rcvMemoCount = this.receiveMemoService.getRcvCountBySendMemoId(sendMemoId);
        System.out.println("ApiMemoController.cancelOneSendMemo: rcvMemoCount > " + rcvMemoCount);

        if(sendMemoCount != rcvMemoCount) {
            return ApiResponse.FORBIDDEN("수신 확인한 쪽지가 있어 취소할 수 없습니다.");
			 
        }

        boolean isCancelSuccess = this.sendMemoService.cancelOneSendMemo(sendMemoId, rcvMemoCount);
        return ApiResponse.Ok(isCancelSuccess);
    }

    /**
     * 발신 쪽지 보관
     */
    @PutMapping("/send/save/{sendMemoId}")
    public ApiResponse saveOneSendMemo(@PathVariable String sendMemoId, @RequestBody SendMemoVO sendMemoVO, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        SendMemoVO originalSendMemoVO = this.sendMemoService.getOneSendMemo(sendMemoId);
        if(!employeeVO.getEmpId().equals(originalSendMemoVO.getSendId())) {
            return ApiResponse.FORBIDDEN("권한이 없습니다.");
        }

        originalSendMemoVO.setSendSaveYn(sendMemoVO.getSendSaveYn());
        boolean isSaveSuccess = this.sendMemoService.saveOneSendMemo(originalSendMemoVO);
        return ApiResponse.Ok(isSaveSuccess);
    }

    /**
     * 발신 쪽지 삭제
     */
    @DeleteMapping("/send/{sendMemoId}")
    public ApiResponse deleteSendMemo(@PathVariable String sendMemoId, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMemo(sendMemoId);
        if(!employeeVO.getEmpId().equals(sendMemoVO.getSendId())) {
            return ApiResponse.FORBIDDEN("삭제할 권한이 없습니다.");
        }
        boolean isDeleteSuccess = this.sendMemoService.deleteOneSendMemo(sendMemoId);
        return ApiResponse.Ok(isDeleteSuccess);
    }


    // ---------- 수신 ----------
    /**
     * 로그인한 사원의 수신 쪽지 전체 조회
     */
    @GetMapping("/receive")
    public ApiResponse searchReceiveMemoList(SearchMemoVO searchMemoVO, Authentication authentication) {
    	
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();
        searchMemoVO.setEmpId(employeeVO.getEmpId());
        ReceiveMemoListVO receiveMemoListVO = this.receiveMemoService.searchAllReceiveMemo(searchMemoVO);
        
        return ApiResponse.Ok(receiveMemoListVO.getReceiveMemoList(), receiveMemoListVO.getRcvMemoCnt(),
                searchMemoVO.getPageCount(), searchMemoVO.getPageNo() < searchMemoVO.getPageCount() - 1);
    }
    
    /**
     * 보관처리한 수신 쪽지 전체 조회
     */
    @GetMapping("/receive/save")
    public ApiResponse searchSaveRcvMemoList(SearchMemoVO searchMemoVO, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        searchMemoVO.setEmpId(employeeVO.getEmpId());
        searchMemoVO.setSearchKeyword("Y");
        searchMemoVO.setSearchType("rcv_save");
        ReceiveMemoListVO saveReceiveMemoListVO = this.receiveMemoService.searchAllReceiveMemo(searchMemoVO);

        return ApiResponse.Ok(saveReceiveMemoListVO.getReceiveMemoList(), saveReceiveMemoListVO.getRcvMemoCnt(),
                searchMemoVO.getPageCount(), searchMemoVO.getPageNo() < searchMemoVO.getPageCount() - 1);
    }

    /**
     * 한 개의 수신 쪽지 조회
     */
    @GetMapping("/receive/{rcvMemoId}")
    public ApiResponse viewReceiveMemo(@PathVariable String rcvMemoId) {
        ReceiveMemoVO receiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        return ApiResponse.Ok(receiveMemoVO, receiveMemoVO == null ? 0 : 1);
    }

    /**
     * 발신 쪽지 ID로 수신 쪽지 전체 조회
     */
    @GetMapping("/receive/sendMemo/{sendMemoId}")
    public ApiResponse searchReceiveMemoListBySendMemoId(@PathVariable String sendMemoId) {
        List<String> receiveMemoList = this.receiveMemoService.searchReceiveMemoListBySendMemoId(sendMemoId);
        return ApiResponse.Ok(receiveMemoList, receiveMemoList == null ? 0 : 1);
    }

    /**
     * 수신 쪽지 읽음
     */
    @PutMapping("/receive/read/{rcvMemoId}")
    public ApiResponse doReceiveDateModify(@PathVariable String rcvMemoId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        ReceiveMemoVO originReceiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        if(! originReceiveMemoVO.getRcvId().equals(employeeVO.getEmpId())) {
            throw new PageNotFoundException();
        }

        boolean isModifySuccess = this.receiveMemoService.updateReceiveMemoDate(rcvMemoId);
        return ApiResponse.Ok(isModifySuccess);
    }

    /**
     * 수신 쪽지 보관
     */
    @PutMapping("/receive/save/{rcvMemoId}")
    public ApiResponse saveOneReceiveMemo(@PathVariable String rcvMemoId, @RequestBody ReceiveMemoVO receiveMemoVO, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        ReceiveMemoVO originReceiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        if(!employeeVO.getEmpId().equals(originReceiveMemoVO.getRcvId())) {
            return ApiResponse.FORBIDDEN("권한이 없습니다.");
        }
        
        originReceiveMemoVO.setRcvSaveYn(receiveMemoVO.getRcvSaveYn());
        boolean isSaveSuccess = this.receiveMemoService.saveOneReceiveMemo(originReceiveMemoVO);
        return ApiResponse.Ok(isSaveSuccess);
    }

    /**
     * 수신 쪽지 삭제
     */
    @DeleteMapping("/receive/{rcvMemoId}")
    public ApiResponse deleteOneReceiveMemo(@PathVariable String rcvMemoId, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        ReceiveMemoVO receiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        if(!employeeVO.getEmpId().equals(receiveMemoVO.getRcvId())) {
            return ApiResponse.FORBIDDEN("삭제할 권한이 없습니다.");
        }
        boolean isDeleteSuccess = this.receiveMemoService.deleteOneReceiveMemo(rcvMemoId);
        return ApiResponse.Ok(isDeleteSuccess);
    }

}

