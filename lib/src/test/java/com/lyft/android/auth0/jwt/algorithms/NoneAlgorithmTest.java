package com.lyft.android.auth0.jwt.algorithms;

import com.lyft.android.auth0.jwt.JWT;
import com.lyft.android.auth0.jwt.exceptions.JWTDecodeException;
import com.lyft.android.auth0.jwt.exceptions.SignatureVerificationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class NoneAlgorithmTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldPassNoneVerification() throws Exception {
        Algorithm algorithm = Algorithm.none();
        String jwt = "eyJhbGciOiJub25lIiwiY3R5IjoiSldUIn0.eyJpc3MiOiJhdXRoMCJ9.";
        algorithm.verify(JWT.decode(jwt));
    }

    @Test
    public void shouldFailNoneVerificationWhenTokenHasTwoParts() throws Exception {
        exception.expect(JWTDecodeException.class);
        exception.expectMessage("The token was expected to have 3 parts, but got 2.");
        String jwt = "eyJhbGciOiJub25lIiwiY3R5IjoiSldUIn0.eyJpc3MiOiJhdXRoMCJ9";
        Algorithm algorithm = Algorithm.none();
        algorithm.verify(JWT.decode(jwt));
    }

    @Test
    public void shouldFailNoneVerificationWhenSignatureIsPresent() throws Exception {
        exception.expect(SignatureVerificationException.class);
        exception.expectMessage("The Token's Signature resulted invalid when verified using the Algorithm: none");
        String jwt = "eyJhbGciOiJub25lIiwiY3R5IjoiSldUIn0.eyJpc3MiOiJhdXRoMCJ9.Ox-WRXRaGAuWt2KfPvWiGcCrPqZtbp_4OnQzZXaTfss";
        Algorithm algorithm = Algorithm.none();
        algorithm.verify(JWT.decode(jwt));
    }

    @Test
    public void shouldReturnNullSigningKeyId() throws Exception {
        assertThat(Algorithm.none().getSigningKeyId(), is(nullValue()));
    }
}