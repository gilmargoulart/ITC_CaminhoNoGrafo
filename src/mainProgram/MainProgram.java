package mainProgram;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilitarios.TextReader;

/**
 * 
 * @author Gilmar J. A. Goulart
 * 
 * Descreva o problema e explique o algoritmo desenvolvido em forma de pseudocodigo (somente a parte principal do algoritmo).
 * Desenvolva o algoritmo para resolver o problema do Caminho.
 * Fa�a o algoritmo em qualquer linguagem de programa��o, (Sugest�es, Java, Python e C).
 * O algoritmo deve receber a matriz de adjacencia como entrada (realizar a leitura a partir de um arquivo texto)
 * e indicar se existe um caminho ou n�o. (um arquivo de sa�da indicando a resposta)
 * O problema e o algoritmo � apresentado no livro Introdu��o � Teoria da Computa��o, pg 274-276 (Dispon�vel na Biblioteca Virtual.)
 */
public class MainProgram {
	
	private static String CONFIG_FILE_PATH = "matriz.txt"; 
	private static String START_POINT = "A", END_POINT = "D"; 
	
	public static void main(String[] args) {
		//
		//Exibir o que veio por par�metro.
		int i = 0;
		for (String s : args) {
			System.out.println("args[" + i + "] = " + s);
			i++;
		}
		//
		//
		
		if (args.length > 0) {
			//Par�metro da Matriz de Adjac�ncia
			CONFIG_FILE_PATH	= args[0]; // Primeiro Par�metro � o caminho do arquivo.
			START_POINT			= args[1]; // Segundo par�metro � o ponto de origem.
			END_POINT			= args[2]; // Terceiro par�metro � o ponto de destino.
		}
		
		/*
		�Sobre a entrada <G,s,t> onde G � um grafo direcionado com nos S e T:
		1. Ponha uma marca sobre o no S.
		2. Repita o seguinte at� que nenhum n� adicional seja marcado:
		3. Fa�a uma varredura em todas as arestas de G. Se uma aresta (a,b) for encontrada indo de um n� marcado A para um n� n�o marcado B, marque o n� B.
		4. Se T estiver marcado, aceite. Caso contr�rio, rejeite.�
		*/

		try {
			//Ler arquivo de texto, com a Matriz de adjac�ncia.
			String txtJson = TextReader.readTextFile(CONFIG_FILE_PATH).replaceAll(" ", "");
			
			//Quebra cada linha em um vetor
			String [] txtVetor = txtJson.split("\n");
			
			//Primeira linha cont�m os cabe�alhos, assim como cada linha na mesma posi��o
			char [] cabecalhos = txtVetor[0].trim().toCharArray();
			
			//Inicializa a Matriz
			String [][] txtMatriz = new String[cabecalhos.length][cabecalhos.length];
			int linha = 0, coluna = 0;
			
			//Come�ar contagem em 1, pois 0 s�o os cabe�alhos
			for (int k = 1; k < txtVetor.length; k++) {
				
				//Remover quebras de linha
				String c = txtVetor[k].replaceAll("\\n|\\r", "");
				
				coluna = 0;
				//Alimentar a Matriz em cada posi��o
				for (int j = 1; j < c.length(); j++) {
					txtMatriz[linha][coluna] = String.valueOf(c.charAt(j));
					coluna++;
				}
				linha++;
				
			}
			
			
			for (String[] ss : txtMatriz) {
				for (String string : ss) {
					System.out.print(string);
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
