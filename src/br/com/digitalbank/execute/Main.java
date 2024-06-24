package br.com.digitalbank.execute;

import java.util.Scanner;

import br.com.digitalbank.entities.Agencia;
import br.com.digitalbank.entities.Cliente;
import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.entities.ContaPoupanca;
import br.com.digitalbank.entities.Endereco;
import br.com.digitalbank.model.AgenciaModel;
import br.com.digitalbank.model.ClienteModel;
import br.com.digitalbank.model.ContaModel;
import br.com.digitalbank.model.EnderecoModel;

public class Main {

	public static void main(String[] args) {

		System.out.println(" ** Bem Vindo ao DIGITAL BANK ** ");

		menuDeslogado();

	}

	public static void depositar(Conta conta) {

		ContaModel contaModel = new ContaModel();
		Scanner sc = new Scanner(System.in);
		System.out.println("Quanto voce gostaria de depositar?");
		double valor = sc.nextDouble();
		// Vamos verificar se ele tem um conta corrente e uma conta poupança
		Boolean temContaCorrente = contaModel.temContaCorrente(conta.getId());
		Boolean temContaPoupanca = contaModel.temContaPoupanca(conta.getId());

		if (temContaCorrente && temContaPoupanca) {
			System.out.println("Gostaria de depositar em qual tipo de conta? \n1 - Conta Corrente \n2 - Conta Poupança");
			Integer resposta = sc.nextInt();
			switch (resposta) {
			case 1:
				contaModel.depositoContaCorrente(conta, valor); 
				break;
			case 2:
				contaModel.depositoContaPoupanca(conta, valor);
				break;
				
			default:
				break;
			}
		} else if (temContaCorrente) {
			contaModel.depositoContaCorrente(conta, valor);
		} else if (temContaPoupanca) {
			contaModel.depositoContaPoupanca(conta, valor);

		}
		// Verificar se tem conta poupança ou conta corrente associada (ou se tem as
		// duas)
		// Verificar qual conta sera realizada o deposito(corrente ou poupança)

	}
	
	public static void saldo(Conta conta) {
		
		ContaModel contaModel = new ContaModel();
	
		Scanner sc = new Scanner(System.in);
		
			Integer resposta;
			Boolean temContaCorrente = contaModel.temContaCorrente(conta.getId());
			Boolean temContaPoupanca = contaModel.temContaPoupanca(conta.getId());

			if (temContaCorrente && temContaPoupanca) {
				System.out.println("Gostaria de Consultar o Saldo de qual tipo de conta? \n1 - Conta Corrente \n2 - Conta Poupança");
				resposta = sc.nextInt();
				switch (resposta) {
				case 1:
					Double saldoContaCorrente = contaModel.getSaldoContaCorrente(conta.getId()); 
					System.out.println("O Saldo da sua Conta Corrente é: R$: " + saldoContaCorrente);
					break;
				case 2:
					Double saldoContaPoupanca = contaModel.getSaldoContaPoupanca(conta.getId());
					System.out.println("O Saldo da sua Conta Poupanca é: R$: " + saldoContaPoupanca);
					break;
					
				default:
					break;
				}
			} else if (temContaCorrente) {
				Double saldo = contaModel.getSaldoContaCorrente(conta.getId()); 
				System.out.println("O Saldo da sua Conta Corrente é: " + saldo);
			} else if (temContaPoupanca) {
				contaModel.getSaldoContaPoupanca(conta.getId());

			}
		}

	public static void cadastrar() {

		EnderecoModel enderecoModel = new EnderecoModel();

		ClienteModel clienteModel = new ClienteModel();
		
		Long idConta = null;

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
			idConta = cadastroContaCorrente(Long.parseLong(idAgencia), idGeradoCliente, senha);
			
		}
		
		System.out.println(" ** Conta Corrente Cadastrada com Sucesso ** ");
		System.out.println(" Gostaria Tambem de Criar uma Conta Poupanca? \n1 - Sim, \n2 - Não ");
		
		String resposta = sc.nextLine();
		switch (Integer.parseInt(resposta)) {
		case 1:
			cadastroContaPoupanca(idConta);
			System.out.println("Conta Poupanca Cadastrada com Sucesso!");
			break;

		default:
			break;
		}
		
		System.out.println(" ** Agora Faça o Login no App do Banco ** ");
		
		getLogin();

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
			} else {
				menuLogado(contaLogin);
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

	public static void menuLogado(Conta conta) {
		ContaModel contaModel = new ContaModel();
		
		Boolean temConta = contaModel.temContaPoupanca(conta.getId());

		Scanner sc = new Scanner(System.in);
		
		if (temConta == false) {
			System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia, \n5 - Cadastrar Conta Poupanca");
		}else {
			System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia ");
		}

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			depositar(conta);
			menuLogado(conta); // Recursividade
			break;
			
		case 2:
			saldo(conta);
			menuLogado(conta); // Recursividade
			break;
			
		case 3:
			saque();
			break;
//			
//		case 4:
//			transferencia();
//			break;
			
		case 5:
			cadastroContaPoupanca(conta.getId());
			System.out.println(" ** Conta Poupança Cadastrada com Sucesso ** ");
			menuLogado(conta); // Recursividade
			break;

		default:
			break;
		}
	
	}

	public static Boolean verificarCPF(String cpf) {

		ContaModel contaModel = new ContaModel();
		Conta contaCpf = contaModel.selectContaByCpf(cpf);

		if (contaCpf != null) {
			// System.out.println(" Voce Ja tem uma Conta Associada a esse Cpf: " +
			// "Agencia: " + contaCpf.getIdAgencia() + "Conta: " + contaCpf.getId());
			return true;
		}
			System.out.println(" CPF não encontrado na Base de Dados!");
			menuDeslogado();
			return false;
			
	}
	
	public static Long cadastroContaCorrente(Long idAgencia, Long idGeradoCliente, String senha ) {
		ContaCorrente contaCorrente = new ContaCorrente(idAgencia, idGeradoCliente, senha);

		ContaModel contaModel = new ContaModel();
		return contaModel.cadastroContaCorrente(contaCorrente);
		
	}
	
	public static void cadastroContaPoupanca(Long id) {
		ContaPoupanca contaPoupanca = new ContaPoupanca(id);

		ContaModel contaModel = new ContaModel();
		contaModel.cadastroContaPoupanca(contaPoupanca);
		
	}

}
