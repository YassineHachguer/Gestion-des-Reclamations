package yassine_hachguer.yassine_hachguer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yassine_hachguer.yassine_hachguer.entities.Reclamation;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    Page<Reclamation> findByObjetContains(String keyword, Pageable pageable);

}
