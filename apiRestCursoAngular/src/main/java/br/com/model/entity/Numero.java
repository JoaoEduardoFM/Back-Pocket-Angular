package br.com.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Numero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long usuarioPk;

	private String numero;

	public Long getUsuarioPk() {
		return usuarioPk;
	}

	public void setUsuarioPk(Long usuarioPk) {
		this.usuarioPk = usuarioPk;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
