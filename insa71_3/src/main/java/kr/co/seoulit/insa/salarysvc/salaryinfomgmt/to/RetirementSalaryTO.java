package kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RetirementSalaryTO extends BaseTO {
	
	private String
	position, 
	empname, 
	empcode, 
	hiredate, 
	settlementdate, 
	workingdate, 
	retirementsalary;

}
