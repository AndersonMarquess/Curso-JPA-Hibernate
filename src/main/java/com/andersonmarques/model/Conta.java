package com.andersonmarques.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Informa que esta classe é uma entidade
@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String agencia;
	private String numero;
	private String banco;
	
	public Conta(String titular, String agencia, String numero, String banco) {
		this.titular = titular;
		this.agencia = agencia;
		this.numero = numero;
		this.banco = banco;
	}
	
	
}