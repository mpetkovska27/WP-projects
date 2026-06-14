package mk.ukim.finki.wp.jan2025g1.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.jan2025g1.model.ArchaeologicalSite;
import mk.ukim.finki.wp.jan2025g1.model.HistoricalPeriod;
import mk.ukim.finki.wp.jan2025g1.model.SiteLocation;
import mk.ukim.finki.wp.jan2025g1.model.exceptions.InvalidArchaeologicalSiteIdException;
import mk.ukim.finki.wp.jan2025g1.repository.ArchaeologicalSiteRepository;
import mk.ukim.finki.wp.jan2025g1.repository.SiteLocationRepository;
import mk.ukim.finki.wp.jan2025g1.service.ArchaeologicalSiteService;
import mk.ukim.finki.wp.jan2025g1.service.SiteLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static mk.ukim.finki.wp.jan2025g1.service.FieldFilterSpecification.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchaeologicalSiteServiceImpl implements ArchaeologicalSiteService {
    private final ArchaeologicalSiteRepository archaeologicalSiteRepository;
    private final SiteLocationService siteLocationService;

    @Override
    public List<ArchaeologicalSite> listAll() {
        return this.archaeologicalSiteRepository.findAll();
    }

    @Override
    public ArchaeologicalSite findById(Long id) {
        return this.archaeologicalSiteRepository.findById(id).orElseThrow(()-> new InvalidArchaeologicalSiteIdException());
    }

    @Override
    public ArchaeologicalSite create(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        if (name == null || name.isEmpty() ||
                areaSize == null || rating <= 0.0 ||
                period == null ||
                locationId == null) {
            throw new InvalidArchaeologicalSiteIdException();
        }
        //location id mora da se najde kako cel objekt pa da se prati
        SiteLocation siteLocation = siteLocationService.findById(locationId);
        ArchaeologicalSite archaeologicalSite = new ArchaeologicalSite(name, areaSize, rating, period, siteLocation);
        return this.archaeologicalSiteRepository.save(archaeologicalSite);

    }

    @Override
    public ArchaeologicalSite update(Long id, String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        if (name == null || name.isEmpty() ||
                areaSize == null || rating <= 0.0 ||
                period == null ||
                locationId == null) {
            throw new InvalidArchaeologicalSiteIdException();
        }


        ArchaeologicalSite archaeologicalSite = this.findById(id);
        SiteLocation siteLocation = siteLocationService.findById(locationId);
        archaeologicalSite.setName(name);
        archaeologicalSite.setAreaSize(areaSize);
        archaeologicalSite.setRating(rating);
        archaeologicalSite.setPeriod(period);
        archaeologicalSite.setLocation(siteLocation);
        return this.archaeologicalSiteRepository.save(archaeologicalSite);

    }

    @Override
    public ArchaeologicalSite delete(Long id) {
        ArchaeologicalSite toDelete = this.findById(id);
        archaeologicalSiteRepository.deleteById(id);
        return toDelete;
    }

    @Override
    public ArchaeologicalSite close(Long id) {
        ArchaeologicalSite toClose= this.findById(id);
        toClose.setClosed(true);
        return archaeologicalSiteRepository.save(toClose);
    }

    @Override
    public Page<ArchaeologicalSite> findPage(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId, int pageNum, int pageSize) {
        Specification<ArchaeologicalSite> specification = Specification.allOf(
                filterContainsText(ArchaeologicalSite.class, "name", name),
                greaterThan(ArchaeologicalSite.class, "areaSize", areaSize),
                greaterThan(ArchaeologicalSite.class, "rating", rating),
                filterEqualsV(ArchaeologicalSite.class, "period", period),
                filterEquals(ArchaeologicalSite.class, "location.id", locationId) //location e objekt i od tamu go zima id
        );
                return this.archaeologicalSiteRepository.findAll(specification, PageRequest.of(pageNum, pageSize));
    }
}
