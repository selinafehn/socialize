
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
    "userID",
    "title",
    "friends",
    "option",
    "location",
    "validuntil",
    "description",

})
@Generated("jsonschema2pojo")
public class CreateMeetup {

    @JsonProperty("userID")
    private String userID;
    @JsonProperty("title")
    private String title;
    @JsonProperty("friends")
    private String friends;
    @JsonProperty("option")
    private String option;
    @JsonProperty("location")
    private String location;
    @JsonProperty("validuntil")
    private long validuntil;
    @JsonProperty("description")
    private String description;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("userID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("option")
    public String getOption() {
        return option;
    }

    @JsonProperty("option")
    public void setOption(String option) {
        this.option = option;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("validuntil")
    public long getValiduntil() {
        return validuntil;
    }

    @JsonProperty("validuntil")
    public void setValiduntil(long validuntil) {
        this.validuntil = validuntil;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }
}
