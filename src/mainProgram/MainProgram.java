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
 * Faça o algoritmo em qualquer linguagem de programação, (Sugestões, Java, Python e C).
 * O algoritmo deve receber a matriz de adjacencia como entrada (realizar a leitura a partir de um arquivo texto)
 * e indicar se existe um caminho ou não. (um arquivo de saída indicando a resposta)
 * O problema e o algoritmo é apresentado no livro Introdução à Teoria da Computação, pg 274-276 (Disponível na Biblioteca Virtual.)
 */
public class MainProgram {
	
	public static String CONFIG_FILE_PATH = "matriz.json"; 
	
	public static void main(String[] args) {
		
		//Parâmetro da Matriz de Adjacência
		CONFIG_FILE_PATH = args[0];
		
		/*
		“Sobre a entrada <G,s,t> onde G é um grafo direcionado com nos S e T:
		1. Ponha uma marca sobre o no S.
		2. Repita o seguinte até que nenhum né adicional seja marcado:
		3. Faça uma varredura em todas as arestas de G. Se uma aresta (a,b) for encontrada indo de um nó marcado A para um nó não marcado B, marque o nó B.
		4. Se T estiver marcado, aceite. Caso contrário, rejeite.”
		*/
		
		Gson g = new Gson();
		
		//Ler arquivo de texto, com a configuração armazenada em Json
		String txtJson = null;
		try {
			txtJson = TextReader.readTextFile(CONFIG_FILE_PATH);
			
			// TODO Instanciar classe de configuração com a matriz
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
