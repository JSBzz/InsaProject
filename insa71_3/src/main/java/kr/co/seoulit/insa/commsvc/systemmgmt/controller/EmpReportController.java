package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ReportSalaryTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ReportTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RequestMapping("/systemmgmt/*")
@RestController
public class EmpReportController {

	@Autowired
	private SystemMgmtService systemMgmtService;
	
	@GetMapping("empreport")
	public ResultTO requestEmployment(@RequestParam String empCode,
									 @RequestParam String usage,
									 @RequestParam String requestDay,
									 @RequestParam String useDay,
									 HttpServletRequest request,
									 HttpServletResponse response) { // 재직증명서 신청
		ResultTO resultTO = new ResultTO();
		
		try {

			ReportTO to = systemMgmtService.viewReport(empCode);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(("C:\\dev\\nginx\\nginx-1.20.2\\html\\report\\employment.jrxml"));
			System.out.println("소명"+request.getServletContext().getRealPath("/report/employment.jrxml"));

			JRDataSource datasource = new JREmptyDataSource();

			Map<String, Object> map = new HashMap<>();
			map.put("empName", to.getEmpName());
			map.put("hiredate", to.getHiredate());
			map.put("occupation", to.getOccupation());
			map.put("employmentType", to.getEmploymentType());
			map.put("position", to.getPosition());
			map.put("address", to.getAddress());
			map.put("detailAddress", to.getDetailAddress());
			map.put("deptName", to.getDeptName());
			map.put("usage", usage);
			map.put("date", requestDay);
			map.put("end", useDay);
			ServletOutputStream outputStream = null;

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, datasource);

			outputStream = response.getOutputStream(); 
			response.setContentType("application/pdf"); 

			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			outputStream.flush();
		} catch (Exception e) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(e.getMessage());
		}
		return resultTO;
	}

	@GetMapping("salaryreport")
	public ResultTO requestMonthSalary(@RequestParam String empCode,
									  @RequestParam String applyMonth,
									  HttpServletResponse response) { // 월급여신청
		ResultTO resultTO = new ResultTO();

		try {
			ReportSalaryTO to = systemMgmtService.viewSalaryReport(empCode, applyMonth);
			Map<String, Object> map = new HashMap<String, Object>();

			JasperReport jasperReport = JasperCompileManager
					.compileReport(("C:\\dev\\nginx\\nginx-1.20.2\\html\\report\\employment.jrxml"));

			JRDataSource datasource = new JREmptyDataSource();

			map.put("empName", to.getEmpName());
			map.put("position", to.getPosition());
			map.put("deptName", to.getDeptName());
			map.put("hiredate", to.getHiredate());
			map.put("applyYearMonth", to.getApplyYearMonth());
			map.put("totalExtSal", to.getTotalExtSal());
			map.put("totalDeduction", to.getTotalDeduction());
			map.put("totalPayment", to.getTotalPayment());
			map.put("realSalary", to.getRealSalary());
			map.put("salary", to.getSalary());
			map.put("cost", to.getCost());
			map.put("healthIns", to.getHealthIns());
			map.put("goyongIns", to.getGoyongIns());
			map.put("janggiIns", to.getJanggiIns());
			map.put("gukmin", to.getGukmin());

			OutputStream outputStream = null;

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, datasource); 
			
			outputStream = response.getOutputStream(); 
			response.setContentType("application/pdf"); 
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			outputStream.flush();

		} catch (Exception e) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(e.getMessage());
		}
		return resultTO;
	}
	
}
