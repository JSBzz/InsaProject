package kr.co.seoulit.insa.attdsvc.attdappvl.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AnnualLeaveMgtTO extends BaseTO{
	
	private String empCode, empName, applyYearMonth, afternoonOff,
	monthlyLeave, remainingHoliday, finalizeStatus;
	
}
