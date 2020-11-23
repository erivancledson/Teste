package teste.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.domain.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
