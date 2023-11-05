
package com.mosbach.demo.model;

import java.sql.Timestamp;
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
    "optionid",
    "optionserial",
    "meetupid",
    "dateandtime"
})
@Generated("jsonschema2pojo")
public class CreateOptions {

    @JsonProperty("optionid")
    private String optionid;
    @JsonProperty("optionserial")
    private String optionserial;
    @JsonProperty("meetupid")
    private String meetupid;
    @JsonProperty("dateandtime")
    private Timestamp dateandtime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("optionid")
    public String getOptionid() {
        return optionid;
    }

    @JsonProperty("optionid")
    public void setOptionid(String optionid) {
        this.optionid = optionid;
    }

    @JsonProperty("optionserial")
    public String getOptionserial() {
        return optionserial;
    }

    @JsonProperty("optionserial")
    public void setOptionserial(String optionserial) {
        this.optionserial = optionserial;
    }

    @JsonProperty("meetupid")
    public String getMeetupid() {
        return meetupid;
    }

    @JsonProperty("meetupid")
    public void setMeetupid(String meetupid) {
        this.meetupid = meetupid;
    }

    @JsonProperty("dateandtime")
    public Timestamp getDateandtime() {
        return dateandtime;
    }

    @JsonProperty("dateandtime")
    public void setDateandtime(Timestamp dateandtime) {
        this.dateandtime = dateandtime;
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
