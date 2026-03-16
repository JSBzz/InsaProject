package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.service.EmpInfoService;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/empinfomgmt/*")
@RestController
public class EmpListController {
	
	@Autowired
	private EmpInfoService empInfoService;

	@GetMapping("/emplist")
	public ResultTO emplist(@RequestParam(value = "value", required = false, defaultValue = "전체부서") String value) {
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<EmpTO> list = empInfoService.findEmpList(value);
			resultTO.setAttribute("list", list);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception e) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(e.getMessage());			
		}
		return resultTO;
	}	
	
}