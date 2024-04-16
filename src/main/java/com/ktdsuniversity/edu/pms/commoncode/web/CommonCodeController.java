package com.ktdsuniversity.edu.pms.commoncode.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.pms.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.pms.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.pms.utils.AjaxResponse;

@Controller
public class CommonCodeController {

	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/commoncode")
	public synchronized String viewCommonCodeListPage(Model model) {

		List<CommonCodeVO> codeList = commonCodeService.getAllCommonCodeList();

		model.addAttribute("mainCodeList", codeList.stream()
				.filter(code -> code.getCmcdPid() == null).toList());

		return "commoncode/list";
	}

	@ResponseBody
	@GetMapping("/ajax/commoncode/{pid}")
	public synchronized AjaxResponse getCommonCodeByPID(
			@PathVariable String pid) {

		List<CommonCodeVO> codeList = commonCodeService.getAllCommonCodeList();

		return new AjaxResponse().append("codeList",
				codeList.stream().filter(code -> code.getCmcdPid() != null)
						.filter(code -> code.getCmcdPid().equals(pid))
						.toList());
	}

}
