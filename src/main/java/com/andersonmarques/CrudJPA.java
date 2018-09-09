package com.andersonmarques;

import javax.persistence.EntityManager;

import com.andersonmarques.model.Conta;
import com.andersonmarques.util.JPAUtil;

public class CrudJPA {

	public static void main(String[] args) {
		//inserirContaDefault();
		//buscarConta();
		removerConta();
	}

	
	/**
	 * Inserir conta padrão no banco.
	 */
	public static void inserirContaDefault() {
		Conta conta = new Conta("Anderson", "123", "6543", "Banco de Novigrad");

		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(conta);
		entityManager.getTransaction().commit();

		entityManager.close();
		jpaUtil.fecharFactory();
	}

	
	/**
	 * Buscar conta padrão no banco.
	 */
	private static void buscarConta() {
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		//Classe e ID
		Conta conta = entityManager.find(Conta.class, 1);
		System.out.println(conta);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		fazerMerge(conta);
		jpaUtil.fecharFactory();
	}


	/**
	 * Faz o merge(Update) da conta padrão, 
	 * sincronizando os dados alterados em memória com os existentes no banco.
	 * 
	 * @param conta
	 */
	private static void fazerMerge(Conta conta) {
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager2 = jpaUtil.getEntityManager();
		entityManager2.getTransaction().begin();

		/* Quando a conta não é transient e já foi persistida, 
		 * para sincronizar as informações, é preciso usar o merge */
		conta.setTitular("Vesemir");
		entityManager2.merge(conta);
		System.out.println(conta);
		
		entityManager2.getTransaction().commit();
		entityManager2.close();
		jpaUtil.fecharFactory();
	}
	
	
	private static void removerConta() {
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		//Classe e ID
		Conta conta = entityManager.find(Conta.class, 1);
		entityManager.remove(conta);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}
}
