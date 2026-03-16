package kr.co.seoulit.insa.attdsvc.attdappvl.controller;

import java.util.ArrayList;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import kr.co.seoulit.insa.attdsvc.attdappvl.service.AttdAppvlService;
import kr.co.seoulit.insa.attdsvc.attdappvl.to.AnnualLeaveMgtTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RestController
@RequestMapping("/attdappvl/*")
public class AnnualLeaveMgtController {
	
   @Autowired
   private AttdAppvlService attdAppvlService;
   
   @GetMapping("/annual-leaveMgt")
   public ResultTO findAnnualVacationMgtList(@RequestParam("applyYearMonth") String applyYearMonth){
	  ResultTO resultTO = new ResultTO();
      try {
         ArrayList<AnnualLeaveMgtTO> annualVacationMgtList = attdAppvlService.findAnnualVacationMgtList(applyYearMonth);
         resultTO.setAttribute("annualVacationMgtList", annualVacationMgtList);
         resultTO.setErrorCode("0");
         resultTO.setErrorMsg("success");
      } catch (Exception dae){
    	  resultTO.setErrorCode("-1");
    	  resultTO.setErrorMsg(dae.getMessage());
      }
      return resultTO;
   }
   
   
   @PutMapping("/annual-leaveMgt/1")
   public ResultTO modifyAnnualVacationMgtList(@RequestBody ArrayList<AnnualLeaveMgtTO> annualVacationMgtList){
	   ResultTO resultTO = new ResultTO();
      try {
         attdAppvlService.modifyAnnualVacationMgtList(annualVacationMgtList);
         resultTO.setErrorCode("0");
         resultTO.setErrorMsg("success");
      }catch (Exception dae){
    	  resultTO.setErrorCode("-1");
    	  resultTO.setErrorMsg(dae.getMessage());
      }
      return resultTO;
   } 
   
   
   @PutMapping("/annual-leaveMgt/2")
   public ResultTO cancelAnnualVacationMgtList(@RequestBody ArrayList<AnnualLeaveMgtTO> annualVacationMgtList){
	   ResultTO resultTO = new ResultTO();
      try {
         attdAppvlService.cancelAnnualVacationMgtList(annualVacationMgtList);
         resultTO.setErrorCode("0");
         resultTO.setErrorMsg("success");
      }catch (Exception dae){
    	  resultTO.setErrorCode("-1");
    	  resultTO.setErrorMsg(dae.getMessage());
      }
      return resultTO;
   } 
   
}