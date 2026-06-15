package mk.ukim.finki.wp.june2025g1.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.june2025g1.model.Founder;
import mk.ukim.finki.wp.june2025g1.model.Industry;
import mk.ukim.finki.wp.june2025g1.model.Startup;
import mk.ukim.finki.wp.june2025g1.model.exceptions.InvalidStartupIdException;
import mk.ukim.finki.wp.june2025g1.repository.StartupRepository;
import mk.ukim.finki.wp.june2025g1.service.FounderService;
import mk.ukim.finki.wp.june2025g1.service.StartupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.june2025g1.service.specifications.FieldFilterSpecification.*;

@Service
@RequiredArgsConstructor
public class StartupServiceImpl implements StartupService {
    private final StartupRepository startupRepository;
    private final FounderService founderService;

    @Override
    public List<Startup> listAll() {
        return this.startupRepository.findAll();
    }

    @Override
    public Startup findById(Long id) {
        return this.startupRepository.findById(id).orElseThrow(()-> new InvalidStartupIdException());
    }

    @Override
    public Startup create(String name, Double valuation, Integer yearFounded, Industry industry, Long founderId) {
        if (name == null || name.isEmpty() ||
                valuation == null || valuation <= 0 ||
                yearFounded == null || yearFounded < 0 ||
                industry == null ||
                founderId == null ) {
            throw new InvalidStartupIdException();
        }
        Founder founder = founderService.findById(founderId);
        Startup startup = new Startup(name, valuation, yearFounded, industry, founder);
        return this.startupRepository.save(startup);
    }

    @Override
    public Startup update(Long id, String name, Double valuation, Integer yearFounded, Industry industry, Long founderId) {
        if (name == null || name.isEmpty() ||
                valuation == null || valuation <= 0 ||
                yearFounded == null || yearFounded < 0 ||
                industry == null ||
                founderId == null ) {
            throw new InvalidStartupIdException();
        }
        Founder founder = founderService.findById(founderId);
        Startup startup = findById(id);
        startup.setName(name);
        startup.setValuation(valuation);
        startup.setYearFounded(yearFounded);
        startup.setIndustry(industry);
        startup.setFounder(founder);
        return this.startupRepository.save(startup);
    }

    @Override
    public Startup delete(Long id) {
        Startup startup = findById(id);
        this.startupRepository.deleteById(id);
        return startup;
    }

    @Override
    public Startup deactivate(Long id) {
        Startup startup = findById(id);
        startup.setActive(false);
        return this.startupRepository.save(startup);
    }

    @Override
    public Page<Startup> findPage(String name, Double valuation, Integer yearFounded, Industry industry, Long founderId, int pageNum, int pageSize) {
        Specification<Startup> specification = Specification.allOf(
                filterContainsText(Startup.class, "name", name),
                greaterThan(Startup.class, "valuation", valuation),
                greaterThan(Startup.class, "yearFounded", yearFounded),
                filterEqualsV(Startup.class, "industry", industry),
                filterEquals(Startup.class, "founder.id", founderId)
        );
        return this.startupRepository.findAll(specification, PageRequest.of(pageNum, pageSize));
    }
}
