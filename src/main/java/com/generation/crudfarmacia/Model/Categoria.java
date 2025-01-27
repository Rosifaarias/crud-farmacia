package com.generation.crudfarmacia.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity	
@Table(name = "tb_categoria")
public class Categoria {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank (message = "O atributo tipo é obrigatório")
@Size (min = 3, max = 100)
private String tipo;


@NotBlank (message = "O atributo nome é obrigatório")
@Size (min = 3, max = 100)
private String descricao;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getTipo() {
	return tipo;
}


public void setTipo(String tipo) {
	this.tipo = tipo;
}


public String getDescricao() {
	return descricao;
}


public void setDescricao(String descricao) {
	this.descricao = descricao;
}


	


}
