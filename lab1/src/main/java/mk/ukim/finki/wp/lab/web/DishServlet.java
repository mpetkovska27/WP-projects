package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

        private final DishService dishService;
        private final ChefService chefService;
        private final SpringTemplateEngine templateEngine;

    public DishServlet(DishService dishService, ChefService chefService, SpringTemplateEngine templateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);
        List<Dish> dishes = dishService.listDishes();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context=new WebContext(webExchange);
        context.setVariable("dishes", dishes);
        context.setVariable("chef", chef);

        templateEngine.process("dishesList", context, resp.getWriter());


    }
}
