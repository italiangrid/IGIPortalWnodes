/**
 * 
 */
package it.italiangrid.wnodes.controllers;

import it.italiangrid.wnodes.model.KeyPair;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.impl.UserServiceUtilImpl;

import javax.portlet.RenderRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

/**
 * @author dmichelotto
 * 
 */
@Controller("keyManagementController")
@RequestMapping(value = "VIEW")
public class KeyManagementController {

	private static final Logger log = Logger
			.getLogger(KeyManagementController.class);
	
	/**
	 * Render mapping of the page with the form for creating new virtual
	 * machine.
	 * 
	 * @return The page addVirtualMachine.
	 */
	@RenderMapping(params = "myaction=showKeyManagement")
	public String showAddVirtualMachine() {
		log.info("Add Controllor");

		return "keyManagement";
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
			return service.sshKeysExist();
		}
		return false;
	}
	
	@ModelAttribute("keyPair")
	public KeyPair getKeyPair(){
		return new KeyPair();
	}
}
