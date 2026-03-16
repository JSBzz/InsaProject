package kr.co.seoulit.insa.salarysvc.salaryinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.service.SalaryInfoMgmtService;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to.SalaryBonusTO;


@RequestMapping("/salaryinfomgmt/*")
@RestController
public class SalaryAwardsController {
	
	@Autowired
	private SalaryInfoMgmtService salaryInfoMgmtService;	
	
	@GetMapping("awards")
	public ResultTO salInfo(@RequestParam("empCode") String empCode){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<SalaryBonusTO> list = salaryInfoMgmtService.findBonusSalary(empCode);	
			resultTO.setAttribute("List", list);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception e) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(e.getMessage());
		}
		return resultTO;
	}
	
}