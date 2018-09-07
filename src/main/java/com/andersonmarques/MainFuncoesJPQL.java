package com.andersonmarques;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.andersonmarques.model.Conta;
import com.andersonmarques.model.TipoMovimentacao;
import com.andersonmarques.util.JPAUtil;

/**
 * Classe para testes usando funções com a JPQL
 * 
 * @author Anderson Marques
 * @version 1.0
 */
public class MainFuncoesJPQL {
	
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		Conta conta = new Conta();
		conta.setId(1);
		
		
		Query querySum = getSomaTotalMovimentacoes(entityManager, conta);
		BigDecimal totalValor = (BigDecimal) querySum.getSingleResult();
		System.out.println("Soma de todas as movimentações: "+totalValor);
		
		Query queryAvg = getMediaTotalMovimentacoes(entityManager, conta);
		Double media = (Double) queryAvg.getSingleResult();
		System.out.println("Média das movimentações: "+media);
		
		Query queryMax = getMaiorMovimentacao(entityManager, conta);
		totalValor = (BigDecimal) queryMax.getSingleResult();
		System.out.println("Maior movimentação: "+totalValor);
		
		Query queryCount = getQtdMovimentacoes(entityManager, conta);
		Long count = (Long) queryCount.getSingleResult();
		System.out.println("Total de movimentações: "+count);
		
		
		//Query tipada
		TypedQuery<Double> queryAvgDia = getMediaMovimentacoesPorDia(entityManager, conta);
		List<Double> mediaDia= queryAvgDia.getResultList();
		mediaDia.forEach(m -> System.out.println("Média por dia: "+m));
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}
	
	
	/**
	 * Cria a query com a função de pegar a quantidade total das movimentações e retorna o seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @return Query
	 */
	private static TypedQuery<Double> getMediaMovimentacoesPorDia(EntityManager entityManager, Conta conta) {
		String jpql = "SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo "
				+"GROUP BY DAY(m.data), MONTH(m.data), YEAR(m.data)";
		
		//Query tipada
		TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		return query;
	}
	
	
	/**
	 * Cria a query com a função de pegar a quantidade total das movimentações e retorna o seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @return Query
	 */
	private static Query getQtdMovimentacoes(EntityManager entityManager, Conta conta) {
		String jpql = "SELECT COUNT(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";
		Query query = definirQuery(entityManager, conta, jpql);
		return query;
	}
	
	
	/**
	 * Cria a query com a função de pegar o Maior valor das movimentações e retorna o seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @return Query
	 */
	private static Query getMaiorMovimentacao(EntityManager entityManager, Conta conta) {
		String jpql = "SELECT MAX(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";
		Query query = definirQuery(entityManager, conta, jpql);
		return query;
	}
	
	
	/**
	 * Cria a query com a função de somar todos os valores das movimentações e retorna o seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @return Query
	 */
	private static Query getSomaTotalMovimentacoes(EntityManager entityManager, Conta conta) {
		String jpql = "SELECT SUM(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";
		Query query = definirQuery(entityManager, conta, jpql);
		return query;
	}
	
	
	/**
	 * Cria a query com a função de pegar a média dos valores das movimentações e retorna o seu resultado.
	 * 
	 * @param entityManager
	 * @param conta
	 * @return Query
	 */
	private static Query getMediaTotalMovimentacoes(EntityManager entityManager, Conta conta) {
		String jpql = "SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+"AND m.tipo = :pTipo";
		Query query = definirQuery(entityManager, conta, jpql);
		return query;
	}
	
	
	/**
	 * Define os parâmetros de conta e tipo na query especificada.
	 * 
	 * @param entityManager
	 * @param conta
	 * @param jpql
	 * @return Query
	 */
	private static Query definirQuery(EntityManager entityManager, Conta conta, String jpql) {
		Query query = entityManager.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		return query;
	}
}
