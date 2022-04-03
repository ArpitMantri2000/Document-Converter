package net.codejava.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.model.Doc;

public interface DocRepository extends JpaRepository<Doc, Long> {

	Optional<Doc> findById(Long id);
	
}
