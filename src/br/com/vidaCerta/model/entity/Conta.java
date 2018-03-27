package br.com.vidaCerta.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.vidaCerta.interfaces.IModelFinder;

@Entity
@Table(name = "conta", schema="vidacerta")
public class Conta implements IModelFinder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConta;
	
	private String icone;
	private String cor;
	private String descricao;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.idConta;
	}
}
