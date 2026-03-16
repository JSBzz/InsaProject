package kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.service.SalaryStdInfoMgmtService;
import kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.to.BaseSalaryTO;


@RequestMapping("/salarystdinfomgmt/*")
@RestController
public class BaseSalaryController {
	
	@Autowired
	private SalaryStdInfoMgmtService salaryStdInfoMgmtService;	
	
	@GetMapping("base-salary")
	public ResultTO findBaseSalaryList(){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<BaseSalaryTO> baseSalaryList = salaryStdInfoMgmtService.findBaseSalaryList();
			resultTO.setAttribute("baseSalaryList", baseSalaryList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	
	@PutMapping("base-salary")
	public ResultTO modifyBaseSalaryList(@RequestBody ArrayList<BaseSalaryTO> baseSalaryList){		
		ResultTO resultTO = new ResultTO();
		try { 			
			salaryStdInfoMgmtService.modifyBaseSalaryList(baseSalaryList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
}
