package application;

import java.util.Scanner;

import entities.Matriz;

public class Program {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o tamanho da matriz: ");
		int size = sc.nextInt();

		Matriz matriz = new Matriz(size);

		matriz.preencherMatriz(sc);

		int escolha = 1;
		while (escolha != 0) {
			escolha = menu(sc);
			if (escolha < 0 || escolha > 3) {
				System.out.println("Escolha um valor válido.");
				System.out.println();
			}
			if (escolha == 1) {
				double determinante = matriz.calcularDeterminante();
				System.out.println("Determinante: " + determinante);
			} else if (escolha == 2) {
				System.out.println("Agora os valores da matriz que será adicionada.");
				Matriz outraMatriz = new Matriz(size);
				outraMatriz.preencherMatriz(sc);
				matriz.somar(outraMatriz);
				System.out.println("A soma das matrizes resultou em:");
				matriz.imprimirMatriz();
			} else if (escolha == 3) {
				matriz.calcularInversa();
				matriz.imprimirMatrizInversa();
			}
		}

		System.out.println("FIM");
		sc.close();
	}

	public static int menu(Scanner sc) {
		System.out.println("Calculadora de Matrizes");
		System.out.println("Escolha uma das opções: ");
		System.out.println("1 - Determinantes");
		System.out.println("2 - Soma de Matrizes");
		System.out.println("3 - Matriz Inversa");
		int escolha = sc.nextInt();
		return escolha;
	}
}