package com.masterinformatica.bingo.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Principal extends JPanel {

	int[][] matrizGrafo;
	int number = 0;
	int numberGenerate;
	boolean paint = false;

	public void setMaxNumber(int number) {
		this.number = number;
	}

	public void setNumberGenerate(int numberGenerate, boolean paint) {
		this.numberGenerate = numberGenerate;
		this.paint = paint;

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < number; i++) {
			int aux = i * 50;
			g2d.setColor(Color.BLACK);
			g2d.drawRect(aux, 5, 20, 20);
			int aux1 = 1 + i;
			g2d.drawString(String.valueOf(aux1), aux + 5, 20);
		}

		if (paint) {
			g2d.setColor(Color.RED);
			g2d.drawRect((numberGenerate - 1) * 50, 5, 20, 20);
		}

	}
}