package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LicenseInfoTO extends BaseTO{
	
	private String empCode, licenseCode,licenseName, getDate, expireDate,
				   licenseLevel, licenseCenter, issueNumber;

}
