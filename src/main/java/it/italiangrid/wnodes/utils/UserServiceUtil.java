package it.italiangrid.wnodes.utils;

import java.util.List;

public interface UserServiceUtil {
	
	public List<String> getActiveProxy();
	
	public boolean createSshKey();

}
