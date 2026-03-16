package kr.co.seoulit.insa.attdsvc.attdappvl.controller;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.attdsvc.attdappvl.service.AttdAppvlService;
import kr.co.seoulit.insa.attdsvc.attdappvl.to.DayAttdMgtTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RestController
@RequestMapping("/attdappvl/*")
public class DailyAttendanceMgtController {
	
	@Autowired
	private AttdAppvlService attdAppvlService;
	
	@GetMapping("day-attnd")
	public ResultTO findDayAttdMgtList(@RequestParam("applyDay") String applyDay){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<DayAttdMgtTO> dayAttdMgtList = attdAppvlService.findDayAttdMgtList(applyDay);
			resultTO.setAttribute("dayAttdMgtList", dayAttdMgtList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	@PutMapping("day-attnd")
	public ResultTO modifyDayAttdList(@RequestBody ArrayList<DayAttdMgtTO> dayAttdMgtList){
		ResultTO resultTO = new ResultTO();
		try {	
			attdAppvlService.modifyDayAttdMgtList(dayAttdMgtList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}	

}
