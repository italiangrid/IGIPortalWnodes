/**
 * 
 */
package it.italiangrid.wnodes.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

/**
 * @author dmichelotto
 *
 */
@Controller("resourceController")
@RequestMapping(value = "VIEW")
public class ResourceController {
	
	@ResourceMapping("keyDownload") 
	public void keyDownload(ResourceRequest request, ResourceResponse response) throws IOException{
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			
			String key = System.getProperty("java.io.tmpdir") + "/users/" + user.getUserId() + "/id_rsa";
			try {
				download(key, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}
	
	@ResourceMapping("keyPubDownload") 
	public void keyPubDownload(ResourceRequest request, ResourceResponse response){
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			
			String key = System.getProperty("java.io.tmpdir") + "/users/" + user.getUserId() + "/id_rsa.pub";
			
			try {
				download(key, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	}

	private void download(String key, ResourceResponse response)  throws IOException{
		File keyFile = new File(key);
		byte[] fileBytes = new byte[(int)keyFile.length()];
		
		FileInputStream fileInputStream = new FileInputStream(keyFile);
		fileInputStream.read(fileBytes);
		fileInputStream.close();
		
		response.setContentType("application/xml");
		response.addProperty("Content-disposition", "atachment; filename="+keyFile.getName());
		
		OutputStream out = response.getPortletOutputStream();
		out.write(fileBytes);
		out.flush();
		out.close(); 
		
	}
}
