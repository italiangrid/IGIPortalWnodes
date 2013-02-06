package it.italiangrid.wnodes.controllers;

import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.core.impl.WnodesServiceCLIImpl;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * Controller class for the operations that user can be run from the WNoDeS
 * portlet.
 * 
 * @author dmichelotto
 * 
 */

@Controller("actionController")
@RequestMapping(value = "VIEW")
public class ActionsController {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Action mapping for the action "addVirtualMachine": create a new virtual
	 * machine.
	 * 
	 * @param vm
	 *            - The istance of VirtualMachineCreation that contain the
	 *            informations for creating a new virtual machine.
	 * @param request
	 *            - The HTTP request.
	 * @param response
	 *            - The HTTP response.
	 * @param status
	 *            - The HTTP status.
	 */
	@ActionMapping(params = "myaction=addVirtualMachine")
	public void addVirtualMachine(@ModelAttribute VirtualMachineCreation vm,
			ActionRequest request, ActionResponse response, SessionStatus status) {
		WnodesService service = new WnodesServiceCLIImpl();

		try {
			User user = PortalUtil.getUser(request);
			log.error("Qtà= "+vm.getQta());
			for(int i=0; i<vm.getQta() ; i++ ){
				vm.setSize(vm.getSize().split("#")[0]);
				
				String uuid = service.createVirtualMachines(user.getUserId(), vm);
	
				if (uuid != null) {
					if (uuid.contains("https://test-wnodes-web01.cnaf.infn.it:8443/compute/")) {
						log.error("Virtual machines {}. created for user {}.", uuid,
								user.getUserId());
						SessionMessages.add(request, "vm-created");
						response.setRenderParameter("myaction", "showList");
						return;
						
					} else {
						SessionErrors.add(request, "vo-not-supported");
					}
				}else{
					log.info("Virtual machines not created for user {}.",
							user.getUserId());
					SessionErrors.add(request, "vm-not-created");
				}
			}

		} catch (PortalException e) {
			log.info("Portal exception: {}.", e.getMessage());
			SessionErrors.add(request, "portal-exception");
			e.printStackTrace();
		} catch (SystemException e) {
			log.info("System exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		}
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		response.setRenderParameter("myaction", "showAddVirtualMachine");
		return;
	}

	/**
	 * Action mapping for the action "deleteVirtualMachine": delete a virtual
	 * machine.
	 * 
	 * @param uuid
	 *            - The identifier of the virtual machine that the user want to
	 *            delete.
	 * @param request
	 *            - The HTTP request.
	 * @param response
	 *            - The HTTP response.
	 * @param status
	 *            - The HTTP status.
	 */
	@ActionMapping(params = "myaction=deleteVirtualMachine")
	public void removeVirtualMachine(@RequestParam String uuid,
			ActionRequest request, ActionResponse response, SessionStatus status) {
		WnodesService service = new WnodesServiceCLIImpl();

		log.info(uuid);
		
		try {
			User user = PortalUtil.getUser(request);

			if (service.deleteVirtualMachines(user.getUserId(), uuid)) {
				log.info("Deleted Virtual Machine {}. of the user {}.",
						user.getUserId(), uuid);
				SessionMessages.add(request, "vm-deleted");
			} else {
				SessionErrors.add(request, "vm-not-deleted");

			}
		} catch (PortalException e) {
			log.info("Portal exception: {}.", e.getMessage());
			SessionErrors.add(request, "portal-exception");
			e.printStackTrace();
		} catch (SystemException e) {
			log.info("System exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		}
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		response.setRenderParameter("myaction", "showList");
	}

	/**
	 * Action mapping for the action "deleteMultipleVirtualMachine": delete a
	 * list of virtual machine.
	 * 
	 * @param vmToDel
	 *            - The list of the virtual machine identifier that the user
	 *            want to delete.
	 * @param request
	 *            - The HTTP request.
	 * @param response
	 *            - The HTTP response.
	 * @param status
	 *            - The HTTP status.
	 */
	@ActionMapping(params = "myaction=deleteMultipleVirtualMachine")
	public void removemultipleVirtualMachine(@RequestParam String[] vmToDel,
			ActionRequest request, ActionResponse response, SessionStatus status) {
		WnodesService service = new WnodesServiceCLIImpl();

		try {
			User user = PortalUtil.getUser(request);
			for (String uuid : vmToDel) {

				log.info("VM to delete: {}.", uuid);
				if (service.deleteVirtualMachines(user.getUserId(), uuid)) {
					log.info("Deleted Virtual Machine {} of the user {}.",
							user.getUserId(), uuid);
					SessionMessages.add(request, "vm-deleted");
				} else {
					log.info("VM not deleted: {}.", uuid);
					SessionErrors.add(request, "vm-not-deleted");

				}
			}
		} catch (PortalException e) {
			log.info("Portal exception: {}.", e.getMessage());
			SessionErrors.add(request, "portal-exception");
			e.printStackTrace();
		} catch (SystemException e) {
			log.info("System exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		}
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		response.setRenderParameter("myaction", "showList");
	}

}
