package mk.ukim.finki.wp.lab.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;

@Controller
public class ChefController {
    private final ChefService chefService;


    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/chefs")
    public String getChefsPage(@RequestParam(required=false) String error, Model model){
        List chefs=chefService.listChefs();
        model.addAttribute("chefs",chefs);
        model.addAttribute("error",error);
        return "listChefs";
    }

    @PostMapping("/chefs/add")
    public String saveChef(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio,
                           @RequestParam Chef.Gender gender){
        chefService.create(firstName, lastName, bio, gender);
        return "redirect:/chefs";
    }

    @GetMapping("chefs/chef-form")
    public String getAddChefPage(Model model){
        model.addAttribute("genders", Chef.Gender.values());
        return "chef-form";
    }

    @GetMapping("/chefs/chef-form/{id}")
    public String getEditChefForm(@PathVariable Long id, Model model){
        Chef editedChef=chefService.findById(id);
        if (editedChef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        }
        model.addAttribute("genders",Chef.Gender.values());
        model.addAttribute("chef",editedChef);
        return "chef-form";
    }

    @PostMapping("/chefs/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio,
                           @RequestParam Chef.Gender gender){
        chefService.update(id,firstName,lastName,bio,gender);
        return "redirect:/chefs";
    }

    @GetMapping("/chefs/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        chefService.delete(id);
        return "redirect:/chefs";
    }

    @PostMapping("/chefs")
    public String selectChef(@RequestParam Long chefId) {
        Chef chef = chefService.findById(chefId);
        if (chef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        }
        return "redirect:/dishes?chefId=" + chefId;
    }
}