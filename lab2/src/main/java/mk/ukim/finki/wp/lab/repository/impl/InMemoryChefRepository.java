package mk.ukim.finki.wp.lab.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.repository.ChefRepository;

@Repository
@RequiredArgsConstructor
public class InMemoryChefRepository implements ChefRepository {

    private final DataHolder dataHolder;

    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return DataHolder.chefs.stream()
                .filter(s->s.getId().equals(id))
                .findFirst();
    }

    @Override
    public Chef save(Chef chef) {
        Optional<Chef> najden=findById(chef.getId());
        if(najden.isPresent()){

            Chef update= najden.get();
            update.setFirstName(chef.getFirstName());
            update.setLastName(chef.getLastName());
            update.setBio(chef.getBio());
            update.setGender(chef.getGender());
            return update;
        }
        //AKO NE POSTOI DODAJ NOV
        DataHolder.chefs.add(chef);
        return chef;
    }

    @Override
    public void deleteById(Long id) {
        for(Chef deletedChef:DataHolder.chefs){
            if(deletedChef.getId().equals(id)){
                DataHolder.chefs.remove(deletedChef);
                break;
            }
        }
    }
}