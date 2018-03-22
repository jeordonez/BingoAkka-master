package com.masterinformatica.bingo.messages;

/**
 * Esta clase envía un mensaje de los jugadores al maestros si cantan
 * linea o cantan bingo...
 * 
 * @author jonathan y javi
 *
 */
public class BingoMessage {
	
    public static enum Value {LINEA, BINGO};
    
    private Value type;
    
    public BingoMessage(Value type) {
    	this.type = type;
    }
   
    public Value getValue() {
    	return this.type;
    }
}
