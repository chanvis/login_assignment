package spring.assignment.zuulproxy.filter;

import javax.servlet.http.HttpServletRequest;

import com.google.common.io.CharStreams;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class SimpleFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }


}
