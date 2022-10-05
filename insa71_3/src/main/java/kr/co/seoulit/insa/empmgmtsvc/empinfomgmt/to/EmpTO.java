package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to;


import java.util.ArrayList;

import kr.co.seoulit.insa.commsvc.systemmgmt.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class EmpTO extends BaseTO{
	
   private String empCode, empName, birthdate, gender, mobileNumber, address,
                  detailAddress, postNumber, email, lastSchool, imgExtend, position, deptName,
                  hobong, occupation, employment, authority, hiredate, achievement, ability, attitude;
   
   ArrayList<FamilyInfoTO> familyInfoList;
   ArrayList<LicenseInfoTO> licenseInfoList;
   ArrayList<WorkInfoTO> workInfo;
   
   public String getImgExtend() {
	      return "jpg";
   }
   
}