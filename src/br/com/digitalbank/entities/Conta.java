package br.com.digitalbank.entities;

public class Conta {

	private Long id;
	private Long idAgencia;
	private Long idCliente;
	private String password;

	public Conta(Long id, Long idAgencia, Long idCliente, String password) {
		this.idAgencia = idAgencia;
		this.idCliente = idCliente;
		this.id = id;
		this.password = password;
	}
	
	public Conta(Long idAgencia, Long idCliente, String password) {
		this.idAgencia = idAgencia;
		this.idCliente = idCliente;
		this.password = password;
	}

	public Conta(Long id, Long idAgencia, Long idCliente) {
		this.idAgencia = idAgencia;
		this.idCliente = idCliente;
		this.id = id;
	}
	
	public Conta(Long id) {
		this.id = id;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
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
