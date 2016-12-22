package com.app.config;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.api.SpartanLoggerException;

public class SpartanLogManagerLoader implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		try {
			StringBuffer relativePath = new StringBuffer(context.getServletContext().getRealPath("/WEB-INF/conf/")).append("/");
			initLogger(relativePath.toString());
		} catch (SpartanLoggerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * initialize the spartan logger with given configuration path
	 * 
	 * @throws SpartanLoggerException
	 */
	private void initLogger(final String path) throws SpartanLoggerException {
		LogManagerFactory.createLogManager(path, AppLoggerConstant.LOGGER_ID);
	}

	/** graceful shutdown of logger */
	public void shutdown() {
		LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID).out.shutdown();
	}

}
