package com.andersonmarques.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe responsável por facilitar a criação de uma EntityManager
 * 
 * @author Anderson Marques
 * @version 1.0
 */
public class JPAUtil {
	/* financas-mysql = Nome da persistence-unit no xml, 
	 * podemos ter varias, cada uma referente a um banco de dados.*/
	private static EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("financas-mysql");
	
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
