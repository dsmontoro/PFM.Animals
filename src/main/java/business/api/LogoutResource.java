package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.LogoutController;
import business.wrapper.TokenWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.LOGOUT)
public class LogoutResource {
	
	private LogoutController logoutController;
	
	@Autowired
	public void setLogoutController(LogoutController logoutController) {
		this.logoutController = logoutController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void logout(@RequestBody TokenWrapper tokenWrapper) {
		logoutController.logout(tokenWrapper.getToken());
	}

}
