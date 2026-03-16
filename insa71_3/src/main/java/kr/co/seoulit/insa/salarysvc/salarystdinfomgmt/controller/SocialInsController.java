package kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.service.SalaryStdInfoMgmtService;
import kr.co.seoulit.insa.salarysvc.salarystdinfomgmt.to.SocialInsTO;

@RequestMapping("/salarystdinfomgmt/*")
@RestController
public class SocialInsController {
	
	@Autowired
	private SalaryStdInfoMgmtService salaryStdInfoMgmtService;	
	
	@GetMapping("social")
	public ResultTO findBaseInsureList(@RequestParam("yearBox") String yearBox){
		ResultTO resultTO = new ResultTO();
		try {
			ArrayList<SocialInsTO> baseInsureList = salaryStdInfoMgmtService.findBaseInsureList(yearBox);
			SocialInsTO emptyBean = new SocialInsTO();
			emptyBean.setStatus("insert");                     

			resultTO.setAttribute("baseInsureList", baseInsureList); 
			resultTO.setAttribute("emptyBean", emptyBean);                 
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());	
		}
		return resultTO;
	}
	
	
	@PutMapping("social")
	public ResultTO updateInsureData(@RequestBody ArrayList<SocialInsTO> baseInsureList){		
		ResultTO resultTO = new ResultTO();
		try {		
			salaryStdInfoMgmtService.updateInsureData(baseInsureList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (DataAccessException dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	
	@DeleteMapping("social")
	public ResultTO deleteInsureData(@RequestBody ArrayList<SocialInsTO> baseInsureList){		
		ResultTO resultTO = new ResultTO();
		try {
			salaryStdInfoMgmtService.deleteInsureData(baseInsureList);
			resultTO.setErrorCode("0");
			resultTO.setErrorMsg("success");
		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	
}