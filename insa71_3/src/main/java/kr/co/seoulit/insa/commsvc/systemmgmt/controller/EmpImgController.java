package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.sys.util.EmpImgUploadUtil;


@RequestMapping("/systemmgmt/*")
@RestController
public class EmpImgController {
	
	@Autowired
	private SystemMgmtService systemMgmtService;
	
	@PostMapping("empImg")
	public ResultTO handleRequestInternal(HttpServletRequest request, 
                                         @RequestParam String empCode, 
                                         @RequestParam(required=false) String newcheck,
                                         @RequestParam("uploadFile") MultipartFile file) {
		
		ResultTO result = new ResultTO();
        String empImgUrl = null;
        String check = newcheck;
        if (check == null) {
            check = (String)request.getSession().getAttribute("newcheck");
        }

        int newCheck = 0;
        try {
            if (file != null && !file.isEmpty()) {
                empImgUrl = EmpImgUploadUtil.doFileUpload(request, file, empCode);
            }
	        
	        if("1".equals(check)) {
	        	newCheck = 1;
	        }

	        if(newCheck == 0 && empImgUrl != null) {
	        	systemMgmtService.registEmpImg(empCode, empImgUrl.substring(empImgUrl.lastIndexOf(".")+1));
	        }
	        
	        result.setAttribute("empImgUrl", empImgUrl);
	        result.setErrorCode("0");
	        result.setErrorMsg("사진 저장에 성공했습니다");
        } catch (IOException e){
        	result.setErrorCode("-1");
        	result.setErrorMsg("사진 저장에 실패했습니다: " + e.getMessage());
        } catch (Exception e) {
            result.setErrorCode("-1");
            result.setErrorMsg(e.getMessage());
        }

		return result;
	}

}
