package com.andersonmarques;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.andersonmarques.model.Conta;


public class MainJPA {

	public static void main(String[] args) {
		Conta conta = new Conta("Anderson", "123", "6543", "Banco de novigrad");
		
		EntityManagerFactory entityManagerFactory = 
				//Nome da persistence-unit no xml, podemos ter varias, cada uma referente a um banco de dados.
				Persistence.createEntityManagerFactory("financas-mysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(conta);
        entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
