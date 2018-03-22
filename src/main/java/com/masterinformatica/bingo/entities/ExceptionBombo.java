package com.masterinformatica.bingo.entities;

public class ExceptionBombo extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionBombo() {
		super();
	}

	public ExceptionBombo(String message) {
		super(message);
	}

	public ExceptionBombo(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionBombo(Throwable cause) {
		super(cause);
	}
}
