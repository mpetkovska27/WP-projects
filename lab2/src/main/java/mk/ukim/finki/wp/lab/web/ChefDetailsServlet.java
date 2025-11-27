//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Chef;
//import mk.ukim.finki.wp.lab.model.Dish;
//import mk.ukim.finki.wp.lab.service.ChefService;
//import mk.ukim.finki.wp.lab.service.DishService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
//public class ChefDetailsServlet extends HttpServlet {
//    private final ChefService chefService;
//    private final DishService dishService;
//    private final SpringTemplateEngine templateEngine;
//
//    public ChefDetailsServlet(ChefService chefService, DishService dishService, SpringTemplateEngine templateEngine) {
//        this.chefService = chefService;
//        this.dishService = dishService;
//        this.templateEngine = templateEngine;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context=new WebContext(webExchange);
//
//        Long chefId = Long.parseLong(req.getParameter("chefId"));
//        String dishId = req.getParameter("dishId");
//
//
////Го додаде јадењето во листата на јадења на избраниот готвач
//        Chef chef = chefService.addDishToChef(chefId, dishId);
//        context.setVariable("chef", chef);
//
//        templateEngine.process("chefDetails.html", context, resp.getWriter());
//
//    }
//}
