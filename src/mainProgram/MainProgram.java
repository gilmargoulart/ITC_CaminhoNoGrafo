package mainProgram;

import java.io.FileNotFoundException;

import com.google.gson.Gson;

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
	
	public static String CONFIG_FILE_PATH = "matriz.json"; 
	
	public static void main(String[] args) {
		
		//Par�metro da Matriz de Adjac�ncia
		CONFIG_FILE_PATH = args[0];
		
		/*
		�Sobre a entrada <G,s,t> onde G � um grafo direcionado com nos S e T:
		1. Ponha uma marca sobre o no S.
		2. Repita o seguinte at� que nenhum n� adicional seja marcado:
		3. Fa�a uma varredura em todas as arestas de G. Se uma aresta (a,b) for encontrada indo de um n� marcado A para um n� n�o marcado B, marque o n� B.
		4. Se T estiver marcado, aceite. Caso contr�rio, rejeite.�
		*/
		
		Gson g = new Gson();
		
		//Ler arquivo de texto, com a configura��o armazenada em Json
		String txtJson = null;
		try {
			txtJson = TextReader.readTextFile(CONFIG_FILE_PATH);
			
			// TODO Instanciar classe de configura��o com a matriz
			//configuracao = g.fromJson(txtJson, Configuracao.class);
		
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int i = 0;
		
		for (String s : args) {
			System.out.println("args[" + i + "] = " + s);
			i++;
		}
	}
}
