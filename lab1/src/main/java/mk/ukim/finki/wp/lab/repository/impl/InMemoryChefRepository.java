package mk.ukim.finki.wp.lab.repository.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class InMemoryChefRepository implements ChefRepository {

    private final DataHolder dataHolder;

    public InMemoryChefRepository(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

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
        //ako vekje postoi chef vo listata so isto id, izbrishigo prvo pa zacuvaj go novoto
        DataHolder.chefs.removeIf(s->s.getId().equals(chef.getId()));
        DataHolder.chefs.add(chef);
        return chef;
    }
}
