package com.andersonmarques.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.andersonmarques.dao.MovimentacaoDAO;
import com.andersonmarques.model.Conta;
import com.andersonmarques.model.TipoMovimentacao;
import com.andersonmarques.util.JPAUtil;

/**
 * Classe para testes usando funções com a JPQL
 * 
 * @author Anderson Marques
 * @version 1.0
 */
public class MainFuncoesJPQL {
	
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		EntityManager entityManager = jpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(1);
		
		MovimentacaoDAO mDao = new MovimentacaoDAO(entityManager);
		BigDecimal totalValor = mDao.getSomaTotalMovimentacoesComTipo(conta, TipoMovimentacao.SAIDA);
		System.out.println("Soma de todas as movimentações: "+totalValor);
		
		Double media = mDao.getMediaTotalMovimentacoesComTipo(conta, TipoMovimentacao.SAIDA);
		System.out.println("Média das movimentações: "+media);
		
		totalValor = mDao.getMaiorMovimentacaoComTipo(conta, TipoMovimentacao.SAIDA);
		System.out.println("Maior movimentação: "+totalValor);
		
		Long count = mDao.getQtdMovimentacoesComTipo(conta, TipoMovimentacao.SAIDA);
		System.out.println("Total de movimentações: "+count);
		
		//Query tipada
		List<Double> mediaDia= mDao.getMediaMovimentacoesPorDia(conta, TipoMovimentacao.ENTRADA);
		mediaDia.forEach(m -> System.out.println("Média por dia: "+m));
		
		entityManager.getTransaction().commit();
		entityManager.close();
		jpaUtil.fecharFactory();
	}
}
