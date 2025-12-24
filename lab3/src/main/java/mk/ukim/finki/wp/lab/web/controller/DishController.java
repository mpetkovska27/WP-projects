package mk.ukim.finki.wp.lab.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;

@Controller
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishController(DishService dishService, ChefService chefService, DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) Long chefId,
                                Model model) {
        if (chefId != null) {
            return "redirect:/chefDetails?chefId=" + chefId;
        }

        List<Dish> dishes = dishService.listDishes();
        model.addAttribute("dishes", dishes);
        return "listDishes";
    }

    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam Dish.Cuisine cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam String preparationDate,
                           @RequestParam(required = false) List<Long> chefIds) {
        try {
            LocalDate date = LocalDate.parse(preparationDate);
            Dish dish = dishService.create(dishId, name, cuisine, preparationTime, date);

            if (chefIds != null && !chefIds.isEmpty()) {
                for (Long chefId : chefIds) {
                    chefService.addDishToChef(chefId, dish.getDishId());
                }
            }
            return "redirect:/dishes";
        } catch (Exception e) {
            return "redirect:/dishes?error=" + e.getMessage();
        }
    }

    @PostMapping("/dishes/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam Dish.Cuisine cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam String preparationDate,
                           @RequestParam(required = false) List<Long> chefIds) {

        LocalDate date = LocalDate.parse(preparationDate);

        dishService.update(id, dishId, name, cuisine, preparationTime, date);

        Dish dish = dishService.findById(id);

        for (Chef chef : new ArrayList<>(dish.getChefs())) {
            chef.getDishes().remove(dish);
            dish.getChefs().remove(chef);
            chefRepository.save(chef);
        }

        if (chefIds != null) {
            for (Long chefId : chefIds) {
                Chef chef = chefService.findById(chefId);
                chef.getDishes().add(dish);
                dish.getChefs().add(chef);
                chefRepository.save(chef);
            }
        }
        dishRepository.save(dish);

        return "redirect:/dishes";
    }

    @PostMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        try {
            dishService.delete(id);
            return "redirect:/dishes";
        } catch (Exception e) {
            return "redirect:/dishes?error=" + e.getMessage();
        }
    }

    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish != null) {
            model.addAttribute("dish", dish);
            model.addAttribute("cuisines", Dish.Cuisine.values());
            List<Chef> chefs = chefService.listChefs();
            model.addAttribute("chefs", chefs);
            return "dish-form";
        } else {
            model.addAttribute("error", "Dish not found!");
            return "redirect:/dishes";
        }
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("cuisines", Dish.Cuisine.values());
        List<Chef> chefs = chefService.listChefs();
        model.addAttribute("chefs", chefs);
        return "dish-form";
    }

    @GetMapping("/chefDetails")
    public String getAddDishToChefForm(@RequestParam Long chefId, Model model) {
        try {
            Chef chef = chefService.findById(chefId);
            if (chef == null) {
                return "redirect:/chefs?error=ChefNotFound";
            }
            List<Dish> dishes = dishService.listDishes();
            model.addAttribute("chef", chef);
            model.addAttribute("dishes", dishes);
            return "dishesList";
        } catch (Exception e) {
            return "redirect:/chefs?error=" + e.getMessage();
        }
    }

    @PostMapping(value = "/chefDetails")
    public String addDishToChef(
            @RequestParam Long chefId,
            @RequestParam(required = false) List<String> dishIds,
            Model model) {
        try {
            if (dishIds == null || dishIds.isEmpty()) {
                return "redirect:/chefDetails?chefId=" + chefId + "&error=NoDishesSelected";
            }

            for (String dishId : dishIds) {
                chefService.addDishToChef(chefId, dishId);
            }

            Chef chef = chefService.findById(chefId);
            model.addAttribute("chef", chef);
            return "chefDetails";
        } catch (Exception e) {
            return "redirect:/chefDetails?chefId=" + chefId + "&error=" + e.getMessage();
        }
    }
}