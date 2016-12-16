package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Kommentti;

public interface KommenttiRepository extends JpaRepository<Kommentti, Long> {
    
}
