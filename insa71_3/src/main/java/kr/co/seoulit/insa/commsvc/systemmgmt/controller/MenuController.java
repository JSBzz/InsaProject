package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.MenuTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;

@RequestMapping("/systemmgmt/*")
@RestController
public class MenuController {
	
	@Autowired
	private SystemMgmtService systemMgmtService;
	
	@GetMapping("/menulist")
	public ResultTO findMenuList() {
		ResultTO resultTO = new ResultTO();
		try {

			ArrayList<MenuTO> menuList = systemMgmtService.findMenuList();
			ArrayList<MenuTO> navbarList = new ArrayList<>();

			for (MenuTO menuBean : menuList) {
				if (menuBean.getNavbar_name() != null) {
					navbarList.add(menuBean);
				}
			}
			resultTO.setAttribute("menuList", menuList);
			resultTO.setAttribute("navbarList", navbarList);
			resultTO.setErrorMsg("success");
			resultTO.setErrorCode("0");

		} catch (Exception dae) {
			resultTO.setErrorCode("-1");
			resultTO.setErrorMsg(dae.getMessage());
		}
		
		return resultTO;
	}
}
