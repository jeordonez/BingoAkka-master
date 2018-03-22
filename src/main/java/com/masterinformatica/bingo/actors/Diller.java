package com.masterinformatica.bingo.actors;

import com.masterinformatica.bingo.entities.Bombo;
import com.masterinformatica.bingo.entities.ExceptionBombo;
import com.masterinformatica.bingo.messages.BingoNumber;
import akka.actor.UntypedActor;

public class Diller extends UntypedActor {

	private Bombo bombo;

	public Diller() {
		this.bombo = new Bombo();		
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
		} catch (ExceptionBombo e) {
			System.err.println("Bombo vacío, acabar juego!");
		} catch (InterruptedException e) {
			System.err.println("Interrumped thread sleep...");		
		}

	}
}
