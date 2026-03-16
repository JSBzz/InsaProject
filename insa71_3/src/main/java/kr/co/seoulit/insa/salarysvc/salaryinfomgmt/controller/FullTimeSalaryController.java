package kr.co.seoulit.insa.salarysvc.salaryinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.service.SalaryInfoMgmtService;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to.FullTimeSalTO;
import kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to.PayDayTO;


@RequestMapping("/salaryinfomgmt/*")
@RestController
public class FullTimeSalaryController {
	
	@Autowired
	private SalaryInfoMgmtService salaryInfoMgmtService;
	
	@GetMapping("salary")
	public ResultTO AllMoneyList(@RequestParam("apply_year_month") String applyYearMonth){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<FullTimeSalTO> AllMoneyList = salaryInfoMgmtService.findAllMoney(applyYearMonth);
			resultTO.setAttribute("AllMoneyList", AllMoneyList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	

	@GetMapping("/salary/empcode")
	public ResultTO selectSalary(@RequestParam("apply_year_month") String applyYearMonth,
								 @RequestParam("empCode") String empCode){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<FullTimeSalTO> fullTimeSalaryList = salaryInfoMgmtService.findselectSalary(applyYearMonth,empCode);
			resultTO.setAttribute("FullTimeSalaryList", fullTimeSalaryList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}	
		return resultTO;
	}

	@PutMapping("salary")
	public ResultTO modifyFullTimeSalary(@RequestBody ArrayList<FullTimeSalTO> fullTimeSalary){
		ResultTO resultTO = new ResultTO();
		      try {
		         salaryInfoMgmtService.modifyFullTimeSalary(fullTimeSalary);
		         resultTO.setErrorCode("0");
		         resultTO.setErrorMsg("success");
		      } catch (Exception e) { 
		         resultTO.setErrorCode("-1");
		         resultTO.setErrorMsg(e.getMessage());
		      }
		      return resultTO;
		   }
	
	@GetMapping("payday")
	public ResultTO paydayList() {
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<PayDayTO> list = salaryInfoMgmtService.findPayDayList();
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