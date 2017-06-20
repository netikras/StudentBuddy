package com.netikras.studies.studentbuddy.commons.tools;

import java.security.cert.Certificate;

/**
 * Created by netikras on 16.11.27.
 */
public class AuthenticationDetail {

    private String username;
    private String password;
    private String auth0ticket;
    private Certificate targetCert;
    private String warrantTicket;
    private String sessionId;


    public String getUsername() {
        return username;
    }

    public AuthenticationDetail setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationDetail setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAuth0ticket() {
        return auth0ticket;
    }

    public AuthenticationDetail setAuth0ticket(String auth0ticket) {
        this.auth0ticket = auth0ticket;
        return this;
    }

    public Certificate getTargetCert() {
        return targetCert;
    }

    public AuthenticationDetail setTargetCert(Certificate targetCert) {
        this.targetCert = targetCert;
        return this;
    }

    public String getWarrantTicket() {
        return warrantTicket;
    }

    public AuthenticationDetail setWarrantTicket(String warrantTicket) {
        this.warrantTicket = warrantTicket;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public AuthenticationDetail setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @Override
    public String toString() {
        return "AuthenticationDetail{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", auth0ticket='" + auth0ticket + '\'' +
                ", targetCert=" + targetCert +
                ", warrantTicket='" + warrantTicket + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
