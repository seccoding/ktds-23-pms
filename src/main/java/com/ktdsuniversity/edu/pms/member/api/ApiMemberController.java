package com.ktdsuniversity.edu.pms.member.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.pms.member.service.MemberService;
import com.ktdsuniversity.edu.pms.member.vo.MemberVO;
import com.ktdsuniversity.edu.pms.member.vo.PhonePlanVO;
import com.ktdsuniversity.edu.pms.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class ApiMemberController {
    
    @Autowired
    private MemberService memberService;
    
    @PostMapping("/member")
    public ApiResponse registOneMember(@RequestBody MemberVO memberVO) {
        // Validator<MemberVO> validator = new Validator<>(memberVO);
        // 추후 수정
        // validator.add("memId", Type.NOT_EMPTY, "ID를 입력해 주세요.")
        //          .add("memId", Type.EMPID, "ID 형식을 확인해 주세요.")
        //          .add("pwd", Type.NOT_EMPTY, "비밀번호를 입력해 주세요.");
        // if (validator.hasErrors()) {
        //     return ApiResponse.BAD_REQUEST(validator.getErrors());
        // }
        
        boolean isCreateSuccess = this.memberService.createMember(memberVO);

        return ApiResponse.Ok(isCreateSuccess);
        
    }
    
    @GetMapping("/phoneplan")
    public ApiResponse getAllPhonePlan() {
    	try {
            List<PhonePlanVO> phonePlans = this.memberService.getAllPhonePlans();
            return ApiResponse.Ok(phonePlans, phonePlans.size());
        } catch (Exception e) {
            return ApiResponse.Error("Failed to fetch phone plans.");
        }
    }
}
