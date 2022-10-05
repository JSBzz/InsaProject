package kr.co.seoulit.insa.salarysvc.salaryinfomgmt.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SalaryBonusTO {
	
	private String empCode , empName , deptCode , position, baseSalary, awardsSalary, grade;
	
}
