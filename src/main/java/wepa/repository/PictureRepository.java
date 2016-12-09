package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

}