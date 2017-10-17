package security;

import model.Token;

import java.util.HashMap;

/**
 * Created by roshanalwis on 8/31/17.
 */

public class TokenManager {
    private static HashMap<String, Token> tokenRegistry = new HashMap<>();

    public static void addToken(Token token){
        tokenRegistry.put(token.getRemoteAddress(), token);
    }

    public static boolean verify(Token token){
        Token storedToken = tokenRegistry.get(token.getRemoteAddress());
        if(storedToken.getAccessToken().equals(token.getAccessToken()) &&
                storedToken.getRemoteAddress().equals(token.getRemoteAddress())){
            return true;
        } else {
            return false;
        }
    }


}
