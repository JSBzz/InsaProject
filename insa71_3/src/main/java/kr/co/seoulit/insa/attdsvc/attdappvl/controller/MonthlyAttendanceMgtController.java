package kr.co.seoulit.insa.attdsvc.attdappvl.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.attdsvc.attdappvl.service.AttdAppvlService;
import kr.co.seoulit.insa.attdsvc.attdappvl.to.MonthAttdMgtTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RestController
@RequestMapping("/attdappvl/*")
public class MonthlyAttendanceMgtController {
	
	@Autowired
	private AttdAppvlService attdAppvlService;
	
	@GetMapping("month-attnd")
	public ResultTO findMonthAttdMgtList(@RequestParam("applyYearMonth") String applyYearMonth){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<MonthAttdMgtTO> monthAttdMgtList = attdAppvlService.findMonthAttdMgtList(applyYearMonth);
			resultTO.setAttribute("monthAttdMgtList", monthAttdMgtList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		}catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	
	@PutMapping("month-attnd")
	public ResultTO modifyMonthAttdList(@RequestBody ArrayList<MonthAttdMgtTO> monthAttdMgtList){	
		ResultTO resultTO = new ResultTO();
		try {
			attdAppvlService.modifyMonthAttdMgtList(monthAttdMgtList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		}catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	} 

}
