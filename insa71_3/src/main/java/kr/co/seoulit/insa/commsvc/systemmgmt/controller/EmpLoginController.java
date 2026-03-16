package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpTO;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/systemmgmt/*")
@RestController
public class EmpLoginController {
	
	@Autowired
	private SystemMgmtService systemMgmtService;
	
	@GetMapping("/login")
	public ResultTO empLogin(@RequestParam String empName, @RequestParam String empCode, HttpServletRequest request, HttpServletResponse response) {		
		ResultTO result = new ResultTO();		
		try {
			EmpTO empto = systemMgmtService.findEmp(empName, empCode,request, response);
			
				if(empto!=null) {
					request.getSession().setAttribute("id", empName);
					request.getSession().setAttribute("dept", empto.getDeptName());
					request.getSession().setAttribute("position", empto.getPosition());
					request.getSession().setAttribute("code", empto.getEmpCode());
					request.getSession().setAttribute("authority", empto.getAuthority());
			
					result.setAttribute("me", "enter"); 
					result.setErrorCode("0");
					result.setErrorMsg("success");
				}

		}catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());		
		}
		return result; 
	}
	
}
