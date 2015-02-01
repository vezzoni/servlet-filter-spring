package vezzoni.servlet.filter.spring.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import vezzoni.servlet.filter.spring.services.GreetingsService;

@Component
public class GreetingsFilter implements Filter {

    @Autowired
    private GreetingsService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        stdout("initializing...");

        this.configureSpringAwareness(filterConfig);

        stdout("done.");
    }

	private void configureSpringAwareness(FilterConfig filterConfig) throws IllegalStateException, BeansException {

		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();

		factory.configureBean(this, GreetingsService.COMP_NAME);
	}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        stdout("filtering...");

        stdout(this.service.sayHello());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        stdout("destroying...");
    }

    private void stdout(String message) {
        System.out.println(message);
    }

}
