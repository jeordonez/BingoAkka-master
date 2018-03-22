package com.masterinformatica.bingo.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.masterinformatica.bingo.messages.BingoMessage;
import com.masterinformatica.bingo.messages.BingoNumber;


public class Manager extends UntypedActor {

	private static final int NUM_JUGADORES = 1;
	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	private ActorRef[] players;
	private ActorRef diller;

	public Manager() {
		this.players = new ActorRef[NUM_JUGADORES];

		for (int i = 0; i < NUM_JUGADORES; ++i) {
			ActorRef jugador = getContext().actorOf(Props.create(Player.class));
			this.players[i] = jugador;
		}
		this.diller = getContext().actorOf(Props.create(Diller.class));
	}
	
	@Override
	public void preStart() {
		this.diller.tell(new BingoNumber(-1), getSelf());
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
				
		if (message instanceof BingoMessage) {
			BingoMessage msg = (BingoMessage) message;
			processGame(msg);
		}
		
		if (message instanceof BingoNumber) {
			BingoNumber numb = (BingoNumber) message;
			sendNumber(numb);
		}
	}
	
	private void processGame(BingoMessage message) {		
		BingoMessage.Value type = message.getValue();
		
		switch (type) {
		case BINGO:
			System.err.println("BINGO!!");
			break;		
		case LINEA:
			System.err.println("LINEA!!");			
			break;
			
		default:
			System.err.println("Error message process game...");
		}
	}
	
	private void sendNumber(BingoNumber numb) {
		for (ActorRef player : this.players) {
			player.tell(numb, getSelf());
		}		
		diller.tell(new BingoNumber(-1), getSelf());
	}

	@Override
	public void unhandled(Object message) {
		log.error("Message not registered");
	}
}

/*
 * public class Manager extends UntypedActor {
 * 
 * private final LoggingAdapter log = Logging.getLogger(getContext().system(),
 * this);
 * 
 * private Bombo bombo;
 * 
 * @Override public void preStart() { this.bombo = new Bombo(); }
 * 
 * @Override public void onReceive(Object o) { ; }
 * 
 * @Override public void unhandled(Object o) {
 * log.error("Message not registered"); } }
 */
