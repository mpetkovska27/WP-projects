package mk.ukim.finki.wp.lab.web.controller;

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
    public String getDishesPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) Long chefId,
            Model model) {
        if(error != null) {
            model.addAttribute("error", error);
        }

        if (chefId != null) {
            Chef chef = chefService.findById(chefId);
            if (chef == null) {
                return "redirect:/chefs?error=ChefNotFound";
            }
            model.addAttribute("chef", chef);
            model.addAttribute("dishes", dishService.listDishes());
            return "dishesList";
        }

        model.addAttribute("dishes", dishService.listDishes());
        return "listDishes";
    }
    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }
    @PostMapping("/dishes/edit/{id}")
    public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model){
        try {
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            return "dish-form";
        } catch (RuntimeException e) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model){
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