package com.myspring.petfinder.xls;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class MyFilter implements Filter {

    private FilterConfig filterConfig = null;
    private StringBuffer errorMessage;

    public void init(FilterConfig filterConfig)
            throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!validateParameters(request)) {
            request.setAttribute("message", errorMessage.toString());
            request.setAttribute("ERROR_TYPE", "1");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
            errorMessage = null;
            return;
        }
        chain.doFilter(request, response);

    }

    /**
     *
     * @param request
     * @return
     */
    private boolean validateParameters(ServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        String paramName;
        while (params.hasMoreElements()) {
            paramName = params.nextElement();
            if (searchReservedChars(request.getParameter(paramName), paramName)) {
                return false;

            }
        }
        return true;
    }

    /**
     *
     * @param value
     * @param paramName
     * @return
     *
     * This method search for the suspicious patterns and constructs an error
     * message comprising of the pattern.
     *
     * paramName to be used if specific fields are to be skipped
     */
    private boolean searchReservedChars(String value, String paramName) {

        value = value.toLowerCase();
        Pattern xsspattern = Pattern.compile("[\\w]*((%27)|(‘))\\s * (( % 6F) | o | ( % 4F))((%72)|r | ( % 52))"
                + "|[\\w]*((%27)|(‘))\\s * (( % 61) | a | ( % 41))((%6E)|n | ( % 4E))((%64)|d | ( % 44))"
                + "|(((%3E)|>|(%3C)|<))"
                + "|(((%3E)|>|(%3C)|<)+.*[://.=/(/);’\"&#-]+.*)"
                + "|(.*[://.=/(/);’\"&#-]+.*((%3E)|>|(%3C)|<)+)"
                + "|(((%3C)|<)((%69)|i | ( % 49))((%6D)|m | ( % 4D))((%67)|g | ( % 47))[^\\n]+((%3E)|>))");
        Matcher match = xsspattern.matcher(value);
        if (match.find()) {
            errorMessage = new StringBuffer();
            String charstr = value.substring(match.start(), match.end());
            charstr = charstr.replaceAll(">", "&gt;");
            charstr = charstr.replaceAll("<", "&lt;");
            errorMessage.append("Suspicious input[ ").append(charstr).append(" ]. Use the browser Back key to return to the previous screen to correct this problem.");
            return true;
        }
        return false;
    }
}
