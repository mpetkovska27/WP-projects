//package mk.ukim.finki.wp.lab.web.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//
//@WebFilter(urlPatterns = {"/dish", "/chefDetails"})
//public class ParametharsFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        String chefId = req.getParameter("chefId");
//        String dishId = req.getParameter("dishId");
//        String path = req.getServletPath();
//
//
//        if (chefId == null && path.equals("/dish")) {
//            resp.sendRedirect("/listChefs");
//        }else if (chefId == null && dishId==null && path.equals("/chefDetails")) {
//            resp.sendRedirect("/listChefs");
//        } else {
//            filterChain.doFilter(req, resp);
//        }
//
//    }
//}
