package br.com.digitalbank.entities;

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

//	public BigDecimal withdraw(BigDecimal value) { // Saque
//		if (saldo) {
//
//		}
//		return value;
//
//	}

//	public BigDecimal deposit(double value) {
//
//		if (value > 0) {
//			saldo = saldo.add(BigDecimal.valueOf(value)); // converteu um double para Bigdecimal
//
//		}
//
//		return saldo;
//
//	}

}
