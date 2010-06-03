package com.appspot.safecach.dados;

import com.appspot.safecash.enuns.EnumStatusRequisicao;

public class RequisicaoContrato extends Requisicao {

	private String responsavel;
	private String CNPJ_CPF;
	private String nomeProjeto;
	private String endereco;
	private double valor;
	private String propostaNome;
	private byte[] propostaDados;
	private String observacao;
	
	public RequisicaoContrato(String descricao, Usuario usuario,
			EnumStatusRequisicao status, String responsavel, String cNPJCPF,
			String nomeProjeto, String endereco, double valor,
			String propostaNome, byte[] propostaDados, String observacao) {
		super(descricao, usuario, status);
		this.responsavel = responsavel;
		CNPJ_CPF = cNPJCPF;
		this.nomeProjeto = nomeProjeto;
		this.endereco = endereco;
		this.valor = valor;
		this.propostaNome = propostaNome;
		this.propostaDados = propostaDados;
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

	public String getPropostaNome() {
		return propostaNome;
	}

	public void setPropostaNome(String propostaNome) {
		this.propostaNome = propostaNome;
	}

	public byte[] getPropostaDados() {
		return propostaDados;
	}

	public void setPropostaDados(byte[] propostaDados) {
		this.propostaDados = propostaDados;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	
}