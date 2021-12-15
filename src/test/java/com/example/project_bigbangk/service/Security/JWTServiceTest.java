package com.example.project_bigbangk.service.Security;

import com.example.project_bigbangk.service.Security.ISecretKeyService;
import com.example.project_bigbangk.service.Security.ITokenService;
import com.example.project_bigbangk.service.Security.JWTService;
import com.example.project_bigbangk.service.Security.SecretKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author P.J.Bleichrodt
 * created 12/9/2021
 */
class JWTServiceTest {

    private ISecretKeyService secretKeyService = new SecretKeyService();
    private ITokenService jwtService = new JWTService(secretKeyService);
    private String token = jwtService.getToken("deek@deek.nl", "Deek");
    private final String REGEX_JWT = "(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)";

    @Test
    void GetToken() {
        assertNotNull(token);
        assertNotEquals("", token);
        assertTrue(matchesRegex(token, REGEX_JWT));
    }

    //ToDO Helper functie?
    private boolean matchesRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
//ToDo test of twee JwTs naa elkaar verschillend zijn
    @Test
    void getEamilFromToken() {
        String email = jwtService.getEmailFromToken(token);
        assertEquals("deek@deek.nl", email);
    }

    @Test
    void AuthenticateFail() {
        String tokenFail =
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlhdCI6MTYzODk2NDEwMywiZXhwIjoxNjM4OTY1MzIyLCJhdWQiOiJ3d3cuYmlnYmFuZ2suY29tIiwic3ViIjoiZGVla0BkZWVrLm5sIiwiZmlyc3ROYW1lIjoiRGVlayIsImVtYWlsIjoiZGVla0BkZWVrLm5sIiwicm9sZSI6IkNsaWVudCJ9.70WHKoPseGkbaOthNWiBQTadsI4GRsxI8ByXHJy6xlA";
        assertTrue(matchesRegex(token, REGEX_JWT));
        assertFalse(jwtService.authenticateToken(tokenFail));
    }

    @Test
    void getTokenAndAuthenticateSucces() {
        assertTrue(jwtService.authenticateToken(token));
    }

}