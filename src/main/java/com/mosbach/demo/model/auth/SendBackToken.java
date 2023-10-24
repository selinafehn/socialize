package com.mosbach.demo.model.auth;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class SendBackToken {

    private String token;
    private long validInSeconds;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public SendBackToken(String token, long validInSeconds) {
        this.token = token;
        this.validInSeconds = validInSeconds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getValidInSeconds() {
        return validInSeconds;
    }

    public void setValidInSeconds(long validInSeconds) {
        this.validInSeconds = validInSeconds;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
