package com.alice.chess.customException;

public class BadTurnException extends Exception {

    public BadTurnException(String message) {
        super(message);
    }

}
