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
	
	public Transacao cadastrarTransacao(Transacao novaTransacao){
		
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();    
	    
		if(novaTransacao.getIdTransacao() != null && novaTransacao.getIdTransacao() != 0) {
			em.merge(novaTransacao);
		} else {
			em.persist(novaTransacao);
		}
				
		em.getTransaction().commit();
		
		return novaTransacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listaTransacao (Integer idConta, Date dtInicio, Date dtFim) {
		List<Transacao> listaResultado = new ArrayList<Transacao>();
		
		if(idConta != null) {
			EntityManager em = conexao.getConexao();
					
			em.getTransaction().begin();
		    
			String sql = "SELECT t FROM Transacao t WHERE t.conta.idConta = :idConta";
			
			Query query = em.createQuery(sql);
			query.setParameter("idConta", idConta);
			
			listaResultado.addAll(query.getResultList());
	
			em.getTransaction().commit();
			
			em.close();
		}
		
		return listaResultado;
	}
	
}
