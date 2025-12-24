package mk.ukim.finki.wp.lab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;



@Service
@RequiredArgsConstructor
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {

        Optional<Chef> chefOptional=chefRepository.findById(id);
        if(chefOptional.isPresent()){
            return chefOptional.get();
        }
        return null;
    }

    @Override
    public Chef create(String firstName, String lastName, String bio, Chef.Gender gender) {
        Chef newChef=new Chef(firstName,lastName,bio,gender);
        chefRepository.save(newChef);
        return newChef;
    }

    @Override
    public Chef update(Long id, String firstName, String lastName, String bio, Chef.Gender gender) {
        Chef updatedChef=new Chef(firstName,lastName,bio,gender);
        updatedChef.setId(id);
        chefRepository.save(updatedChef);
        return updatedChef;
    }

    @Override
    public void delete(Long id) {
        chefRepository.deleteById(id);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef=findById(chefId);
        Dish dish=dishRepository.findByDishId(dishId);
        chef.getDishes().add(dish);
        return chefRepository.save(chef);
    }
}