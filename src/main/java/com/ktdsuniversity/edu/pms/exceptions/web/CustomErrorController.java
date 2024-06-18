//package com.ktdsuniversity.edu.pms.exceptions.web;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.ktdsuniversity.edu.pms.utils.RequestUtil;
//
//import jakarta.servlet.RequestDispatcher;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//	@GetMapping("/error")
//	public String viewPageNotFoundErrorPage() {
//		Object status = RequestUtil.getRequest()
//				.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//		if (status != null) {
//			int statusCode = Integer.parseInt(status.toString());
//			return "error/" + statusCode;
//		}
//
//		return "error/404";
//	}
//
//}
