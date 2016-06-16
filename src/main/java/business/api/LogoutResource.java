package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.LogoutController;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.LOGOUT)
public class LogoutResource {
	
	private LogoutController logoutController;
	
	@Autowired
	public void setLogoutController(LogoutController logoutController) {
		this.logoutController = logoutController;
	}
	
	@RequestMapping(value = Uris.TOKEN_VALUE, method = RequestMethod.POST)
	public void logout(@PathVariable String tokenValue) {
		logoutController.logout(tokenValue);
	}

}
