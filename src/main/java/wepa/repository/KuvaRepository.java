package wepa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Kayttaja;
import wepa.domain.Kuva;

public interface KuvaRepository extends JpaRepository<Kuva, Long> {
    List<Kuva> findByKayttaja(Kayttaja kayttaja);
}