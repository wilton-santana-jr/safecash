package com.appspot.safecash.dados;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.blobstore.BlobKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Modelo {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String nome;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private BlobKey blob;

	public Modelo(String nome, String descricao, BlobKey blob) {
		this.nome = nome;
		this.descricao = descricao;
		this.blob = blob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BlobKey getBlob() {
		return blob;
	}

	public void setBlob(BlobKey blob) {
		this.blob = blob;
	}
}
