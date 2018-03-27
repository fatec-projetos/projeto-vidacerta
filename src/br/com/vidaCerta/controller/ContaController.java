package br.com.vidaCerta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.vidaCerta.model.DAO.ContaDAO;
import br.com.vidaCerta.model.entity.Conta;

@ManagedBean
@ViewScoped
public class ContaController {
	
	@ManagedProperty("#{usuarioController}")
	private UsuarioController loginController;
	
	private ContaDAO daoConta = new ContaDAO();;
	List<Conta> listaConta = new ArrayList<Conta>();

	Conta novaConta = new Conta();
	
	Conta contaSelecionada = new Conta();
	
	@PostConstruct
	public void postConstruct(){
		this.carregarLista(loginController);
	}

	public void carregarLista (UsuarioController usr) {
		listaConta.clear();
		listaConta.addAll(daoConta.listaConta(usr.getUsuario().getIdUsuario()));
	}
	
	public void salvarConta() {
		if(contaSelecionada != null)
			novaConta = contaSelecionada;
		
		novaConta.setUsuario(loginController.getUsuario());
		daoConta.cadastrarConta(novaConta);
		FacesMessage msg = new FacesMessage("Dados atualizados com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgDetalheConta').hide();");
		this.postConstruct();
		contaSelecionada = null;
		novaConta = new Conta();
	}
	
	public List<Conta> getListaConta() {
		return listaConta;
	}
	
	public Conta getContaSelecionada() {
		return contaSelecionada;
	}
	
	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}
	
	public Conta getNovaConta() {
		return novaConta;
	}
	
	public void setNovaConta(Conta novaConta) {
		this.novaConta = novaConta;
	}
	
	public UsuarioController getLoginController() {
		return loginController;
	}

	public void setLoginController(UsuarioController loginController) {
		this.loginController = loginController;
	}
	
	
}
