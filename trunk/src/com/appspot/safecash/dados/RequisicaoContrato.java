package com.appspot.safecash.dados;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.enuns.EnumTipoRequisicao;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(customStrategy = "complete-table")
public class RequisicaoContrato extends Requisicao {

	@Persistent
	private String responsavel;
	
	@Persistent
	private String CNPJ_CPF;
	
	@Persistent
	private String nomeProjeto;
	
	@Persistent
	private String endereco;
	
	@Persistent
	private double valor;
	
	@Persistent
	private BlobKey arquivoProposta;
	
	@Persistent
	private String observacao;
	
	public RequisicaoContrato(String assunto, String descricao, EnumStatusRequisicao status, 
			String responsavel, String cNPJCPF, String nomeProjeto,
			String endereco, double valor, BlobKey arquivoProposta,
			String observacao, Date data) {
		super(assunto, descricao, status, EnumTipoRequisicao.CONTRATO, data);
		this.responsavel = responsavel;
		CNPJ_CPF = cNPJCPF;
		this.nomeProjeto = nomeProjeto;
		this.endereco = endereco;
		this.valor = valor;
		this.arquivoProposta = arquivoProposta;
		this.observacao = observacao;
	}
	
	public RequisicaoContrato(String assunto, String descricao, EnumStatusRequisicao status, 
			String responsavel, String cNPJCPF, String nomeProjeto,
			String endereco, double valor, BlobKey arquivoProposta,
			String observacao, Date data, Key key) {
		super(assunto, descricao, status, EnumTipoRequisicao.CONTRATO, data, key);
		this.responsavel = responsavel;
		CNPJ_CPF = cNPJCPF;
		this.nomeProjeto = nomeProjeto;
		this.endereco = endereco;
		this.valor = valor;
		this.arquivoProposta = arquivoProposta;
		this.observacao = observacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getCNPJ_CPF() {
		return CNPJ_CPF;
	}

	public void setCNPJ_CPF(String cNPJCPF) {
		CNPJ_CPF = cNPJCPF;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public BlobKey getArquivoProposta() {
		return arquivoProposta;
	}

	public void setArquivoProposta(BlobKey arquivoProposta) {
		this.arquivoProposta = arquivoProposta;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}