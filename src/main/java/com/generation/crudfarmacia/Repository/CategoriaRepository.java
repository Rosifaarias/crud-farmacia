package com.generation.crudfarmacia.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.crudfarmacia.Model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	

	List<Categoria> findAllBytipoContainingIgnoreCase(@Param("tipo") String tipo);
}
