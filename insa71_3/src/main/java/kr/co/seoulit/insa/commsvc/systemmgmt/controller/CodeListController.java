package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.CodeTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.DetailCodeTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;


@RequestMapping("/systemmgmt/*")
@RestController
public class CodeListController {

	@Autowired
	private SystemMgmtService systemMgmtService;

	@GetMapping("codelist")
	public ResultTO detailCodelist(@RequestParam String code) {
		ResultTO result = new ResultTO();
		try {
			ArrayList<DetailCodeTO> detailCodeList = systemMgmtService.findDetailCodeList(code);
			result.setAttribute("detailCodeList", detailCodeList);
			result.setErrorMsg("success");
			result.setErrorCode("0");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	@GetMapping("code/rest")
	public ResultTO detailCodelistRest(@RequestParam String code1, 
                                     @RequestParam String code2, 
                                     @RequestParam String code3) {
		ResultTO result = new ResultTO();
		try {
			ArrayList<DetailCodeTO> detailCodeList = systemMgmtService.findDetailCodeListRest(code1, code2, code3);
			result.setAttribute("detailCodeList", detailCodeList);
			result.setErrorMsg("success");
			result.setErrorCode("0");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	
	@GetMapping("codelist/all")
	public ResultTO codelist() {
		ResultTO result = new ResultTO();
		try {
			ArrayList<CodeTO> codeList = systemMgmtService.findCodeList();
			result.setAttribute("codeList", codeList);
			result.setErrorMsg("success");
			result.setErrorCode("0");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
}
