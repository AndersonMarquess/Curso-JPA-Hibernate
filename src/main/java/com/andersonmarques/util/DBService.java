package com.andersonmarques.util;

import javax.persistence.EntityManager;

import com.andersonmarques.model.Conta;

/**
 * Classe responsável por popular o banco de dados, 
 * para facilitar a realização de testes.
 * 
 * @author Anderson Marques
 * @version 1.0
 */
public class DBService {
	
	public static void main(String[] args) {
		Conta conta1 = new Conta("Geratl", "159", "1347", "Banco de Novigrad");
		Conta conta2 = new Conta("Zoltan", "789", "6547", "Banco de Skellige");
		Conta conta3 = new Conta("Ciri", "889", "7421", "Banco de Teméria");
		Conta conta4 = new Conta("Dandelion", "632", "5874", "Banco de Novigrad");
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(conta1);
		entityManager.persist(conta2);
		entityManager.persist(conta3);
		entityManager.persist(conta4);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
