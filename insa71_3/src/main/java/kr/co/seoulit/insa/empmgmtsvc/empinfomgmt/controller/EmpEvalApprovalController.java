package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.service.EmpInfoService;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpEvalTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/empinfomgmt/*")
@RestController
public class EmpEvalApprovalController {
	
	@Autowired
	private EmpInfoService empInfoService;
	
	@GetMapping("/evaluation-approval")
	public ResultTO findEmpEvalAppoList(@RequestParam String deptName, @RequestParam String year) {
		
		ResultTO resultTO = new ResultTO();
		
		try {	
			ArrayList<EmpEvalTO> empEvalList = empInfoService.findEmpEval(deptName,year);
			resultTO.setAttribute("empEvalList", empEvalList);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");
		} catch (Exception dae) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	
	@PutMapping("evaluation-approval")
	public ResultTO modifyEmpEvalList(@RequestBody ArrayList<EmpEvalTO> empevalList) {
		
		ResultTO resultTO = new ResultTO();
		
		try {
			empInfoService.modifyEmpEvalList(empevalList);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");
			
		} catch (Exception dae) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());

		}
		return resultTO;
	}
}
