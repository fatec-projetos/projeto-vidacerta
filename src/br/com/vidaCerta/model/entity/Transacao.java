package br.com.vidaCerta.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transacao", schema="vidacerta")
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransacao;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConta")
	private Conta conta;
	
	private double valor;
	
	@Temporal(TemporalType.DATE)
    @Column(name="dataParaPagamento")
    private Date dataParaPagamento;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="dataFim")
    private Date dataFim;

	private String titulo;
	private String descricao;
	
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Date getDataParaPagamento() {
		return dataParaPagamento;
	}

	public void setDataParaPagamento(Date dataParaPagamento) {
		this.dataParaPagamento = dataParaPagamento;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

