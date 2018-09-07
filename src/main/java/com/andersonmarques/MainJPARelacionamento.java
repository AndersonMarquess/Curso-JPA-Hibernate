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
//		Categoria categoria2 = new Categoria("Negócio");
		Categoria categoria2 = new Categoria();
		categoria2.setId(2);
		
		//Conta conta = new Conta("Triss", "951", "7431", "Banco de Novigrad");
		Conta conta = new Conta();
		conta.setId(1);
		
		Movimentacao movimentacao1 = new Movimentacao(BigDecimal.valueOf(25.90d), TipoMovimentacao.SAIDA,
				Calendar.getInstance(), "Alfaiate", conta);
		Movimentacao movimentacao2 = new Movimentacao(BigDecimal.valueOf(150.00d), TipoMovimentacao.SAIDA,
				Calendar.getInstance(), "Ferreiro", conta);
		Movimentacao movimentacao3 = new Movimentacao(BigDecimal.valueOf(75.00d), TipoMovimentacao.ENTRADA,
				Calendar.getInstance(), "Recompensa", conta);

		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Movimentacao movimentacao4 = new Movimentacao(BigDecimal.valueOf(90.00d), TipoMovimentacao.SAIDA, 
				amanha, "Artesã", conta);
		
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));
		movimentacao3.setCategorias(Arrays.asList(categoria2));
		
		movimentacao4.setCategorias(Arrays.asList(categoria2));

		
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
		entityManager.persist(movimentacao4);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}
}
