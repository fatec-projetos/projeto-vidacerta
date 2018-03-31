package br.com.vidaCerta.model.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.vidaCerta.model.entity.Transacao;
import br.com.vidaCerta.model.utils.Conexao;

public class TransacaoDAO {
	Conexao conexao = new Conexao();
	
	public Transacao salvarTransacao(Transacao transacao){
		EntityManager em = conexao.getConexao();
		em.getTransaction().begin();
		em.persist(transacao);
				
		em.getTransaction().commit();
		em.close();
		
		return transacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listaTransacao (Integer idConta, Date dtInicio, Date dtFim) {
		List<Transacao> listaResultado = new ArrayList<Transacao>();
		
		if(idConta != null) {
			EntityManager em = conexao.getConexao();
					
			em.getTransaction().begin();
		    
			String sql = "SELECT t FROM Transacao t WHERE t.dataFim is null";
			if (idConta != null) sql += " and t.conta.idConta = :idConta";
			if (dtInicio != null) sql += " and t.dataParaPagamento >= :dtInicio";
			if (dtFim != null) sql += " and t.dataParaPagamento <= :dtFim";
			
			Query query = em.createQuery(sql);
			if (idConta != null) query.setParameter("idConta", idConta);
			if (dtInicio != null) query.setParameter("dtInicio", dtInicio);
			if (dtFim != null) query.setParameter("dtFim", dtFim);
			
			listaResultado.addAll(query.getResultList());
	
			em.getTransaction().commit();
			
			em.close();
		}
		
		return listaResultado;
	}
	
}
