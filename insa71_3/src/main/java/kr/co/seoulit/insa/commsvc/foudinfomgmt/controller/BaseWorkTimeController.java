package kr.co.seoulit.insa.commsvc.foudinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.co.seoulit.insa.commsvc.foudinfomgmt.service.FoudInfoMgmtService;
import kr.co.seoulit.insa.commsvc.foudinfomgmt.to.BaseWorkTimeTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import java.util.List;

@RequestMapping("/foudinfomgmt/*")
@RestController
public class BaseWorkTimeController {

	@Autowired
	private FoudInfoMgmtService foudInfoMgmtService;

	@GetMapping("basetime")
	public ResultTO findTimeList() {
		ResultTO result = new ResultTO();
		try {
			ArrayList<BaseWorkTimeTO> list = foudInfoMgmtService.findTimeList();
			BaseWorkTimeTO emptyBean = new BaseWorkTimeTO();
			result.setAttribute("emptyBean", emptyBean);
			result.setAttribute("list", list);
			result.setErrorCode("0");
			result.setErrorMsg("success");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	
	@PutMapping("basetime")
	public ResultTO batchTimeProcess(@RequestBody Map<String, Object> payload) {
		ResultTO result = new ResultTO();
		try {
			Gson gson = new Gson();
			String sendData = gson.toJson(payload.get("sendData"));
			String applyYear = (String) payload.get("applyYear");
			
			ArrayList<BaseWorkTimeTO> timeList = gson.fromJson(sendData, new TypeToken<ArrayList<BaseWorkTimeTO>>() {}.getType());

			foudInfoMgmtService.batchTimeProcess(timeList);
			result.setErrorCode("0");
			result.setErrorMsg(applyYear + "년도 기준근무시간이 등록/삭제가 완료되었습니다.");
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}

	
}
