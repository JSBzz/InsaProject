package kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to;


import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FullTimeSalTO extends BaseTO {
	
	private String
	empCode,
	applyYearMonth,
	basicSalary,
	positionSalary,
	familySalary,
	foodSalary,
	overWorkSalary,
	nationalPension,
	healthInsurance,
	longTermInsurance,
	employmentInsurance,
	religionDonation,
	incomeTax,
	residentTax,
	finalizeStatus,
	basicSalBefore;
	
}
	