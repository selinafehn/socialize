
package com.mosbach.demo.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "relID",
    "userID",
    "meetupID",
    "host"
})
@Generated("jsonschema2pojo")
public class CreateAttendee {

    @JsonProperty("relID")
    private String relID;
    @JsonProperty("userID")
    private String userID;
    @JsonProperty("meetupID")
    private String meetupID;
    @JsonProperty("host")
    private String host;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("relID")
    public String getRelID() {
        return relID;
    }

    @JsonProperty("relID")
    public void setRelID(String relID) {
        this.relID = relID;
    }

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("userID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("meetupID")
    public String getMeetupID() {
        return meetupID;
    }

    @JsonProperty("meetupID")
    public void setMeetupID(String meetupID) {
        this.meetupID = meetupID;
    }

    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
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
