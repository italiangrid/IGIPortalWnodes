package it.italiangrid.wnodes.controllers;

import java.util.List;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.SshKeysService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.core.impl.WnodesServiceCLIImpl;
import it.italiangrid.wnodes.exception.WnodesPortletException;
import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.WnodesConfig;
import it.italiangrid.wnodes.utils.impl.UserServiceUtilImpl;

import javax.portlet.PortletConfig;
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
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * Controller class for the first page of the WNoDeS portlet.
 * 
 * Check if user is logged in and have already downloaded proxy. If all is done
 * show the list of the his instantiated virtual machine
 * 
 * @author dmichelotto
 * 
 */

@Controller("homeController")
@RequestMapping(value = "VIEW")
public class HomeController {

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

	@Autowired
	private SshKeysService sshKeysService;
	/**
	 * Render Mapping of the first page displayed.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return home page if user is authenticated and have proxy,
	 *         otherwise return the appropriate error page.
	 */
	@RenderMapping
	public String showHomePage(RenderRequest request) {
		log.info("Home Controllor");

		try {
			User user = PortalUtil.getUser(request);

			if (user != null) {
				log.info("User logged in.");
				UserServiceUtil check = new UserServiceUtilImpl(
						user.getUserId());
				List<String> proxyList = check.getActiveProxy();
				for(String proxy: proxyList){
					
					if (proxy.equals(WnodesConfig.getProperties("cloud.vo"))) {
						log.info("User have proxy.");
						return "home";
					}
				}
				log.info("User haven't proxy.");
				return "error-proxy";
			}

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (WnodesPortletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info("User not logged in.");
		return "error";
	}

	/**
	 * Render mapping of the action "showList".
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return home page if user is authenticated and have proxy,
	 *         otherwise return the appropriate error page.
	 */
	@RenderMapping(params = "myaction=showList")
	public String showList(RenderRequest request) {
		return showHomePage(request);
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
//			UserServiceUtil service = new UserServiceUtilImpl(user.getUserId());
//			boolean status = service.createSshKey();
//			if (status)
//				SessionMessages.add(request, "added-ssh-key");
			return userInfoService.findByUsername(user.getScreenName());
		}
		return null;
	}
	
	/**
	 * Check if the key pair was already created/uploaded.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return true if the key pair was already created/uploaded, false otherwise.
	 */
	@ModelAttribute("keyPairExist")
	public boolean keyPairExist(RenderRequest request){
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			UserServiceUtil service = new UserServiceUtilImpl(user.getUserId());
			if(service.sshKeysExist()){
				return true;
			}else{
				if(service.sshKeyPubExistOnly()){
					//Dowload from myproxy
					
					//if myproxy ok return true, else print error and return false
					UserInfo userInfo = userInfoService.findByMail(user.getEmailAddress());
					boolean retrieveStatus = service.downloadKeys(sshKeysService, userInfo);
//					boolean retrieveStatus = service.downloadKeys(userInfo);
					if(retrieveStatus){
						return true;
					}
					SessionErrors.add(request, "myproxy-error");
					PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
					SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				}
			}
		}
		return false;
	}

	/**
	 * Attribute mapping for the "virtualMachines" variable displayed in the
	 * page.
	 * 
	 * @param request
	 *            - The HTTP request.
	 * @return Return the list of the virtual machines istantiated by the user.
	 */
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
		if(!check.sshKeysExist()){
			if(check.sshKeyPubExistOnly()){
				//Dowload from myproxy
				
				//if myproxy ok return true, else print error and return false
				UserInfo userInfo = userInfoService.findByMail(user.getEmailAddress());
				boolean retrieveStatus = check.downloadKeys(sshKeysService, userInfo);
//				boolean retrieveStatus = check.downloadKeys(userInfo);
				if(!retrieveStatus){
					SessionErrors.add(request, "myproxy-error");
					PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
					SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				}
			}else{
				return null;
			}
		}
			
		WnodesService wnodesService = new WnodesServiceCLIImpl();
		return wnodesService.getVirtualMachines(userId);

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
