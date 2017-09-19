package ua.com.itproekt.gup.server.servlet;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.net.URL;

public class App extends HttpServlet {
	private static final long  serialVersionUID = 199465984923187558L;
	private static String LOG4J_CONFIG_FILE_XML = "log4j.xml";
	private static Logger                   log = Logger.getLogger(App.class);

	public void init(ServletConfig config) throws ServletException {
		URL url = Loader.getResource(LOG4J_CONFIG_FILE_XML);
		DOMConfigurator.configure(url);
		log.info("Initialized log4j");
		super.init(config);
	}
}
