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
 * Faça o algoritmo em qualquer linguagem de programação, (Sugestões, Java, Python e C).
 * O algoritmo deve receber a matriz de adjacencia como entrada (realizar a leitura a partir de um arquivo texto)
 * e indicar se existe um caminho ou não. (um arquivo de saída indicando a resposta)
 * O problema e o algoritmo é apresentado no livro Introdução à Teoria da Computação, pg 274-276 (Disponível na Biblioteca Virtual.)
 */
public class MainProgram {
	
	private static String CONFIG_FILE_PATH = "matriz.txt"; 
	private static String START_POINT = "A", END_POINT = "D"; 
	
	public static void main(String[] args) {
		//
		//Exibir o que veio por parâmetro.
		int i = 0;
		for (String s : args) {
			System.out.println("args[" + i + "] = " + s);
			i++;
		}
		//
		//
		
		if (args.length > 0) {
			//Parâmetro da Matriz de Adjacência
			CONFIG_FILE_PATH	= args[0]; // Primeiro Parâmetro é o caminho do arquivo.
			START_POINT			= args[1]; // Segundo parâmetro é o ponto de origem.
			END_POINT			= args[2]; // Terceiro parâmetro é o ponto de destino.
		}
		
		/*
		“Sobre a entrada <G,s,t> onde G é um grafo direcionado com nos S e T:
		1. Ponha uma marca sobre o no S.
		2. Repita o seguinte até que nenhum nó adicional seja marcado:
		3. Faça uma varredura em todas as arestas de G. Se uma aresta (a,b) for encontrada indo de um nó marcado A para um nó não marcado B, marque o nó B.
		4. Se T estiver marcado, aceite. Caso contrário, rejeite.”
		*/

		try {
			//Ler arquivo de texto, com a Matriz de adjacência.
			String txtJson = TextReader.readTextFile(CONFIG_FILE_PATH).replaceAll(" ", "");
			
			//Quebra cada linha em um vetor
			String [] txtVetor = txtJson.split("\n");
			
			//Primeira linha contém os cabeçalhos, assim como cada linha na mesma posição
			char [] cabecalhos = txtVetor[0].trim().toCharArray();
			
			//Inicializa a Matriz
			String [][] txtMatriz = new String[cabecalhos.length][cabecalhos.length];
			int linha = 0, coluna = 0;
			
			//Começar contagem em 1, pois 0 são os cabeçalhos
			for (int k = 1; k < txtVetor.length; k++) {
				
				//Remover quebras de linha
				String c = txtVetor[k].replaceAll("\\n|\\r", "");
				
				coluna = 0;
				//Alimentar a Matriz em cada posição
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
