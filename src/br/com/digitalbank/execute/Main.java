package br.com.digitalbank.execute;

import java.util.Scanner;

import br.com.digitalbank.entities.Account;
import br.com.digitalbank.model.AccountModel;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println(" ** Bem Vindo ao DIGITAL BANK ** ");
	
		System.out.println(" Acesse sua Conta: ");
		Long conta = sc.nextLong();
		
		System.out.println(" Digite sua Senha: ");
		String senha = sc.next();
		
		AccountModel accountModel =  new AccountModel();
		Account account = accountModel.login(conta, senha);
		
		if (account == null) {
			System.out.println("Login e senha não conferem");
		}
		
		System.out.println(" O que voce gostaria de Fazer? \n1 - Deposito \n2 - Saldo \n3 - Saque \n4 - Tranferencia");
	

		int opcao = sc.nextInt();
		
		switch (opcao) {
		case 1:
			depositar();
			break;

		default:
			break;
		}
	
	}

	public static void depositar(Account account) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quanto voce gostaria de depositar? ");
		double valor = sc.nextDouble();
		//Verificar qual conta sera realizada o deposito(corrente ou poupança)
		
	}

}
