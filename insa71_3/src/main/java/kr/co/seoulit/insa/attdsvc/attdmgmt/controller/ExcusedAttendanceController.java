package kr.co.seoulit.insa.attdsvc.attdmgmt.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.attdsvc.attdmgmt.service.AttdMgmtService;
import kr.co.seoulit.insa.attdsvc.attdmgmt.to.RestAttdTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RestController
@RequestMapping("/attdmgmt/*")
public class ExcusedAttendanceController {
	
	@Autowired
	private AttdMgmtService attdMgmtService;	
	
	@PostMapping("/excused-attnd")
	public ResultTO registRestAttd(@RequestBody RestAttdTO restAttd) {		
		ResultTO resultTO = new ResultTO();
		try {
			attdMgmtService.registRestAttd(restAttd);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	
	@GetMapping("/excused-attnd")
	public ResultTO findRestAttdList(@RequestParam("empCode") String empCode,
									@RequestParam("startDate") String startDate,
									@RequestParam("endDate") String endDate,
									@RequestParam("code") String code) {
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<RestAttdTO> restAttdList = attdMgmtService.findRestAttdList(empCode, startDate, endDate, code);
			resultTO.setAttribute("restAttdList", restAttdList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		}catch (Exception dae) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

	@DeleteMapping("/excused-attnd")
	public ResultTO removeRestAttdList(@RequestBody ArrayList<RestAttdTO> restAttdList) {
		ResultTO resultTO = new ResultTO();
		try {
			attdMgmtService.removeRestAttdList(restAttdList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}

}
