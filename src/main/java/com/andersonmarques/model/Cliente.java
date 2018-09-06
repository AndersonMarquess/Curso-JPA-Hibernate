package com.andersonmarques.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String profissao;
	private String endereco;
	
	/* Um cliente só pode possuir uma conta e uma conta pertence apenas a um cliente. */
	@JoinColumn(unique=true)
	@OneToOne
	private Conta conta;
	
	public Cliente() {}
	
	public Cliente(String nome, String profissao, String endereco, Conta conta) {
		super();
		this.nome = nome;
		this.profissao = profissao;
		this.endereco = endereco;
		this.conta = conta;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
