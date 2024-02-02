package br.com.digitalbank.entities;

import java.util.Scanner;

public class Account {

	private Long idAgencia;
	private Integer idCliente;
	private Long id;
	private String password;

	public Account(Long idAgencia, Integer idCliente, Long id, String password) {
		this.idAgencia = idAgencia;
		this.idCliente = idCliente;
		this.id = id;
		this.password = password;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean deposito(double valor) {
		return true;
	}

}
