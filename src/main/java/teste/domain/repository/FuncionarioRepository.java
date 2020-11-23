package teste.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import teste.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	@Query("from Funcionario f inner join f.departamentos d where d.id=:departamentoId")
	List<Funcionario> findByDepartamento(@Param("departamentoId") Integer departamentoId);
}
