package it.italiangrid.wnodes.controllers;

import java.util.List;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.core.impl.WnodesServiceCLIImpl;
import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.impl.UserServiceUtilImpl;

import javax.portlet.RenderRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

@Controller("homeController")
@RequestMapping(value = "VIEW")
public class HomeController {

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RenderMapping
	public String showHomePage(RenderRequest request) {
		log.info("Home Controllor");

		try {
			User user = PortalUtil.getUser(request);

			if (user != null) {
				log.info("User logged in.");
				UserServiceUtil check = new UserServiceUtilImpl(
						user.getUserId());
				if (check.getActiveProxy().isEmpty()) {
					log.info("User haven't proxy.");
					return "error-proxy";
				}
				log.info("User have proxy.");
				return "home";
			}

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}

		log.info("User not logged in.");
		return "error";
	}

	@RenderMapping(params = "myaction=showList")
	public String showList(RenderRequest request) {
		return showHomePage(request);
	}

	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			UserServiceUtil service = new UserServiceUtilImpl(user.getUserId());
			boolean status = service.createSshKey();
			if (status)
				SessionMessages.add(request, "added-ssh-key");
			return userInfoService.findByUsername(user.getScreenName());
		}
		return null;
	}

	@ModelAttribute("virtualMachines")
	public List<VirtualMachine> getVirtualMachines(RenderRequest request) {
		log.info("Getting all virtual machines of the user");
		User user = (User) request.getAttribute(WebKeys.USER);
		long userId;

		if (user != null)
			userId = user.getUserId();
		else
			return null;
		UserServiceUtil check = new UserServiceUtilImpl(user.getUserId());
		if (check.getActiveProxy().isEmpty()) {
			log.info("User haven't proxy.");
			return null;
		}
		WnodesService wnodesService = new WnodesServiceCLIImpl();
		return wnodesService.getVirtualMachines(userId);

	}

}
