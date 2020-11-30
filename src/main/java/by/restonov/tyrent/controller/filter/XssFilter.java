package by.restonov.tyrent.controller.filter;

import by.restonov.tyrent.manager.PageName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Check if someone print <script> or <img> tags
 * instead of parameter value into URI
 *
 * Preventing xss attack
 * Redirect to 404 page
 *
 */
public class XssFilter implements Filter {
    private static final String SCRIPT_TAG = "script";
    private static final String IMAGE_TAG = "img";
    private static final String CONTENT_POLICY = "Content-Security-Policy";
    private static final String SCRIPT_NONE = "script-src 'none'";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        boolean scriptDetected = false;
        Map<String, String[]> parameterMap = request.getParameterMap();
        
        for (String[] values : parameterMap.values()) {
            for (String value : values) {
                if (value.toLowerCase().contains(SCRIPT_TAG) || value.toLowerCase().contains(IMAGE_TAG)) {
                    scriptDetected = true;
                    break;
                }
            }
        }
        if (scriptDetected) {
            response.setHeader(CONTENT_POLICY, SCRIPT_NONE);
            response.sendRedirect(request.getContextPath() + PageName.ERROR_404_PATH);
        } else {
            chain.doFilter(request, response);
        }
    }
}
