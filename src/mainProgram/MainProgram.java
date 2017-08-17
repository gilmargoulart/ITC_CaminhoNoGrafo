package mainProgram;

import com.google.gson.Gson;

import utilitarios.TextReader;

public class MainProgram {
	
	public static String CONFIG_FILE_PATH = "matriz.json"; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Gson g = new Gson();
		
		//Ler arquivo de texto, com a configuração armazenada em Json
		String txtJson = null;
		try {
			txtJson = TextReader.readTextFile(CONFIG_FILE_PATH);
			
			// TODO Instanciar classe de configuração com a matriz
			//configuracao = g.fromJson(txtJson, Configuracao.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
