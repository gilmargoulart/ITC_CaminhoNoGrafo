package mainProgram;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import utilitarios.*;

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
	private static String OUTPUT_FILE_PATH = "matriz_resultado.txt";
	private static String START_POINT = "A", END_POINT = "A"; 
	private static int POS_START_POINT = -1, POS_END_POINT = -1;
	
	private static boolean START_ELEMENT_ENCONTRADO = false;
	private static boolean END_ELEMENT_ENCONTRADO = false;
	
	//private static List<Elemento> ELEMENTOS = new ArrayList<>();
	
	public static void main(String[] args) {
		List<Elemento> ELEMENTOS = new ArrayList<>();
		
		//Exibir o que veio por parâmetro.
		System.out.println("Parâmetros recebidos: ");
		for (int i = 0; i < args.length; i++) {
			System.out.println("args[" + i + "] = " + args[i]);
		}
		
		if (args.length > 0) {
			//Parâmetro da Matriz de Adjacência
			CONFIG_FILE_PATH	= args[0]; // Primeiro Parâmetro é o caminho do arquivo.
			START_POINT			= args[1]; // Segundo parâmetro é o ponto de origem.
			END_POINT			= args[2]; // Terceiro parâmetro é o ponto de destino.
		}
		
		/*
		“Sobre a entrada <G,s,t> onde G é um grafo direcionado com nos S e T:
		1. Ponha uma marca sobre o no S.
		2. Repita o seguinte até que nenhum né adicional seja marcado:
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
			
			//Criar todos os "cabeçalhos"
			for (int i = 0; i < cabecalhos.length; i++) {
				ELEMENTOS.add(i, new Elemento(cabecalhos[i]));
			}
			
			int cabecalhosLen = cabecalhos.length;
			
			//Inicializa a Matriz
			String [][] txtMatriz = new String[cabecalhosLen][cabecalhosLen];
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
				
				if (cabecalhos[linha] == START_POINT.charAt(0)) {
					POS_START_POINT = linha;
				}
				if (cabecalhos[linha] == END_POINT.charAt(0)) {
					POS_END_POINT = linha;
				}
				
				linha++;
			}
			
			//System.out.println("POS_START: " + POS_START_POINT + ", POS_END: " + POS_END_POINT);
			
			System.out.println("Matriz:");
			for (String[] ss : txtMatriz) {
				for (String string : ss) {
					System.out.print(string);
				}
				System.out.println();
			}

			//Inicializar uma lista com o mapeamento dos caminhos.
			for (int i = 0; i < txtMatriz.length; i++) {
				for (int j = 0; j < txtMatriz[i].length; j++) {
					if (txtMatriz[i][j].equals("1")) {
						Elemento eOrigem = ELEMENTOS.get(i);
						Elemento eDestino = ELEMENTOS.get(j);
						eOrigem.addChild(eDestino);
					}
				}
			}
			
			//Começar a rotina no elemento inicial
			Elemento base = ELEMENTOS.get(POS_START_POINT);
			buscarCaminho(base);
			
			//Exibir o resultado
			String resultado = START_ELEMENT_ENCONTRADO && END_ELEMENT_ENCONTRADO ? "Caminho ENCONTRADO. :)":"Caminho NÃO encontrado. :(";
			System.out.println(resultado);
			
			//Gravar o resultado em um arquivo.
			System.out.println("Gravando o resultado para o arquivo: " + "\"" + OUTPUT_FILE_PATH + "\"");
			TextWriter.writeTextToFile(OUTPUT_FILE_PATH, resultado);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void buscarCaminho(Elemento base){
		//Verificar o incial e o final apenas uma vez, pois se encontrar o ponto inicial
		//então o ponto final deverá ser encontrado no elementos filhos.
		if (base.getName().equals(START_POINT)){
			START_ELEMENT_ENCONTRADO = true;
		} else if (base.getName().equals(END_POINT)) {
			END_ELEMENT_ENCONTRADO = true;
		}
		
		//Se encontrar caminho, parar.
		if (START_ELEMENT_ENCONTRADO && END_ELEMENT_ENCONTRADO) return;
		
		//Navegar nos elementos filhos
		for (Elemento e : base.getChildren()) {
			//Verificar se este elemento filho ainda não foi visitado.
			if (!e.isVisitado()) {
				//Verificar se o elemento é o ponto inicial ou final.
				if (!START_ELEMENT_ENCONTRADO && e.getName().equals(START_POINT)) {
					START_ELEMENT_ENCONTRADO = true;
				} else if (e.getName().equals(END_POINT)) {
					END_ELEMENT_ENCONTRADO = true;
				}
				
				//Marcar elemento como visitado, para evitar loops inifitos.
				e.setVisitado();
				
				//Se encontrar caminho, parar.
				if (START_ELEMENT_ENCONTRADO && END_ELEMENT_ENCONTRADO) break;
				
				//Se o elemento for diferente do elemento filho, então continuar a busca...
				if (e.getName() != base.getName()) {
					buscarCaminho(e);
				}
			}
		}
		
		//Se encontrar os pontos inicial e final, retornar para parar.
		if (START_ELEMENT_ENCONTRADO && END_ELEMENT_ENCONTRADO) return;
	}
}
