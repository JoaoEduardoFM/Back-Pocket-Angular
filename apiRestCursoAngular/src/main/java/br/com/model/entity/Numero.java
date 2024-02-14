package br.com.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Numero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNumero;

	private String numero;

	public Long getIdNumero() {
		return idNumero;
	}

	public void setIdNumero(Long idNumero) {
		this.idNumero = idNumero;
	}

	public Long getId() {
		return idNumero;
	}

	public void setId(Long idNumero) {
		this.idNumero = idNumero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
