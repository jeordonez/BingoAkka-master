package com.masterinformatica.bingo.entities;

public class Casilla {

	private Coordenada coordenada;
	private int number;
	private boolean state;
	
	
	public Casilla(Coordenada coordenada, int number) {
		this.coordenada = coordenada;
		this.number = number;
		this.state = false;
	}
	
	public void mark() {
		this.state = true;
	}
	
	public boolean isMarked() {
		return this.state;
	}
	
	public boolean isCasilla(int number) {
		return this.number == number;
	}
	
	public int  getValue() {
		return this.number;
	}
	
	@Override
	public String toString() {
		return String.format(" %s %d %s ", this.coordenada.toString(), this.number, (this.state) ? "marcada" : "vacia");
	}
}
