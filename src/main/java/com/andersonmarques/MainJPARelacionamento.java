package com.andersonmarques;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import com.andersonmarques.model.Conta;
import com.andersonmarques.model.Movimentacao;
import com.andersonmarques.model.TipoMovimentacao;
import com.andersonmarques.util.JPAUtil;

public class MainJPARelacionamento {

	public static void main(String[] args) {

		Conta conta = new Conta("Triss", "951", "7431", "Banco de Novigrad");

		Movimentacao movimentacao = new Movimentacao(BigDecimal.valueOf(25.90d), TipoMovimentacao.SAIDA,
				Calendar.getInstance(), "Alfaiate", conta);

		EntityManager entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(conta);
		entityManager.persist(movimentacao);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
