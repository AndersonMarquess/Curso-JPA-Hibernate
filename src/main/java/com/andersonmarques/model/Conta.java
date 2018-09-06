package com.andersonmarques.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Informa que esta classe é uma entidade
@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String agencia;
	private String numero;
	private String banco;

	//Uma conta para muitas movimentações
	//conta presente na classe Movimentacao
	@OneToMany(mappedBy="conta")
	private List<Movimentacao> movimentacoes;

	public Conta() {
	}

	public Conta(String titular, String agencia, String numero, String banco) {
		this.titular = titular;
		this.agencia = agencia;
		this.numero = numero;
		this.banco = banco;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", titular=" + titular + ", agencia=" + agencia + ", numero=" + numero + ", banco="
				+ banco + "]";
	}
}
