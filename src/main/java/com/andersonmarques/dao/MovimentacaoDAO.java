package com.andersonmarques.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.andersonmarques.model.Conta;
import com.andersonmarques.model.TipoMovimentacao;

/**
 * Classe com todas as operação da JPQL
 * 
 * @author Anderson Marques
 * @version 1.0
 */
public class MovimentacaoDAO {
	
	private EntityManager entityManager;
	
	public MovimentacaoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	/**
	 * Atribui a conta e o tipo da movimentação a uma query da JPQL
	 * 
	 * @param conta
	 * @param tipoMovimentacao
	 * @param jpql
	 * @return Query
	 */
	private Query atribuirContaETipo(Conta conta, TipoMovimentacao tipoMovimentacao, String jpql) {
		Query query = entityManager.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipoMovimentacao);
		return query;
	}
	
	
	/**
	 * Cria a query com a função de pegar o valor médio das movimentações e retorna seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @param tipoMovimentacao 
	 * @return Double
	 */
	public Double getMediaTotalMovimentacoesComTipo(Conta conta, TipoMovimentacao tipoMovimentacao) {
		String jpql = "SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";

		Query query = atribuirContaETipo(conta, tipoMovimentacao, jpql);

		return (Double) query.getSingleResult();
	}
	
	
	/**
	 * Cria a query com a função de pegar o Maior valor das movimentações e retorna seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @param tipoMovimentacao 
	 * @return BigDecimal
	 */
	public BigDecimal getMaiorMovimentacaoComTipo(Conta conta, TipoMovimentacao tipoMovimentacao) {
		String jpql = "SELECT MAX(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";

		Query query = atribuirContaETipo(conta, tipoMovimentacao, jpql);
		
		return (BigDecimal) query.getSingleResult();
	}
	
	
	/**
	 * Cria a query com a função de somar todos os valores das movimentações e retorna seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @param tipoMovimentacao 
	 * @return BigDecimal
	 */
	public BigDecimal getSomaTotalMovimentacoesComTipo(Conta conta, TipoMovimentacao tipoMovimentacao) {
		String jpql = "SELECT SUM(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";

		Query query = atribuirContaETipo(conta, tipoMovimentacao, jpql);
		
		return (BigDecimal) query.getSingleResult();
	}
	

	/**
	 * Cria a query com a função de pegar a quantidade total das movimentações e retorna seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @param tipoMovimentacao 
	 * @return Long
	 */
	public Long getQtdMovimentacoesComTipo(Conta conta, TipoMovimentacao tipoMovimentacao) {
		String jpql = "SELECT COUNT(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";
		
		Query query = atribuirContaETipo(conta, tipoMovimentacao, jpql);
		
		return (Long) query.getSingleResult();
	}
	
	
	/**
	 * Cria a List<Double> com a função de pegar a média do dia das movimentações e retorna seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @param tipoMovimentacao 
	 * @return List<Double>
	 */
	public List<Double> getMediaMovimentacoesPorDia(Conta conta, TipoMovimentacao tipoMovimentacao) {
		String jpql = "SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo "
				+"GROUP BY DAY(m.data), MONTH(m.data), YEAR(m.data)";
		
		//Query tipada
		TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipoMovimentacao);
		
		return query.getResultList();
	}
}
