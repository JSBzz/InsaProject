package kr.co.seoulit.insa.empmgmtsvc.documentmgmt.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class proofTO extends BaseTO {
	
	private String empName, empCode, proofTypeCode, proofTypeName, startDate,
	               position, dept,cash, cause, receipt, applovalStatus;

}
