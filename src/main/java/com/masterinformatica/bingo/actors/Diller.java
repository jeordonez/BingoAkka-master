package com.masterinformatica.bingo.actors;

import com.masterinformatica.bingo.entities.Bombo;
import com.masterinformatica.bingo.entities.ExceptionBombo;
import com.masterinformatica.bingo.messages.BingoNumber;
import javax.swing.JFrame;
import akka.actor.UntypedActor;
import com.masterinformatica.bingo.views.Principal;

public class Diller extends UntypedActor {

	private Bombo bombo;
	JFrame frame = new JFrame("Bingo");
	Principal graWindow = new Principal();

	public Diller() {
		this.bombo = new Bombo();

		graWindow.setMaxNumber(bombo.getMaxNumber());
        frame.add(graWindow);
        frame.setSize(1000, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof BingoNumber) {
			generateNumber();
		}
	}
	
	private void generateNumber() {
		try {
			BingoNumber numb = bombo.generate();		
			Thread.sleep(1000);
		    getSender().tell(numb, getSelf());	
		    graWindow.setNumberGenerate(numb.getValue(), true);
			graWindow.repaint();
		} catch (ExceptionBombo e) {
			System.err.println("Bombo vacío, acabar juego!");
		} catch (InterruptedException e) {
			System.err.println("Interrumped thread sleep...");		
		}

	}
}
