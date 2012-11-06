package br.com.caelum.projeto.dao.teste;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.projeto.dao.MovimentacaoDAO;
import br.com.caelum.projeto.infra.JPAUtil;
import br.com.caelum.projeto.modelo.Conta;
import br.com.caelum.projeto.modelo.Movimentacao;
import br.com.caelum.projeto.modelo.TipoMovimentacao;

public class MovimentacaoDAOTeste {

	private EntityManager entityManager;

	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
	}

	@Test
	public void deveriaRetornarAsMovimentacoes_UsandoJPQL() throws Exception {
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);
		
		List<Movimentacao> movimentacoes = movimentacaoDAO.lista_com_jpql();
		
		assertEquals(8, movimentacoes.size());
	}

	@Test
	public void deveriaRetornarAsMovimentacoes_UsandoCriteria() throws Exception {
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);
		
		List<Movimentacao> movimentacoes = movimentacaoDAO.lista_com_criteria();
		
		assertEquals(8, movimentacoes.size());
	}
	
	@Test
	public void deveriaRetornarAsMovimentacoesDadaUmaContaEUmTipo() throws Exception {
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);
		
		Conta conta = entityManager.find(Conta.class, 17l);
		
		List<Movimentacao> movimentacoes = movimentacaoDAO.buscaPorContaETipo_Concatenando(conta, TipoMovimentacao.ENTRADA);
		
		assertEquals(2, movimentacoes.size());
	}

	@Test
	public void deveriaRetornarAsMovimentacoesDadaUmaContaEUmTipo_LevementeRefatorado() throws Exception {
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);
		
		Conta conta = entityManager.find(Conta.class, 17l);
		
		List<Movimentacao> movimentacoes = movimentacaoDAO.buscaPorContaETipo_Concatenando_LevementeRefatorado(conta, TipoMovimentacao.ENTRADA);
		
		assertEquals(2, movimentacoes.size());
	}
}
