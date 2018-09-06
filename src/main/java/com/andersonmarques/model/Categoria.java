package com.andersonmarques.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	public Categoria() {}

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
