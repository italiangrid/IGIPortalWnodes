package it.italiangrid.wnodes.controllers;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.wnodes.core.WnodesInfoService;
import it.italiangrid.wnodes.core.impl.WnodesInfoServiceCLIImpl;
import it.italiangrid.wnodes.model.VirtualMachineCreation;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.impl.UserServiceUtilImpl;

import java.util.List;

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
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

@Controller("addController")
@RequestMapping(value = "VIEW")
public class AddController {
	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RenderMapping(params = "myaction=showAddVirtualMachine")
	public String showAddVirtualMachine() {
		log.info("Add Controllor");

		return "addVirtualMachine";
	}

	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null)
			return userInfoService.findByUsername(user.getScreenName());
		return null;
	}

	@ModelAttribute("tags")
	public List<String> getTags(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		WnodesInfoService infoService;
		// if (user != null)
		infoService = new WnodesInfoServiceCLIImpl(Long.toString(user
				.getUserId()));
		return infoService.getTags();
	}

	@ModelAttribute("sizes")
	public List<String> getSizes(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		WnodesInfoService infoService;
		// if (user != null)
		infoService = new WnodesInfoServiceCLIImpl(Long.toString(user
				.getUserId()));
		return infoService.getSizes();
	}

	@ModelAttribute("vm")
	public VirtualMachineCreation getCommandObject() {
		return new VirtualMachineCreation();
	}

	@ModelAttribute("vos")
	public List<String> getActiveVO(RenderRequest request) {
		try {
			User user = PortalUtil.getUser(request);

			if (user != null) {
				log.info("Getting active proxies for user {}.",
						user.getUserId());
				UserServiceUtil proxyService = new UserServiceUtilImpl(
						user.getUserId());
				return proxyService.getActiveProxy();
			}

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
