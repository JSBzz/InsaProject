package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EmpEvalTO {
	
	private String empCode,empName,deptName,position,approval_Status,apply_day,grade;
	private int achievement,ability,attitude;
	
}
