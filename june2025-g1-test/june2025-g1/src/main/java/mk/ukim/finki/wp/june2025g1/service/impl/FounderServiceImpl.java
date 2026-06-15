package mk.ukim.finki.wp.june2025g1.service.impl;

import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.june2025g1.model.Founder;
import mk.ukim.finki.wp.june2025g1.model.exceptions.InvalidFounderIdException;
import mk.ukim.finki.wp.june2025g1.repository.FounderRepository;
import mk.ukim.finki.wp.june2025g1.service.FounderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FounderServiceImpl implements FounderService {
    private final FounderRepository founderRepository;
    @Override
    public Founder findById(Long id) {
        return this.founderRepository.findById(id).orElseThrow(()-> new InvalidFounderIdException());
    }

    @Override
    public List<Founder> listAll() {
        return this.founderRepository.findAll();
    }

    @Override
    public Founder create(String name, String email) {
        if (name == null || name.isEmpty() ||
                email == null || email.isEmpty()) {
            throw new InvalidFounderIdException();
        }
        Founder founder = new Founder(name, email);
        return this.founderRepository.save(founder);
    }
}
