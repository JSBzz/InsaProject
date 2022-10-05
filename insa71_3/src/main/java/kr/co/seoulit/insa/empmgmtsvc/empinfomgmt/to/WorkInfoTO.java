package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WorkInfoTO extends BaseTO{
	
	private String empCode, workInfoDays, hiredate, retireDate, 
			       occupation, employmentType, hobong, position, deptName;

}
