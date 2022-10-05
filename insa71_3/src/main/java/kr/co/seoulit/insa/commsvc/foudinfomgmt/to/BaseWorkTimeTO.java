package kr.co.seoulit.insa.commsvc.foudinfomgmt.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BaseWorkTimeTO extends BaseTO {
	
  String applyYear, attendTime, quitTime, lunchStartTime, lunchEndTime, dinnerStartTime,
      dinnerEndTime, overEndTime, nightEndTime;

}
