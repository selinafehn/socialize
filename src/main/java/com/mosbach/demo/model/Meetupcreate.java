
package com.mosbach.demo.model;

import java.util.LinkedHashMap;
import java.util.List;
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
    "id",
    "title",
    "friends",
    "date",
    "place",
    "timerange",
    "description"
})
@Generated("jsonschema2pojo")
public class Meetupcreate {

    @JsonProperty("title")
    private String title;
    @JsonProperty("friends")
    private List<String> friends;
    @JsonProperty("date")
    private String date;
    @JsonProperty("place")
    private String place;
    @JsonProperty("specification")
    private String specification;
    @JsonProperty("timerange")
    private String timerange;
    @JsonProperty("token")
    private String token;
    @JsonProperty("description")
    private String description;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("friends")
    public List<String> getFriends() {
        return friends;
    }

    @JsonProperty("friends")
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("place")
    public String getPlace() {
        return place;
    }

    @JsonProperty("place")
    public void setPlace(String place) {
        this.place = place;
    }

    @JsonProperty("specification")
    public String getSpecification() {
        return specification;
    }

    @JsonProperty("specification")
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @JsonProperty("timerange")
    public String getTimerange() {
        return timerange;
    }

    @JsonProperty("timerange")
    public void setTimerange(String timerange) {
        this.timerange = timerange;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
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
