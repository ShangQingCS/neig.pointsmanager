package cn.sqhl.neig.pointsmanager.controller.web;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class basicInfo {
	private Logger logger = LogManager.getLogger(basicInfo.class);
	public static final Level ERROR = Level.ERROR;
	public static final Level INFO = Level.INFO;
	public static final Level DEBUG = Level.DEBUG;

	@Value("${ad.type}")
	protected String adtype;
    
}
