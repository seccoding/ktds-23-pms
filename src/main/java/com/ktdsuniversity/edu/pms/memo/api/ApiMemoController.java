package com.ktdsuniversity.edu.pms.memo.api;

import com.ktdsuniversity.edu.pms.beans.security.SecurityUser;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.exceptions.PageNotFoundException;
import com.ktdsuniversity.edu.pms.memo.service.ReceiveMemoService;
import com.ktdsuniversity.edu.pms.memo.service.SendMemoService;
import com.ktdsuniversity.edu.pms.memo.vo.*;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;
import com.ktdsuniversity.edu.pms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/memo")
public class ApiMemoController {

    @Autowired
    private SendMemoService sendMemoService;
    @Autowired
    private ReceiveMemoService receiveMemoService;

    // ---------- 발신 ----------
    /**
     * 로그인한 사원의 발신 쪽지 전체 조회
     * @Todo Authentication으로 사원아이디 넘겨주기(searchMemoVO에 empId 추가)
     */
    @GetMapping("/send")
    public ApiResponse searchSendMemoList(SearchMemoVO searchMemoVO) {
        SendMemoListVO sendMemoListVO = this.sendMemoService.searchAllSendMemo(searchMemoVO);
        return ApiResponse.Ok(sendMemoListVO.getSendMemoList(), sendMemoListVO.getSendMenoCnt(),
                                searchMemoVO.getPageCount(), searchMemoVO.getPageNo() < searchMemoVO.getPageCount() - 1);
    }

    /**
     * 한 개의 발신 쪽지 조회
     */
    @GetMapping("/send/{sendMemoId}")
    public ApiResponse viewSendMemo(@PathVariable String sendMemoId) {
        SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMomo(sendMemoId);
        return ApiResponse.Ok(sendMemoVO, sendMemoVO == null ? 0 : 1);
    }

    /**
     * 쪽지 발신
     */
    @PostMapping("/send")
    public ApiResponse doSendMemo(SendMemoVO sendMemoVO,
                                  @RequestParam(required = false) MultipartFile file,
                                  Authentication authentication) {

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
        sendMemoVO.setSendId(authentication.getName());
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

        SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMomo(sendMemoId);
        if(!employeeVO.getEmpId().equals(sendMemoVO.getSendId())) {
            return ApiResponse.FORBIDDEN("취소할 권한이 없습니다.");
        }

        List<String> receiveMemoIdList = this.receiveMemoService.searchReceiveMemoListBySendMemoId(sendMemoId);
        for(String rcvMemoId : receiveMemoIdList) {
            String rcvData = this.receiveMemoService.getOneReceiveMemo(rcvMemoId).getRcvDate();
            if(rcvData == null) {
                return ApiResponse.FORBIDDEN("확인한 쪽지가 있어 취소할 수 없습니다.");
            }
        }
        boolean isCancelSuccess = this.sendMemoService.cancelOneSendMemo(sendMemoId, receiveMemoIdList);
        return ApiResponse.Ok(isCancelSuccess);
    }

    /**
     * 발신 쪽지 보관
     */
    @PutMapping("/send/save/{sendMemoId}")
    public ApiResponse saveOneSendMemo(@PathVariable String sendMemoId, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMomo(sendMemoId);
        if(!employeeVO.getEmpId().equals(sendMemoVO.getSendId())) {
            return ApiResponse.FORBIDDEN("삭제할 권한이 없습니다.");
        }
        boolean isSaveSuccess = this.sendMemoService.saveOneSendMemo(sendMemoId);
        return ApiResponse.Ok(isSaveSuccess);
    }

    /**
     * 발신 쪽지 삭제
     */
    @DeleteMapping("/send/{sendMemoId}")
    public ApiResponse deleteSendMemo(@PathVariable String sendMemoId, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        SendMemoVO sendMemoVO = this.sendMemoService.getOneSendMomo(sendMemoId);
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
    public ApiResponse searchReceiveMemoList(SearchMemoVO searchMemoVO) {
        ReceiveMemoListVO receiveMemoListVO = this.receiveMemoService.searchAllReceiveMemo(searchMemoVO);
        return ApiResponse.Ok(receiveMemoListVO.getReceiveMemoList(), receiveMemoListVO.getRcvMemoCnt(),
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
    @GetMapping("/receive/{sendMemoId}")
    public ApiResponse searchReceiveMemoListBySendMemoId(@PathVariable String sendMemoId) {
        List<String> receiveMemoList = this.receiveMemoService.searchReceiveMemoListBySendMemoId(sendMemoId);
        return ApiResponse.Ok(receiveMemoList, receiveMemoList == null ? 0 : 1);
    }

    /**
     * 수신 쪽지 읽음
     */
    @PutMapping("/receive/read/{rcvMemoId}")
    public ApiResponse doReceiveDateModify(@PathVariable String rcvMemoId, ReceiveMemoVO receiveMemoVO,
                                           Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        ReceiveMemoVO originReceiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        if(! originReceiveMemoVO.getRcvMemoId().equals(employeeVO.getEmpId())) {
            throw new PageNotFoundException();
        }

        boolean isModifySuccess = this.receiveMemoService.updateReceiveMemoDate(rcvMemoId);
        return ApiResponse.Ok(isModifySuccess);
    }

    /**
     * 수신 쪽지 보관
     */
    @PutMapping("/receive/save/{rcvMemoId}")
    public ApiResponse saveOneReceiveMemo(@PathVariable String rcvMemoId, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        EmployeeVO employeeVO = ((SecurityUser) userDetails).getEmployeeVO();

        ReceiveMemoVO receiveMemoVO = this.receiveMemoService.getOneReceiveMemo(rcvMemoId);
        if(!employeeVO.getEmpId().equals(receiveMemoVO.getRcvId())) {
            return ApiResponse.FORBIDDEN("삭제할 권한이 없습니다.");
        }
        boolean isSaveSuccess = this.receiveMemoService.saveOneReceiveMemo(rcvMemoId);
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

