package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.service.EmpInfoService;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpTO;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.FamilyInfoTO;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.LicenseInfoTO;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.WorkInfoTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/empinfomgmt/*")
@RestController
public class EmpDetailController {
	
	@Autowired
	private EmpInfoService empInfoService;
	
	@GetMapping("/empdetail/all")
	public ResultTO findAllEmployeeInfo(@RequestParam String empCode){
		
		ResultTO resultTO = new ResultTO();
		
		try{
			EmpTO empTO=empInfoService.findAllEmpInfo(empCode);
			ArrayList<WorkInfoTO> workInfoTO = empTO.getWorkInfo();
			ArrayList<LicenseInfoTO> licenseInfoTO = empTO.getLicenseInfoList();			
			ArrayList<FamilyInfoTO> familyInfoTO = empTO.getFamilyInfoList();
			
			resultTO.setAttribute("empBean", empTO);
			resultTO.setAttribute("emptyFamilyInfoBean",familyInfoTO );
			resultTO.setAttribute("emptyLicenseInfoBean", licenseInfoTO);
			resultTO.setAttribute("emptyWorkInfoBean", workInfoTO);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");

		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	@PutMapping("/empdetail/empcode")
	public ResultTO modifyEmployee(@RequestBody EmpTO emp){
		
		ResultTO resultTO = new ResultTO();
		
		try{
			empInfoService.modifyEmployee(emp);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");

		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		return resultTO;
	}
	
	@DeleteMapping("/empdetail/empcode")
	public ResultTO removeEmployeeList(@RequestBody ArrayList<EmpTO> empList){
		
		ResultTO resultTO = new ResultTO();
		
		try{ 			
			empInfoService.deleteEmpList(empList);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");

		} catch (Exception dae){
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}

		return resultTO;
	}
}
