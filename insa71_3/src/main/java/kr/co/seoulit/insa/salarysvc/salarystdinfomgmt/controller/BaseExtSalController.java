package kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.service.SalaryStdInfoMgmtService;
import kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.to.BaseExtSalTO;


@RequestMapping("/salarystdinfomgmt/*")
@RestController
public class BaseExtSalController {
	
	@Autowired
	private SalaryStdInfoMgmtService salaryStdInfoMgmtService;	

	@GetMapping("over-sal")
	public ResultTO findBaseExtSalList(){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<BaseExtSalTO> baseExtSalList = salaryStdInfoMgmtService.findBaseExtSalList();
			resultTO.setAttribute("baseExtSalList", baseExtSalList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	
	@PutMapping("over-sal")
	public ResultTO modifyBaseExtSalList(@RequestBody ArrayList<BaseExtSalTO> baseExtSalList){		
		ResultTO resultTO = new ResultTO();
		try { 		
			salaryStdInfoMgmtService.modifyBaseExtSalList(baseExtSalList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
}
