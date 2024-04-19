package com.ktdsuniversity.edu.pms.commoncode.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.employee.vo.EmployeeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class CommonCodeController {

	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/commoncode")
	public String viewCommonCodeListPage(Model model) {

		List<CommonCodeVO> codeList = this.commonCodeService
				.getAllCommonCodeList();

		model.addAttribute("mainCodeList", codeList.stream()
				.filter(code -> code.getCmcdPid() == null).toList());

		return "commoncode/list";
	}

	@ResponseBody
	@GetMapping("/ajax/commoncode/reload")
	public AjaxResponse getCommonCode(Model model) {

		List<CommonCodeVO> codeList = this.commonCodeService
				.getAllCommonCodeList();

		return new AjaxResponse().append("codeList", codeList.stream()
				.filter(code -> code.getCmcdPid() == null).toList());
	}

	@ResponseBody
	@GetMapping("/ajax/commoncode/{pid}")
	public AjaxResponse getCommonCodeByPID(@PathVariable String pid) {

		List<CommonCodeVO> codeList = this.commonCodeService
				.getAllCommonCodeList();

		return new AjaxResponse().append("codeList",
				codeList.stream().filter(code -> code.getCmcdPid() != null)
						.filter(code -> code.getCmcdPid().equals(pid))
						.toList());
	}

	@ResponseBody
	@PostMapping("/ajax/commoncode/new")
	public AjaxResponse saveNewCommonCode(
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			CommonCodeVO commonCodeVO) {

		commonCodeVO.setCrtrId(employeeVO.getEmpId());

		boolean isSuccess = this.commonCodeService
				.saveNewCommonCode(commonCodeVO);

		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@PostMapping("/ajax/commoncode/update")
	public AjaxResponse updateCommonCode(
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			CommonCodeVO commonCodeVO) {

		commonCodeVO.setMdfrId(employeeVO.getEmpId());

		boolean isSuccess = this.commonCodeService
				.updateCommonCode(commonCodeVO);

		return new AjaxResponse().append("result", isSuccess);
	}

	@ResponseBody
	@GetMapping("/ajax/commoncode/delete/{cmcdId}")
	public AjaxResponse updateCommonCode(
			@SessionAttribute("_LOGIN_USER_") EmployeeVO employeeVO,
			@PathVariable String cmcdId) {

		CommonCodeVO commonCodeVO = new CommonCodeVO();
		commonCodeVO.setCmcdId(cmcdId);
		commonCodeVO.setMdfrId(employeeVO.getEmpId());

		boolean isSuccess = this.commonCodeService
				.deleteCommonCode(commonCodeVO);

		return new AjaxResponse().append("result", isSuccess);
	}

}
