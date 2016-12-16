package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Tunniste;

public interface TunnisteRepository  extends JpaRepository<Tunniste, Long> {
    Tunniste findByNimi(String nimi);
    
}
