package com.andersonmarques;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

		//procurarMovimentacaoConta(entityManager);
		//procurarMovimentacaoPorCategoria(entityManager);

		//LEFT JOIN FETCH Muda o lazy da lista de movimentações da conta, resolvendo o N+1
		//LEFT JOIN para trazer todas as contas, mesmo que ela não possua movimentações.
		String jpql = "SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.movimentacoes";
		Query query = entityManager.createQuery(jpql);
		List<Conta> contas = query.getResultList();
		
		for(Conta c : contas) {
			System.out.println("Titular: "+c.getTitular());
			System.out.println("Movimentações: ");
			System.out.println(c.getMovimentacoes());
		}

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
		printListMovimentacao(result);
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
		printListMovimentacao(result);
	}


	private static void printListMovimentacao(List<Movimentacao> movimentacoes) {
		
		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println("Descrição: "+movimentacao.getDescricao());
			System.out.println("Valor: "+movimentacao.getValor());
			System.out.println("Tipo: "+movimentacao.getTipo());
			System.out.println("Data: "+movimentacao.getDataFormatada()+"\n");
		}
	}
}
