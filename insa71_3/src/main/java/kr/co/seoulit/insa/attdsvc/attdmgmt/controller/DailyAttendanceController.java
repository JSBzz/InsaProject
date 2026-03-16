package kr.co.seoulit.insa.attdsvc.attdmgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.attdsvc.attdmgmt.service.AttdMgmtService;
import kr.co.seoulit.insa.attdsvc.attdmgmt.to.DayAttdTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RestController
@RequestMapping("/attdmgmt/*")
public class DailyAttendanceController {
	
	@Autowired
	private AttdMgmtService attdMgmtService;
	
	@GetMapping("daily-attnd")
	public ResultTO findDayAttdList(@RequestParam("applyDay") String applyDay, 
									@RequestParam("empCode") String empCode){	 
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<DayAttdTO> dayAttdList = attdMgmtService.findDayAttdList(empCode, applyDay);
			resultTO.setAttribute("dayAttdList", dayAttdList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		}catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	@PostMapping("daily-attnd")
	public ResultTO registDayAttd(@RequestBody DayAttdTO dayAttd){
		ResultTO resultTO = null;
		try {			
			resultTO = attdMgmtService.registDayAttd(dayAttd);
		}catch (Exception dae){
			resultTO = new ResultTO();
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	@DeleteMapping("daily-attnd")
	public ResultTO removeDayAttdList(@RequestBody ArrayList<DayAttdTO> dayAttdList){
		ResultTO resultTO = new ResultTO();
		try {
			attdMgmtService.removeDayAttdList(dayAttdList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		}catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	@PostMapping("insert-daily-attnd")
	public ResultTO insertDayAttd(@RequestBody DayAttdTO dayAttd){ 
		ResultTO resultTO = new ResultTO();
		try {		
			attdMgmtService.insertDayAttd(dayAttd);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		}catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
}
