package br.com.vidaCerta.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.vidaCerta.model.DAO.UsuarioDAO;
import br.com.vidaCerta.model.entity.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {

	private UsuarioDAO daoUsuario = new UsuarioDAO();;

	// Pagina de login
	private String login;
	private String senha;

	// Filtro login
	private Usuario usuario = null;
	private boolean logado;

	// Cadastro
	private Usuario novoUsuario = new Usuario();

	/**
	 * Método responsável por verificar se o usuário tem acesso ao sistema.
	 * @return
	 */
	public String entrar() {

		usuario = daoUsuario.validarUsuario(getLogin(), getSenha());

		if (usuario != null) {
			logado = true;
			return "minha-carteira/index.xhtml?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Usuário ou senha incorretos!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "";
	}

	/**
	 * Método responsável por deslogar o usuário.
	 * @return
	 */
	public String sair() {
		logado = false;

		FacesMessage msg = new FacesMessage("Sessãoo encerrada!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "index.xhtml?faces-redirect=true";
	}

	public void salvarUsuario() {
		daoUsuario.salvarUsuario(getUsuario());
		FacesMessage msg = new FacesMessage("Dados atualizados com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Método para cadastro e envio para página de login.
	 * @return
	 */
	public String cadastrarNovoUsuario() {

		if (validarUsuario()) {
			daoUsuario.cadastrarNovoUsuario(getNovoUsuario());
			setNovoUsuario(new Usuario());
			
			FacesMessage msg = new FacesMessage("Usuário cadastrado com sucesso!", "INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "index.xhtml?faces-redirect=true";
		} else {
			return "";
		}
	}

	public boolean validarUsuario() {

		String validacao;
		validacao = daoUsuario.validarUsuario(getNovoUsuario());

		if (validacao != null) {
			FacesMessage msg = new FacesMessage("Usuário não disponível", "");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}
		return true;
	}

	// Getters and Setters

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

}
