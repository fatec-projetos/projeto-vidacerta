package br.com.vidaCerta.model.entity;

import java.util.Date;

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
    @JoinColumn(name = "idTransacaoOrigem")
	private Transacao transacaoOrigem; // pode ser null só é usado em caso de recorrência mensal
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConta")
	private Conta conta;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
	private Categoria categoria;
    
	private double valor;
	private TipoTransacao tipoTransacao;
	private Integer quantidadeParcela;
	private boolean pagou;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataParaPagamento;
	private boolean lembrarPagamento;
	private String titulo;
	private String descricao;
	
	
	
	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Transacao getTransacaoOrigem() {
		return transacaoOrigem;
	}

	public void setTransacaoOrigem(Transacao transacaoOrigem) {
		this.transacaoOrigem = transacaoOrigem;
	}

	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public boolean isPagou() {
		return pagou;
	}

	public void setPagou(boolean pagou) {
		this.pagou = pagou;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataParaPagamento() {
		return dataParaPagamento;
	}

	public void setDataParaPagamento(Date dataParaPagamento) {
		this.dataParaPagamento = dataParaPagamento;
	}

	public boolean isLembrarPagamento() {
		return lembrarPagamento;
	}

	public void setLembrarPagamento(boolean lembrarPagamento) {
		this.lembrarPagamento = lembrarPagamento;
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

	public enum TipoTransacao {
		Recorrencia,
		Mensal,
		Unica
	};
}

