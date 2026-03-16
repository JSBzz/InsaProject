package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.service.EmpInfoService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpTO;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/empinfomgmt/*")
@RestController
public class EmpRegisterController {
	
	@Autowired
	private EmpInfoService empInfoService;

	@PostMapping("/employee")
	public ResultTO registEmployee(@RequestBody EmpTO emp) {		
		ResultTO result = new ResultTO();
		try {
			empInfoService.registEmployee(emp);			
			result.setErrorMsg("success");
			result.setErrorCode("0");

		} catch (Exception e) {
			result.setErrorMsg(e.getMessage());
			result.setErrorCode("-1");

		}
		return result;
	}

	
	@GetMapping("/employee")
	public ResultTO findLastEmpCode() {
		ResultTO result = new ResultTO();		
		try {
			String empCode = empInfoService.findLastEmpCode();
			result.setAttribute("lastEmpCode", empCode);
			result.setErrorMsg("success");
			result.setErrorCode("0");

			
		} catch (Exception dae) {
			result.setErrorCode("-1");
			result.setErrorMsg(dae.getMessage());
		}
		return result;
	}

}
