package model;

import java.sql.Timestamp;

/**
 * Created by roshanalwis on 8/31/17.
 */
public class Token {
    private String key;
    private Timestamp timestamp;

    public Token() {

    }

    public Token(String key, Timestamp timestamp) {
        this.key = key;
        this.timestamp = timestamp;
    }


}
