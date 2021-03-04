package com.lyft.android.auth0.jwt.exceptions;

public class InvalidClaimException extends JWTVerificationException {

    public InvalidClaimException(String message) {
        super(message);
    }
}
