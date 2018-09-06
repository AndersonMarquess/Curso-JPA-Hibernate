package com.andersonmarques;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.andersonmarques.model.Categoria;
import com.andersonmarques.model.Conta;
import com.andersonmarques.model.Movimentacao;
import com.andersonmarques.model.TipoMovimentacao;
import com.andersonmarques.util.JPAUtil;

/**
 * Classe para realizar testes com a JPQL
 * 
 * @author Anderson Marques
 * @version 1.0
 */
public class MainJPQL {
	
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();

		procurarMovimentacaoConta(entityManager);
		procurarMovimentacaoPorCategoria(entityManager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}

	/**
	 * Procura as movimentações com a categoria de ID 2
	 * @param entityManager
	 */
	private static void procurarMovimentacaoPorCategoria(EntityManager entityManager) {
		Categoria categoria = new Categoria();
		categoria.setId(2);
		
		String jpql = "SELECT m FROM Movimentacao m JOIN m.categorias c WHERE c = :pCategoria";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("pCategoria", categoria);
		
		List<Movimentacao> result = query.getResultList();
		printResultQueryMovimentacao(result);
	}
	

	/**
	 * Procura as movimentações da conta com ID 1
	 * 
	 * @param entityManager
	 */
	private static void procurarMovimentacaoConta(EntityManager entityManager) {
		Conta conta = new Conta();
		conta.setId(1);
		
		//Movimentacao.conta.id
		String jpql = "SELECT m FROM Movimentacao m WHERE m.conta = :pConta "
				+ "AND m.tipo = :pTipo "
				+ "ORDER BY m.valor DESC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		List<Movimentacao> result = query.getResultList();
		printResultQueryMovimentacao(result);
	}


	private static void printResultQueryMovimentacao(List<Movimentacao> movimentacoes) {
		
		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println("Descrição: "+movimentacao.getDescricao());
			System.out.println("Valor: "+movimentacao.getValor());
			System.out.println("Tipo: "+movimentacao.getTipo());
			System.out.println("Data: "+movimentacao.getDataFormatada()+"\n");
		}
	}
}
