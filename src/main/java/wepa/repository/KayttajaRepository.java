package wepa.repository;
import wepa.domain.Kayttaja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KayttajaRepository extends JpaRepository<Kayttaja, Long> {
    Kayttaja findByUsername(String name);
}
