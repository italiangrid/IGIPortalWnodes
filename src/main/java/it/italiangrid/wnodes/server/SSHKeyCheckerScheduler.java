package it.italiangrid.wnodes.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SSHKeyCheckerScheduler implements ServletContextListener {
	
	private ScheduledExecutorService scheduler;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// Start thread
		scheduler.shutdownNow();
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// Destroy thread
		scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new SSHKeyCheck(), 0, 5, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new SSHKeyCheck(), 0, 10, TimeUnit.SECONDS);
		
	}

}
