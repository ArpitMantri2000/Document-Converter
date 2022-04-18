package net.codejava.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Doc;

@Repository
public interface DocRepository extends JpaRepository<Doc, Long> {

	Optional<Doc> findById(Long id);

	Doc findByDocumentuid(String documentuid);


	


}
