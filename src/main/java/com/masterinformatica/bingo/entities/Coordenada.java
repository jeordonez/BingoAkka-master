package com.masterinformatica.bingo.entities;

public class Coordenada {

	private int x;
	private int y;
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("(%d , %d)", this.x, this.y);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Coordenada)) {
			return false;
		}
		Coordenada c = (Coordenada) o;
		return this.x == c.x && this.y == c.y;
	}
}
