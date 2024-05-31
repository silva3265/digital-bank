package br.com.digitalbank.execute;

import java.util.List;
import java.util.Scanner;

import br.com.digitalbank.entities.Agencia;
import br.com.digitalbank.entities.Cliente;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.entities.Endereco;
import br.com.digitalbank.model.AgenciaModel;
import br.com.digitalbank.model.ClienteModel;
import br.com.digitalbank.model.ContaModel;
import br.com.digitalbank.model.EnderecoModel;

public class Main {

	public static void main(String[] args) {

		
		System.out.println(" ** Bem Vindo ao DIGITAL BANK ** ");
		
		menuDeslogado();
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Cadastrar Nova Conta - \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia");
		
		
	}

	public static void depositar(Conta conta) {

		ContaModel accountModel = new ContaModel();
		Scanner sc = new Scanner(System.in);
		System.out.println("Quanto voce gostaria de depositar? ");
		double valor = sc.nextDouble();
		Boolean temContaCorrente = accountModel.temContaCorrente(conta.getId());
		Boolean temContaPoupanca = accountModel.temContaPoupanca(conta.getId());

		
		if (temContaCorrente && temContaPoupanca) {
			System.out.println("Gostaria de depositar em qual tipo de conta? \n1 - Conta Corrente \n2 - Conta Paoupança");
			Integer resposta = sc.nextInt();
			switch (resposta) {
			case 1:
				ContaCorrente accountCurrent = accountModel.getAccountCurrent(conta.getId());
				Boolean deposito = accountCurrent.deposito(valor);
				//accountModel.updateAccountCurrent(accountCurrent);
				break;

			default:
				break;
			}
		} else if (temContaCorrente) {
			
		} else if (temContaPoupanca) {

		}
		// Verificar se tem conta poupança ou conta corrente associada (ou se tem as
		// duas)
		// Verificar qual conta sera realizada o deposito(corrente ou poupança)

	}
	
	public static void cadastrar() {
		
		EnderecoModel enderecoModel = new EnderecoModel();
		
		ClienteModel clienteModel = new ClienteModel();
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" Insira seu Nome: ");
		String nome = sc.nextLine();
		
		System.out.println(" Insira seu CPF: ");
		String cpf = sc.nextLine();
		
		System.out.println(" Insira seu Telefone: ");
		String telefone = sc.nextLine();
		
		System.out.println(" Cadastro de Endereço: ");
		
		System.out.println("Rua: ");
		String rua = sc.nextLine();
		System.out.println("Numero: ");
		String numero = sc.nextLine();
		System.out.println("CEP: ");
		String cep = sc.nextLine();
		System.out.println("Complemento: ");
		String complemento = sc.nextLine();
		
		System.out.println("Digite uma Senha");
		String senha = sc.nextLine();
		
		System.out.println("Confirme a senha novamente");
		String segundaSenha = sc.nextLine();
		
		Endereco endereco = new Endereco(rua, Integer.parseInt(numero), cep, complemento);
		Long idGeradoEndereco = enderecoModel.cadastroEndereco(endereco);
		
		Cliente cliente = new Cliente(nome, cpf, idGeradoEndereco, telefone);
		
		
		Long idGeradoCliente = clienteModel.cadastroCliente(cliente);
		
		
		System.out.println("Seleciona uma Agencia mais proxima: ");
		
		for (Agencia agencia : new AgenciaModel().getAgencias()) { // pra cada agencia, da LISTA de Agencias
			agencia.imprimirAgencia();
			
		}
				
		String idAgencia = sc.nextLine();
		
		if (senha.equals(segundaSenha)) {
			
			ContaCorrente contaCorrente = new ContaCorrente(Long.parseLong(idAgencia), idGeradoCliente, senha);
			
			ContaModel contaModel = new ContaModel();
			contaModel.cadastroConta(contaCorrente);
		}
		
		
		
		
		// cadastrar o cliente - ok
		// e criar uma Conta Corrente  pro cliente - ok
		
	}
	
	public static void getLogin() {
		
		Scanner sc = new Scanner(System.in);
		ContaModel contaModel = new ContaModel();
		
		System.out.println("Insira o CPF: ");
		String cpf = sc.next();
		
		System.out.println("Insira a Senha: ");
		String senha = sc.next();
		
		if (verificarCPF(cpf) == true) {
			Conta contaLogin = contaModel.getLogin(cpf, senha);
			
			
			if (contaLogin == null) {
				System.out.println("Login e senha não conferem");
				menuDeslogado();
			}else {
				menuLogado();
			}
		}
		
	}
	
	public static void menuDeslogado() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" O que voce gostaria de Fazer? \n 1 - Login \n 2 - Cadastrar Nova Conta");
		
		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			getLogin();
			break;
			
		case 2:
			cadastrar();
		default:
			break;
		}
	}
	
	public static void menuLogado() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia");		
	
		int opcao = sc.nextInt();

//		switch (opcao) {
//		case 1:
//			depositar();
//			break;
//			
//		case 2:
//			saldo();
//			break;
//			
//		case 3:
//			saque();
//			break;
//			
//		case 4:
//			transferencia();
//			break;
//
//		default:
//			break;
//		}
//	
	}
	
	
	public static Boolean verificarCPF(String cpf) {
		
		ContaModel contaModel = new ContaModel();
		Conta contaCpf = contaModel.selectContaByCpf(cpf);
		
		if (contaCpf != null) {
			//System.out.println(" Voce Ja tem uma Conta Associada a esse Cpf: " + "Agencia: " + contaCpf.getIdAgencia() + "Conta: " + contaCpf.getId());
			return true;
		}
		else {
			return false;
		}
		
	}
	

	
}
