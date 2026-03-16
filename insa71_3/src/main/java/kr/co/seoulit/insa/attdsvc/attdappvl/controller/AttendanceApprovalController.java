package kr.co.seoulit.insa.attdsvc.attdappvl.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.attdsvc.attdappvl.service.AttdAppvlService;
import kr.co.seoulit.insa.attdsvc.attdmgmt.to.RestAttdTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RestController
@RequestMapping("/attdappvl/*")
public class AttendanceApprovalController {

	@Autowired
	private AttdAppvlService attdAppvlService;

	@GetMapping("attnd-approval")
	public ResultTO findRestAttdListByDept(@RequestParam("startDate") String startDate,
										 @RequestParam("endDate") String endDate,
										 @RequestParam("deptName") String deptName){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<RestAttdTO> restAttdList = attdAppvlService.findRestAttdListByDept(deptName, startDate, endDate);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
			resultTO.setAttribute("restAttdList", restAttdList);
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}


	@PutMapping("attnd-approval")
	public ResultTO modifyRestAttdList(@RequestBody ArrayList<RestAttdTO> restAttdList){		
		ResultTO resultTO = new ResultTO();
		try {
			attdAppvlService.modifyRestAttdList(restAttdList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO; 
	} 

}
