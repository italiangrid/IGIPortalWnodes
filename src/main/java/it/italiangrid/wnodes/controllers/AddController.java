package it.italiangrid.wnodes.controllers;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.wnodes.core.WnodesInfoService;
import it.italiangrid.wnodes.core.impl.WnodesInfoServiceCLIImpl;
import it.italiangrid.wnodes.exception.WnodesPortletException;
import it.italiangrid.wnodes.model.MarketPlace;
import it.italiangrid.wnodes.model.VirtualMachineCreation;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.WnodesConfig;
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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * Controller class for the add virtual machine page of the WNoDeS portlet.
 * 
 * Return to the page the appropriate tag and size values retrieved from WNoDeS.
 * 
 * @author dmichelotto
 * 
 */

@Controller("addController")
@RequestMapping(value = "VIEW")
public class AddController {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * UserInfo Service.
	 */
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * Render mapping of the page with the form for creating new virtual
	 * machine.
	 * 
	 * @return The page addVirtualMachine.
	 */
	@RenderMapping(params = "myaction=showAddVirtualMachine")
	public String showAddVirtualMachine() {
		log.info("Add Controllor");

		return "addVirtualMachine";
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
		if (user != null)
			return userInfoService.findByUsername(user.getScreenName());
		return null;
	}

//	/**
//	 * Attribute mapping for the "tags" variable displayed in the page.
//	 * 
//	 * @param request
//	 *            - The HTTP request.
//	 * @return Return the list of the tags supported by WNoDes.
//	 */
//	@ModelAttribute("tags")
//	public List<String> getTags(RenderRequest request) {
//		User user = (User) request.getAttribute(WebKeys.USER);
//		WnodesInfoService infoService;
//		// if (user != null)
//		infoService = new WnodesInfoServiceCLIImpl(Long.toString(user
//				.getUserId()));
//		return infoService.getTags();
//	}
//
//	/**
//	 * Attribute mapping for the "sizes" variable displayed in the page.
//	 * 
//	 * @param request
//	 *            - The HTTP request.
//	 * @return Return the list of the sizes supported by WNoDes.
//	 */
//	@ModelAttribute("sizes")
//	public List<String> getSizes(RenderRequest request) {
//		User user = (User) request.getAttribute(WebKeys.USER);
//		WnodesInfoService infoService;
//		// if (user != null)
//		infoService = new WnodesInfoServiceCLIImpl(Long.toString(user
//				.getUserId()));
//		return infoService.getSizes();
//	}
	
	/**
	 * Attribute mapping for the "sizes" variable displayed in the page.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return the list of the sizes supported by WNoDes.
	 */
	@ModelAttribute("marketPlace")
	public MarketPlace getMarketPlace(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		WnodesInfoService infoService;
		// if (user != null)
		infoService = new WnodesInfoServiceCLIImpl(Long.toString(user
				.getUserId()));
		try {
			return infoService.getMarketPlaces();
		} catch (WnodesPortletException e) {
			SessionErrors.add(request, e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Attribute mapping for the "vm" variable displayed in the page.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return a new instance of the class VirtualMachineCreation.
	 */
	@ModelAttribute("vm")
	public VirtualMachineCreation getCommandObject() {
		return new VirtualMachineCreation();
	}

	/**
	 * Attribute mapping for the "vos" variable displayed in the page.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return the list of the proxies downloaded by the user.
	 */
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
	
	@ModelAttribute("cloudVo")
	public String getCloudVo(){
		try {
			return WnodesConfig.getProperties("cloud.vo");
		} catch (WnodesPortletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
