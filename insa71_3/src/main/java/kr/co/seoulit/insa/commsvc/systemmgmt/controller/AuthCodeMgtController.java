package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.AdminCodeTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RequestMapping("/systemmgmt/*")
@RestController
public class AuthCodeMgtController {
	
	@Autowired
	private SystemMgmtService systemMgmtService;	
	
	@GetMapping("adminCodeList")
	public ResultTO adminCodeList() {
		ResultTO result = new ResultTO();
		try {
			ArrayList<AdminCodeTO> authCodeList = (ArrayList<AdminCodeTO>) systemMgmtService.adminCodeList();
			result.setAttribute("authCodeList", authCodeList);
			result.setErrorCode("0");
			result.setErrorMsg("Success!");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	@PutMapping("authcode")
	public ResultTO modifyAuthority(@RequestParam String empCode, @RequestParam String adminCode){
		ResultTO result = new ResultTO();
			try {
				systemMgmtService.modifyAuthority(empCode , adminCode);
				result.setErrorCode("0");
				result.setErrorMsg("Success!");
			} catch (Exception e) {
				result.setErrorCode("-1");
				result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	
	@GetMapping("authcode")
	public ResultTO authadminCodeList(@RequestParam String empCode) {
		ResultTO result = new ResultTO();
		try {
			ArrayList<AdminCodeTO> authadminCodeList = (ArrayList<AdminCodeTO>) systemMgmtService.authadminCodeList(empCode);
			result.setAttribute("authadminCodeList", authadminCodeList);
			result.setErrorCode("0");
			result.setErrorMsg("Success!");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
}
