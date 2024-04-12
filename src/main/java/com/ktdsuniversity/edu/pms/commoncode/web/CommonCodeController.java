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

	private static List<CommonCodeVO> COMMON_CODE_LIST;
	public static boolean NEED_RELOAD;
	private static Object LOCK;

	public static void needReload() {
		NEED_RELOAD = true;
	}

	static {
		NEED_RELOAD = false;
		if (COMMON_CODE_LIST == null) {
			NEED_RELOAD = true;
		}

		LOCK = new Object();
	}

	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/commoncode")
	public synchronized String viewCommonCodeListPage(Model model) {

		synchronized (LOCK) {
			if (NEED_RELOAD) {
				COMMON_CODE_LIST = commonCodeService.getAllCommonCodeList();
			}
		}

		model.addAttribute("mainCodeList", COMMON_CODE_LIST.stream()
				.filter(code -> code.getCmcdPid() == null).toList());

		return "commoncode/list";
	}

	@ResponseBody
	@GetMapping("/ajax/commoncode/{pid}")
	public synchronized AjaxResponse getCommonCodeByPID(
			@PathVariable String pid) {

		synchronized (LOCK) {
			if (NEED_RELOAD) {
				COMMON_CODE_LIST = commonCodeService.getAllCommonCodeList();
			}
		}

		return new AjaxResponse().append("codeList", COMMON_CODE_LIST.stream()
				.filter(code -> code.getCmcdPid() != null)
				.filter(code -> code.getCmcdPid().equals(pid)).toList());
	}

}
