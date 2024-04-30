package br.com.digitalbank.execute;

import java.util.Scanner;

import br.com.digitalbank.entities.Conta;
import br.com.digitalbank.entities.ContaCorrente;
import br.com.digitalbank.model.ContaModel;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println(" ** Bem Vindo ao DIGITAL BANK ** ");
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Cadastrar Nova Conta - \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia");

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			verificarCPF(cpf);
			break;
			
		default:
			break;
		}
		
		System.out.println(" Acesse sua Conta: ");
		Long conta = sc.nextLong();

		System.out.println(" Digite sua Senha: ");
		String senha = sc.next();

		ContaModel contaModel = new ContaModel();
		Conta contaLogin = contaModel.login(conta, senha);

		if (contaLogin == null) {
			System.out.println("Login e senha não conferem");
		}

		System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia");

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			depositar(contaLogin);
			break;

		default:
			break;
		}

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
	
	public static void cadastrar(Conta conta) {
		
	}
	
	public static void verificarCPF(String cpf) {
		
	}
}
