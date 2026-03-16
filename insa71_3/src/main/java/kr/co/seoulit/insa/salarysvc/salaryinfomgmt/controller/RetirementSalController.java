package kr.co.seoulit.insa.salarysvc.salaryinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.service.SalaryInfoMgmtService;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to.RetirementSalaryTO;


@RequestMapping("/salaryinfomgmt/*")
@RestController
public class RetirementSalController {
	
	@Autowired
	private SalaryInfoMgmtService salaryInfoMgmtService;	
	
	@GetMapping("retirement")
	public ResultTO retirementSalaryList(@RequestParam("empCode") String empCode){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<RetirementSalaryTO> retirementSalaryList = salaryInfoMgmtService.findretirementSalaryList(empCode);
			resultTO.setAttribute("retirementSalaryList", retirementSalaryList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
}
