package it.italiangrid.wnodes.controllers;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.core.impl.WnodesServiceCLIImpl;
import it.italiangrid.wnodes.model.VirtualMachine;
import javax.portlet.RenderRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

/**
 * Controller class for the specific virtual machine shell page of the WNoDeS
 * portlet.
 * 
 * Return to the page the virtual machine information retrieved from WNoDeS.
 * 
 * @author dmichelotto
 * 
 */

@Controller("shellController")
@RequestMapping(value = "VIEW")
public class ShellController {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ShellController.class);

	/**
	 * UserInfo Service.
	 */
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * Render mapping of the host's shell page.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return the page "myshell";
	 */
	@RenderMapping(params = "myaction=viewVirtualMachine")
	public String showHomePage(RenderRequest request) {
		log.info("Shell Controller");

		return "myshell";
	}

	/**
	 * Attribute mapping for the "userInfo" variable displayed in the page.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return the userInfo object of the logged user.
	 */
	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			return userInfoService.findByUsername(user.getScreenName());
		}
		return null;
	}

	/**
	 * Attribute mapping for the "host" variable displayed in the page.
	 * 
	 * @param uuid
	 *            - The virtual machine identifier
	 * @param request
	 *            - The HTTP request.
	 * @return Return the hostname of the virtual machine specified.
	 */
	@ModelAttribute("host")
	public String getHostname(@RequestParam String uuid, RenderRequest request) {
		log.info("Getting specific virtual machines of the user");
		User user = (User) request.getAttribute(WebKeys.USER);
		long userId;

		if (user != null)
			userId = user.getUserId();
		else
			return null;
		WnodesService wnodesService = new WnodesServiceCLIImpl();
		VirtualMachine vm = wnodesService.getVirtualMachine(userId, uuid);
		return vm.getHostname();
	}

}
