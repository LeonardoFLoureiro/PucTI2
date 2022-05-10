package model;

public class Posto {
	private int id_posto;
	private String marca;
	private int valorgasolina;
	private int valoretanol;
	
//CONSTRUTORES
	public Posto() {
		
	}
	public Posto(int id_posto,String marca,int valorgasolina,int valoretanol) {
		this.id_posto = id_posto;
		this.marca = marca;
		this.valorgasolina = valorgasolina;
		this.valoretanol = valoretanol;
	}
// GETS e SETS
	
	public int getid_posto() {
		
		return id_posto;
	}
	public void setidposto(int id_posto) {
		this.id_posto = id_posto;
	}
	
	public String getmarca() {
		return marca;
	}
	public void setmarca(String marca) {
		this.marca = marca;
	}
	
	public int getvalorgasolina() {
		return valorgasolina;
	}
	public void setvalorgasolina(int valorgasolina) {
		this.valorgasolina = valorgasolina;
	}
	public int getvaloretanol() {
		return valoretanol;
	}
	public void setvaloretanol(int valoretanol) {
		this.valoretanol = valoretanol;
	}
	
//toString
	
	public String toString() {
		return "Posto [id posto=" + id_posto + ", marca=" + marca + ", valor gasolina=" + valorgasolina + ", valor etanol=" + valoretanol + "]";
	}
}
