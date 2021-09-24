package by.kirill.pympproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/Controller")
public class FilterForLocale implements Filter {
    private final static String ATTRIBUTE_PATH = "path";
    private final static String PATH = "Controller?command=go_to_welcome";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
        if (session.getAttribute(ATTRIBUTE_PATH) == null) {
            session.setAttribute(ATTRIBUTE_PATH, PATH);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}