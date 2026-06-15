package mk.ukim.finki.wp.june2025g1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.wp.june2025g1.model.Founder;

@Repository
public interface FounderRepository extends JpaRepository<Founder, Long> {

}
