package utilitarios;

import java.util.ArrayList;
import java.util.List;

public class Elemento {

	private String name;
	private List<Elemento> children = new ArrayList<>();
	private boolean visitado = false;
	
	public Elemento(String name){
		setName(name);
	}
	
	public boolean isVisitado(){
		return this.visitado;
	}
	
	public void setVisitado(){
		this.visitado = true;
	}
	
	public Elemento(char name){
		setName(String.valueOf(name));
	}

	public String getName(){
		return this.name;
	}
	
	private void setName(String name) {
		this.name = name;
	}

	public List<Elemento> getChildren() {
		return children;
	}

	public void addChild(Elemento e){
		children.add(e);
	}
}
