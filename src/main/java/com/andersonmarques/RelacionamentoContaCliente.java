package com.andersonmarques;

import javax.persistence.EntityManager;

import com.andersonmarques.model.Cliente;
import com.andersonmarques.model.Conta;
import com.andersonmarques.util.JPAUtil;

public class RelacionamentoContaCliente {
	
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setId(1);//Conta já existente
		
		
		Cliente cliente = new Cliente("Yennefer", "Conselheira", "Aedirn", conta);
		Cliente cliente2 = new Cliente("Outro", "Conselheira", "Aedirn", conta);
		
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(cliente2);//com conta já existente
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}
}
