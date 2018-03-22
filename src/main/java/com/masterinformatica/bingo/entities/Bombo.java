package com.masterinformatica.bingo.entities;

import java.util.Random;
import com.masterinformatica.bingo.messages.BingoNumber;

/**
 * Esta clase contiene el bombo genera un numero aleatorio
 * entre 0..N-1 que no haya salido antes...
 * @author javi y jonathan.
 *
 */
public class Bombo {

	public static final int MAX_NUMBERS = 20;
	
	private boolean numbers[]; 
	private Random seed;
	
	public Bombo() {
		this.seed = new Random();
		this.numbers = new boolean[MAX_NUMBERS];
		
		for (int i = 0; i < MAX_NUMBERS; i++) {
			this.numbers[i] = true;
		}
	}
	
	public BingoNumber generate() throws ExceptionBombo {
		if (this.empty()) {
			throw new ExceptionBombo();
		}
				
		for (;;) {
			int numb = this.seed.nextInt(Bombo.MAX_NUMBERS);
			
			if (this.numbers[numb]) {
				this.numbers[numb] = false;
				return new BingoNumber(numb);
			}
		}
	}
	
	private boolean empty() {
		for (int i = 0; i < MAX_NUMBERS; ++i) {
			if (numbers[i]) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer format = new StringBuffer();
		
		format.append("Bombo\n");
		for (int i = 0; i < MAX_NUMBERS; i++) {
			format.append(String.format(" %d %s ", i, (this.numbers[i]) ? "In" : "Out"));
			if (i % 10 == 0 && i != 0) {
				format.append('\n');
			}
		}
		return format.toString();
	}
	
	public int getMaxNumber() {
		return MAX_NUMBERS;
	}
}
