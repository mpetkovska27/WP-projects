package mk.ukim.finki.wp.jan2025g1.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.jan2025g1.model.SiteLocation;
import mk.ukim.finki.wp.jan2025g1.model.exceptions.InvalidSiteLocationIdException;
import mk.ukim.finki.wp.jan2025g1.repository.SiteLocationRepository;
import mk.ukim.finki.wp.jan2025g1.service.SiteLocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteLocationServiceImpl implements SiteLocationService {
    private final SiteLocationRepository siteLocationRepository;

    @Override
    public SiteLocation findById(Long id) {
        return this.siteLocationRepository.findById(id).orElseThrow(InvalidSiteLocationIdException::new);    }

    @Override
    public List<SiteLocation> listAll() {
        return this.siteLocationRepository.findAll();
    }

    @Override
    public SiteLocation create(String city, String country) {
        if (city == null || city.isEmpty() ||
                country == null || country.isEmpty()) {
            throw new InvalidSiteLocationIdException();
        }
        SiteLocation siteLocation = new SiteLocation(city, country);
        return this.siteLocationRepository.save(siteLocation);
    }
}
