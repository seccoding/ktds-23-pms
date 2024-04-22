package com.ktdsuniversity.edu.pms.approval.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.approval.service.ApprovalDetailService;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class ApprovalDetailController {

    @Autowired
    private ApprovalDetailService approvalDetailService;

    // 결재 승인 후 비품 사용 불가
    @ResponseBody
    @PostMapping("/ajax/apprDtl/unusablePrdt")
    public AjaxResponse doUnusableProduct(String apprId) {
        boolean isSuccessChanged = this.approvalDetailService.updateUnusablePrdt(apprId);
        return new AjaxResponse().append("result", isSuccessChanged)
                                 .append("next", "/approval/view?apprId=" + apprId);
    }
}
