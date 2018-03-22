package com.masterinformatica.bingo.entities;

import java.util.Random;
import com.masterinformatica.bingo.messages.BingoNumber;

public class Carton {

	private static final int ROWS = 3;
	private static final int COLS = 3;
	
	private Casilla[] casillas;
	private boolean[] lineas;
	
	public Carton() {
		this.lineas = new boolean[ROWS];
		this.casillas = new Casilla[ROWS * COLS];
		init();
	}
	
	private void init() {		
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				insertNumber(i, j);
			}
			this.lineas[i] = false;
		}		
	}
	
	private void insertNumber(int i, int j) {
		Random rand = new Random();
		boolean inserted = false;

		do {
			int number = rand.nextInt(Bombo.MAX_NUMBERS);
			if (!contains(number)) {				
				this.casillas[i * ROWS + j] = new Casilla(new Coordenada(i, j), number);															
				inserted = true;
			}
		} while (!inserted);
	}
	
	private boolean contains(int numb) {
		for (Casilla casilla : this.casillas) {
			if (casilla != null && casilla.getValue() == numb) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isLinea() {	
		for (int i = 0; i < ROWS; i++) {
			boolean[] cols = getLineMarked(i);
			if (!this.lineas[i] && isColsLine(cols)) {
				this.lineas[i] = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean isColsLine(boolean[] cols) {
		for (int k = 0; k < COLS; k++) {
			if (!cols[k]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean[] getLineMarked(int row) {
		boolean[] cols = new boolean[COLS];

		for (int j = 0; j < COLS; ++j) {
			if (this.casillas[row * ROWS + j].isMarked()) {
				cols[j] = true;
			}				
		}
		return cols;
	}
	
	public boolean isBingo() {
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				if (!this.casillas[i * COLS + j].isMarked()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void mark(BingoNumber numb) {
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				Casilla casilla = this.casillas[i * COLS + j];
				if (casilla.isCasilla(numb.getValue())) {
					casilla.mark();					
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer format = new StringBuffer();
		
		format.append("Carton\n");
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				format.append(String.format(" %s ", this.casillas[i * COLS + j].toString()));				
			}
			format.append('\n');
		}
		return format.toString();
	}
}
