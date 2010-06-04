package com.appspot.safecash.dados;

public class Funcionario {
	
	private String nome;
	private double salario;
	private String cargo;
	private String CPF;
	private String endereco;
	private String telefone;
	private String email;
	
	public Funcionario(String nome, double salario, String cargo, String cPF,
			String endereco, String telefone, String email) {
		this.nome = nome;
		this.salario = salario;
		this.cargo = cargo;
		CPF = cPF;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}