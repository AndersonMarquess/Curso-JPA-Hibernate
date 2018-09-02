package com.andersonmarques.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	private String descricao;
	
	@ManyToOne
	private Conta conta;
	
	
	public Movimentacao() {}

	public Movimentacao(BigDecimal valor, TipoMovimentacao tipo, 
			Calendar data, String descricao, Conta conta) {
		this.valor = valor;
		this.tipo = tipo;
		this.data = data;
		this.descricao = descricao;
		this.conta = conta;
	}
	
	
	
	
}
