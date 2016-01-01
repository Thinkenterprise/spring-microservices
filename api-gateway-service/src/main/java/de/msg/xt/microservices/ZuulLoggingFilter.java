package de.msg.xt.microservices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.zuul.ZuulFilter;

public class ZuulLoggingFilter extends ZuulFilter{
	
	protected static Log log = LogFactory.getLog(ZuulLoggingFilter.class);
	

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.debug("Logging from Zuul Filter");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
