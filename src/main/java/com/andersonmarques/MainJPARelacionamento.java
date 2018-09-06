package com.andersonmarques;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import com.andersonmarques.model.Categoria;
import com.andersonmarques.model.Conta;
import com.andersonmarques.model.Movimentacao;
import com.andersonmarques.model.TipoMovimentacao;
import com.andersonmarques.util.JPAUtil;

public class MainJPARelacionamento {

	public static void main(String[] args) {
		Categoria categoria1 = new Categoria("Viagem");
		Categoria categoria2 = new Categoria("Negócio");
		Conta conta = new Conta("Triss", "951", "7431", "Banco de Novigrad");

		Movimentacao movimentacao1 = new Movimentacao(BigDecimal.valueOf(25.90d), TipoMovimentacao.SAIDA,
				Calendar.getInstance(), "Alfaiate", conta);
		Movimentacao movimentacao2 = new Movimentacao(BigDecimal.valueOf(150.00d), TipoMovimentacao.SAIDA,
				Calendar.getInstance(), "Ferreiro", conta);
		Movimentacao movimentacao3 = new Movimentacao(BigDecimal.valueOf(75.00d), TipoMovimentacao.ENTRADA,
				Calendar.getInstance(), "Recompensa", conta);
		
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao3.setCategorias(Arrays.asList(categoria2));

		
		/* Transação */
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(conta);
		entityManager.persist(categoria1);
		entityManager.persist(categoria2);
		entityManager.persist(movimentacao1);
		entityManager.persist(movimentacao2);
		entityManager.persist(movimentacao3);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}
}
