package com.daniam.shop.util;


public class JspHelper {
    public static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getJspFormat(String jspName){
        return String.format(JSP_FORMAT, jspName);
    }


}
