package com.alice.chess.customException;

public class UnacceptableTurnException extends BadTurnException {

    public UnacceptableTurnException(String message) {
        super(message);
    }

}
