package br.com.vidaCerta.model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.vidaCerta.model.entity.Usuario;
import br.com.vidaCerta.model.utils.Conexao;
import br.com.vidaCerta.model.utils.Criptografia;

public class UsuarioDAO {
	Conexao conexao = new Conexao();
	
	public Usuario validarUsuario(String login , String senha){
		
		
		Usuario usuario= null;
		
		EntityManager em = conexao.getConexao();
		
		em.getTransaction().begin();    
	    
		Query query = em.createQuery("SELECT u FROM Usuario u "
				+ "WHERE u.email = :email and u.senha=:senha");
		query.setParameter("email", login); 
		query.setParameter("senha", Criptografia.criptografar(senha)); 

		try{
		  usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
		  usuario = null;
		}
				
		em.getTransaction().commit();
		
		return usuario;
	}
	
	public Usuario cadastrarNovoUsuario(Usuario novoUsuario){
		
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();    
	    
		novoUsuario.setSenha(Criptografia.criptografar(novoUsuario.getSenha()));
		
		em.persist(novoUsuario);
				
		em.getTransaction().commit();
		
		return novoUsuario;
	}

	public String validarUsuario(Usuario novoUsuario) {
		EntityManager em = conexao.getConexao();
		String usuario= null;
		
		em.getTransaction().begin();    
	    
		Query query = em.createQuery("SELECT u.email FROM Usuario u WHERE u.idUsuario = :usuario");
		query.setParameter("usuario", novoUsuario.getIdUsuario()); 
		
		try{
		  usuario = (String) query.getSingleResult();
		}catch (NoResultException e) {
		  usuario = null;
		}
				
		em.getTransaction().commit();
		
		return usuario;
		
	}
	
	public Usuario salvarUsuario(Usuario usuario){
		
		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();    
	    
		usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		em.merge(usuario);
				
		em.getTransaction().commit();
		
		return usuario;
	}
	
}
