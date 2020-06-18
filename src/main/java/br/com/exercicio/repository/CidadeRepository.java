package br.com.exercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.exercicio.model.beans.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	@Query("SELECT c FROM Cidade c WHERE c.nome LIKE :c%")
	public List<Cidade> findByInicialLetter(String c);
	
	@Query("SELECT c FROM Cidade c WHERE c.latitude = :lat AND c.longitude = :lon")
	public List<Cidade> findByCoord(Long lat, Long lon);
}
