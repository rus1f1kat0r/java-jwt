package com.lyft.android.auth0.jwt.algorithms;

import android.util.Base64;
import com.lyft.android.auth0.jwt.exceptions.SignatureGenerationException;
import com.lyft.android.auth0.jwt.exceptions.SignatureVerificationException;
import com.lyft.android.auth0.jwt.interfaces.DecodedJWT;

class NoneAlgorithm extends Algorithm {

    NoneAlgorithm() {
        super("none", "none");
    }

    @Override
    public void verify(DecodedJWT jwt) throws SignatureVerificationException {
        byte[] signatureBytes = Base64.decode(jwt.getSignature(), Base64.DEFAULT);
        if (signatureBytes.length > 0) {
            throw new SignatureVerificationException(this);
        }
    }

    @Override
    public byte[] sign(byte[] headerBytes, byte[] payloadBytes) throws SignatureGenerationException {
        return new byte[0];
    }

    @Override
    @Deprecated
    public byte[] sign(byte[] contentBytes) throws SignatureGenerationException {
        return new byte[0];
    }
}
