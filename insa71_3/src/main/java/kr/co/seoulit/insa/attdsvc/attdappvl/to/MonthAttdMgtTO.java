package kr.co.seoulit.insa.attdsvc.attdappvl.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MonthAttdMgtTO extends BaseTO{
	
	private String empCode, empName, applyYearMonth ,basicWorkDays
	,weekdayWorkDays ,basicWorkHour ,workHour
	,overWorkHour ,nightWorkHour ,holidayWorkDays,earlyLeaveDays
	,holidayWorkHour ,lateDays ,absentDays ,halfHolidays ,Holidays ,finalizeStatus;

}
