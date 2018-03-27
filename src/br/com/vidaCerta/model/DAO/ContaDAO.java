package br.com.vidaCerta.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.vidaCerta.interfaces.IDAOFinder;
import br.com.vidaCerta.interfaces.IModelFinder;
import br.com.vidaCerta.model.entity.Conta;
import br.com.vidaCerta.model.utils.Conexao;

public class ContaDAO implements IDAOFinder {
	Conexao conexao = new Conexao();
	
	public Conta cadastrarConta(Conta novaConta){
		
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();    
	    
		if(novaConta.getIdConta() != null && novaConta.getIdConta() != 0) {
			em.merge(novaConta);
		} else {
			em.persist(novaConta);
		}
				
		em.getTransaction().commit();
		
		return novaConta;
	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> listaConta (Integer idUsuario) {
		List<Conta> listaResultado = new ArrayList<Conta>();
		
		EntityManager em = conexao.getConexao();
				
		em.getTransaction().begin();
	    
		String sql = "SELECT c FROM Conta c WHERE c.usuario.idUsuario = :idUsuario";
		
		Query query = em.createQuery(sql);
		query.setParameter("idUsuario", idUsuario);
		
		listaResultado.addAll(query.getResultList());

		em.getTransaction().commit();
		
		em.close();
		
		return listaResultado;
	}
	

	public Conta buscarContaById(Integer idConta) {
		
		Conta conta;

		EntityManager em = conexao.getConexao();
		
		em.getTransaction().begin();

			try {
				conta= em.find(Conta.class, idConta);
			} catch (Exception e) {
				conta= null;
			}

		em.getTransaction().commit();
		
		em.close();
		
		return conta;
	}



	@Override
	public IModelFinder getById(int id) {
		// TODO Auto-generated method stub
		return (IModelFinder) this.buscarContaById(id);
	}
	
}
