package model;

import security.Password;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by roshanalwis on 8/31/17.
 */
public class Token {
    private long userId;
    private String remoteAddress;
    private Timestamp timestamp;
    private String accessToken;

    public Token() {

    }

    public Token(long userId, String remoteAddress, String userPassword) throws Exception {
        this.userId = userId;
        this.remoteAddress = remoteAddress;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        String token = timestamp.toString() + userPassword;
        this.accessToken = Password.getSaltedHash(token);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
