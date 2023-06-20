//package filter;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/*"})
//public class CustomFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String servletPath = request.getServletPath();
//        String contextPath = request.getContextPath();
//        String role = "";
//        String email = "";
//
//        HttpSession session = request.getSession();
//
//        try {
//            email = (String) session.getAttribute("email");
//            role = (String) session.getAttribute("role");
//        } catch (Exception e) {
//        }
//
//        System.out.println("role: " + role);
//
//        if (role == ("ROLE_USER")) {
//            if (servletPath.endsWith("/add") || servletPath.endsWith("/edit")) {
//                response.sendRedirect(contextPath + "/403");
//            } else {
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//        } else if (role == ("ROLE_ADMIN")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            response.sendRedirect(contextPath + "/login");
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
