package br.com.maisViagens.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.maisViagens.model.Feedback;


@Repository
public interface FeedbackRepository extends  JpaRepository<Feedback, Long>{
	
	@Query(value = "select f from Feedback f where upper(trim(f.descricao)) like %?1%")
	List<Feedback> buscarPorDescricao(String descricao);
	
	@Query(value = "select f from Feedback f where upper(trim(f.data)) like %?1%")
	List<Feedback> buscarPorData(String data);

}
