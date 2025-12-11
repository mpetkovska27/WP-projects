package mk.ukim.finki.wp.lab.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;

@Controller
public class DishController {
    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }
    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error, Model model){
        List<Dish> dishes = dishService.listDishes();
        model.addAttribute("dishes",dishes);
        return "listDishes";
    }
    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime,  @RequestParam(required = false) Long chefId){
        try {
            Dish dish = dishService.create(dishId, name, cuisine, preparationTime);
            //ako ima selektirano chef, da mu se dodeli toj dish na toj chef
            if(chefId != null) {
                chefService.addDishToChef(chefId, dish.getDishId());
            }
            return "redirect:/dishes";
        } catch (Exception e) {
            return "redirect:/dishes?error=" + e.getMessage();
        }
    }
    @PostMapping("/dishes/edit/{id}")
    public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime, @RequestParam(required = false) Long chefId){
        try {
            dishService.update(id, dishId, name, cuisine, preparationTime);
            if(chefId != null) {
                Dish dish = dishService.findByDishId(dishId);
                if (dish != null) {
                    chefService.addDishToChef(chefId, dishId);
                }
            }
            return "redirect:/dishes";
        } catch (Exception e) {
            return "redirect:/dishes?error=" + e.getMessage();
        }
    }
    @PostMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        try {
            dishService.delete(id);
            return "redirect:/dishes";
        } catch (Exception e) {
            return "redirect:/dishes?error=" + e.getMessage();
        }
    }
    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model){
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            List<Chef> chefs = chefService.listChefs();
            model.addAttribute("chefs", chefs);
            return "dish-form";
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model){
        List<Chef> chefs = chefService.listChefs();
        model.addAttribute("chefs", chefs);
        return "dish-form";
    }

    //integracija so chef
    @PostMapping(value = "/chefDetails")
    public String addDishToChef(
            @RequestParam Long chefId,
            @RequestParam String dishId,
            Model model) {
        try {
            Chef chef = chefService.addDishToChef(chefId, dishId);
            model.addAttribute("chef", chef);
            return "chefDetails";
        } catch (Exception e) {
            return "redirect:/dishes?error=" + e.getMessage();
        }
    }
}
