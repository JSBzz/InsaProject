package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.service.EmpInfoService;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpEvalTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/empinfomgmt/*")
@RestController
public class EmpEvalController {
	
	@Autowired
	private EmpInfoService empInfoService;
	
	@PostMapping("evaluation")
	public ResultTO registEmpEval(@RequestBody EmpEvalTO emp){		
		ResultTO resultTO = new ResultTO();
		try{			
			empInfoService.registEmpEval(emp);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");
			
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	
	@GetMapping("/evaluation")
	public ResultTO findEmpEval(){		
		ResultTO resultTO = new ResultTO();
		try{
			ArrayList<EmpEvalTO> empevalList = empInfoService.findEmpEval();			
			resultTO.setAttribute("empevalList", empevalList);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");

		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	@DeleteMapping("evaluation")
	public ResultTO removeEmpEvalList(@RequestParam("emp_code") String emp_code, @RequestParam("apply_day") String apply_day){		
		ResultTO resultTO = new ResultTO();
		try{			
			empInfoService.removeEmpEvalList(emp_code, apply_day);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");

		} catch (Exception dae){	
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
}
