package it.italiangrid.wnodes.controllers;

import it.italiangrid.wnodes.model.KeyPair;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.impl.UserServiceUtilImpl;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@Controller("uploadController")
@RequestMapping(value = "VIEW")
public class UploadController {
	
	private static final Logger log = Logger.getLogger(UploadController.class);
	
	/**
	 * Upload the user's ssh key pair.
	 * @param request
	 * @param response
	 */
	@ActionMapping(params = "myaction=uploadKeyPair")
	public void uploadKeyPair(@ModelAttribute KeyPair keyPair, BindingResult bndingResult, ActionRequest request, ActionResponse response) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			UserServiceUtil service = new UserServiceUtilImpl(user.getUserId());
//			MultipartFile keyMultipartFile = request.getFile("key");
//		    MultipartFile keyPubMultipartFile = request.getFile("keyPub");
		    
			if(keyPair.getPrivateKey()==null)
				log.error("private key null");
			if(keyPair.getPublicKey()==null)
				log.error("public key null");
			if(keyPair.getPrivateKey()!=null && keyPair.getPublicKey()!=null){
				
			    try {
					service.storeKeys(keyPair.getPrivateKey(), keyPair.getPublicKey());
					SessionMessages.add(request, "keys-uploaded-successfully");
				} catch (IllegalStateException e) {
					SessionErrors.add(request, "system-exception");
					e.printStackTrace();
				} catch (IOException e) {
					SessionErrors.add(request, "keys-not-uploaded");
					e.printStackTrace();
				}
			}else{
				SessionErrors.add(request, "keys-null");
			}
		}
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		response.setRenderParameter("myaction", "showKeyManagement");
	}
	
	@ActionMapping(params = "myaction=destroyKeyPair")
	public void destroyKeypair(ActionRequest request, ActionResponse response){
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			UserServiceUtil service = new UserServiceUtilImpl(user.getUserId());
			service.destroyKeyPair();
			SessionMessages.add(request, "keys-deleted-successfully");
			response.setRenderParameter("myaction", "showKeyManagement");
		}
	}
	
}
