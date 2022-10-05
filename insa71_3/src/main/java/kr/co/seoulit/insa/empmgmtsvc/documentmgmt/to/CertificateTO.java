package kr.co.seoulit.insa.empmgmtsvc.documentmgmt.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CertificateTO extends BaseTO {
	
	public String empCode, empName, deptName, requestDate, useDate, usageCode, 
	usageName, etc, approvalStatus, rejectCause;	
	
}
