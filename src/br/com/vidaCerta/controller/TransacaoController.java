package br.com.vidaCerta.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.vidaCerta.model.DAO.TransacaoDAO;
import br.com.vidaCerta.model.entity.Conta;
import br.com.vidaCerta.model.entity.Transacao;

@ManagedBean
@ViewScoped
public class TransacaoController {

	@ManagedProperty("#{usuarioController}")
	private UsuarioController loginController;

	private TransacaoDAO daoTransacao = new TransacaoDAO();
	List<Transacao> listaTransacao = new ArrayList<Transacao>();

	Transacao novaTransacao = new Transacao();

	Transacao transacaoSelecionada = new Transacao();

	Conta contaSelecionada = new Conta();
	
	List<Conta> listaConta = new ArrayList<Conta>();

	Date dtIni = new Date();
	Date dtFim = new Date();
	
	@PostConstruct
	public void postConstruct () {
		ContaController ct = new ContaController();
		ct.carregarLista(loginController);
		this.listaConta = ct.getListaConta();
	}
	
	public void filtrar () {
		listaTransacao.clear();
		listaTransacao.addAll(daoTransacao.listaTransacao(contaSelecionada.getIdConta(), dtIni, dtFim));
	}

	public void salvarTransacao() {
		if (transacaoSelecionada != null)
			novaTransacao = transacaoSelecionada;

		novaTransacao.setConta(contaSelecionada);
		daoTransacao.cadastrarTransacao(novaTransacao);
		FacesMessage msg = new FacesMessage("Dados atualizados com sucesso!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgDetalheConta').hide();");
		this.filtrar();
		transacaoSelecionada = new Transacao();
		novaTransacao = new Transacao();
	}

	public List<Transacao> getListaTransacao() {
		return listaTransacao;
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public Transacao getNovaTransacao() {
		return novaTransacao;
	}

	public void setNovaTransacao(Transacao novaTransacao) {
		this.novaTransacao = novaTransacao;
	}

	public UsuarioController getLoginController() {
		return loginController;
	}

	public void setLoginController(UsuarioController loginController) {
		this.loginController = loginController;
	}

	public Transacao getTransacaoSelecionada() {
		return transacaoSelecionada;
	}

	public void setTransacaoSelecionada(Transacao transacaoSelecionada) {
		this.transacaoSelecionada = transacaoSelecionada;
	}

	public Date getDtIni() {
		return dtIni;
	}

	public void setDtIni(Date dtIni) {
		this.dtIni = dtIni;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public void setListaTransacao(List<Transacao> listaTransacao) {
		this.listaTransacao = listaTransacao;
	}

	public List<Conta> getListaConta () {
		return this.listaConta;
	}
	
	public void setListaConta (List<Conta> listaConta) {
		this.listaConta = listaConta;
	}
	
}
