package entities;

import java.util.Scanner;

public class Matriz {
	private double[][] matriz;
	private int size;

	public Matriz(int size) {
		this.size = size;
		this.matriz = new double[size][size];
	}

	public void preencherMatriz(Scanner sc) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("Digite o valor da posição a" + (i + 1) + (j + 1) + ": ");
				matriz[i][j] = sc.nextDouble();
			}
		}
	}

	public double calcularDeterminante() {
		if (size == 2) {
			return matriz[0][0] * matriz[1][1] - matriz[1][0] * matriz[0][1];
		} else if (size == 3) {
			return (matriz[0][0] * matriz[1][1] * matriz[2][2] + matriz[0][1] * matriz[1][2] * matriz[2][0]
					+ matriz[0][2] * matriz[1][0] * matriz[2][1])
					- (matriz[0][2] * matriz[1][1] * matriz[2][0] + matriz[0][0] * matriz[1][2] * matriz[2][1]
							+ matriz[0][1] * matriz[1][0] * matriz[2][2]);
		} else {
			return 0.0;
		}
	}

	public void somar(Matriz outraMatriz) {
		if (size == outraMatriz.size) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					matriz[i][j] += outraMatriz.matriz[i][j];
				}
			}
		} else {
			System.out.println("As matrizes têm tamanhos diferentes e não podem ser somadas.");
		}
	}

	public void calcularInversa() {
		if (size == 2) {
			double det = calcularDeterminante();
			if (det == 0) {
				System.out.println("A matriz não tem uma matriz inversa, pois o determinante é zero.");
				return;
			}

			double temp = matriz[0][0];
			matriz[0][0] = matriz[1][1] / det;
			matriz[1][1] = temp / det;
			matriz[0][1] = -matriz[0][1] / det;
			matriz[1][0] = -matriz[1][0] / det;
		}
		if (size != 3) {
			System.out.println("A função de cálculo da matriz inversa é suportada apenas para matrizes 3x3 ou 2x2 por enquanto.");
			return;
		}

		double det = calcularDeterminante();
		if (det == 0) {
			System.out.println("A matriz não tem uma matriz inversa, pois o determinante é zero.");
			return;
		}

		double[][] cofatores = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				double menorDet = calcularMenorDeterminante(i, j);
				cofatores[i][j] = Math.pow(-1, i + j) * menorDet;
			}
		}

		double[][] adjunta = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				adjunta[i][j] = cofatores[j][i];
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matriz[i][j] = adjunta[i][j] / det;
			}
		}
	}

	public void imprimirMatrizInversa() {
		System.out.println("Matriz Inversa:");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.println("a" + (i + 1) + (j + 1) + ": " + matriz[i][j]);
			}
		}
		System.out.println();
	}

	private double calcularMenorDeterminante(int linha, int coluna) {
		double[][] menorMatriz = new double[size - 1][size - 1];
		int menorI = 0;
		int menorJ = 0;

		for (int i = 0; i < size; i++) {
			if (i == linha) {
				continue;
			}
			for (int j = 0; j < size; j++) {
				if (j == coluna) {
					continue;
				}
				menorMatriz[menorI][menorJ] = matriz[i][j];
				menorJ++;
			}
			menorI++;
			menorJ = 0;
		}

		Matriz menor = new Matriz(size - 1);
		menor.matriz = menorMatriz;

		return menor.calcularDeterminante();
	}

	public void imprimirMatriz() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.println("a" + (i + 1) + (j + 1) + ": " + matriz[i][j]);
			}
		}
	}
}