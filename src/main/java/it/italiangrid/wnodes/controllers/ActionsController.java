package it.italiangrid.wnodes.controllers;

import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.core.impl.WnodesServiceListImpl;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;


@Controller("actionController")
@RequestMapping(value = "VIEW")
public class ActionsController {
	
	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);
	
	@ActionMapping(params = "myaction=addVirtualMachine")
	public void addVirtualMachine(@ModelAttribute VirtualMachineCreation vm,
			ActionRequest request, ActionResponse response, SessionStatus status) {
		WnodesService service = new WnodesServiceListImpl();

		try {
			User user = PortalUtil.getUser(request);
			String uuid = service.createVirtualMachines(user.getUserId(), vm);

			if (uuid != null) {
				log.info("Virtual machines {}. created for user {}.", uuid,
						user.getUserId());
				SessionMessages.add(request, "vm-created");
				response.setRenderParameter("myaction", "showList");
				status.setComplete();
				return;
			} else {
				log.info("Virtual machines not created for user {}.",
						user.getUserId());
				SessionErrors.add(request, "vm-not-created");
			}

		} catch (PortalException e) {
			log.info("Portal exception: {}.", e.getMessage());
			SessionErrors.add(request, "portal-exception");
			e.printStackTrace();
		} catch (SystemException e) {
			log.info("System exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		} catch (Exception e){
			log.info("Exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		}

		response.setRenderParameter("myaction", "showAddVirtualMachine");
		return;
	}
	
	@ActionMapping(params = "myaction=deleteVirtualMachine")
	public void removeVirtualMachine(@RequestParam String uuid, ActionRequest request,
			ActionResponse response, SessionStatus status) {
		WnodesService service = new WnodesServiceListImpl();
		
		try {
			User user = PortalUtil.getUser(request);
			
			if(service.deleteVirtualMachines(user.getUserId(), uuid)){
				log.info("Deleted Virtual Machine {}. of the user {}.",user.getUserId(),uuid);
				SessionMessages.add(request, "vm-deleted");
			}else{
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
		} catch (Exception e){
			log.info("Exception: {}.", e.getMessage());
			SessionErrors.add(request, "system-exception");
			e.printStackTrace();
		}
		
		response.setRenderParameter("myaction", "showList");
	}


}
