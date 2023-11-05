
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
    "voteid",
    "userid",
    "meetupid",
    "opt1",
    "opt2",
    "opt3",
    "opt4",
    "opt5",
    "opt6",
    "opt7"
})
@Generated("jsonschema2pojo")
public class CreateVoting {

    @JsonProperty("voteid")
    private String voteid;
    @JsonProperty("userid")
    private String userid;
    @JsonProperty("meetupid")
    private String meetupid;
    @JsonProperty("opt1")
    private boolean opt1;
    @JsonProperty("opt2")
    private boolean opt2;
    @JsonProperty("opt3")
    private boolean opt3;
    @JsonProperty("opt4")
    private boolean opt4;
    @JsonProperty("opt5")
    private boolean opt5;
    @JsonProperty("opt6")
    private boolean opt6;
    @JsonProperty("opt7")
    private boolean opt7;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("voteid")
    public String getVoteid() {
        return voteid;
    }

    @JsonProperty("voteid")
    public void setVoteid(String voteid) {
        this.voteid = voteid;
    }

    @JsonProperty("userid")
    public String getUserid() {
        return userid;
    }

    @JsonProperty("userid")
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @JsonProperty("meetupid")
    public String getMeetupid() {
        return meetupid;
    }

    @JsonProperty("meetupid")
    public void setMeetupid(String meetupid) {
        this.meetupid = meetupid;
    }

    @JsonProperty("opt1")
    public boolean getOpt1() {
        return opt1;
    }

    @JsonProperty("opt1")
    public void setOpt1(boolean opt1) {
        this.opt1 = opt1;
    }

    @JsonProperty("opt2")
    public boolean getOpt2() {
        return opt2;
    }

    @JsonProperty("opt2")
    public void setOpt2(boolean opt2) {
        this.opt2 = opt2;
    }

    @JsonProperty("opt3")
    public boolean getOpt3() {
        return opt3;
    }

    @JsonProperty("opt3")
    public void setOpt3(boolean opt3) {
        this.opt3 = opt3;
    }

    @JsonProperty("opt4")
    public boolean getOpt4() {
        return opt4;
    }

    @JsonProperty("opt4")
    public void setOpt4(boolean opt4) {
        this.opt4 = opt4;
    }

    @JsonProperty("opt5")
    public boolean getOpt5() {
        return opt5;
    }

    @JsonProperty("opt5")
    public void setOpt5(boolean opt5) {
        this.opt5 = opt5;
    }

    @JsonProperty("opt6")
    public boolean getOpt6() {
        return opt6;
    }

    @JsonProperty("opt6")
    public void setOpt6(boolean opt6) {
        this.opt6 = opt6;
    }

    @JsonProperty("opt7")
    public boolean getOpt7() {
        return opt7;
    }

    @JsonProperty("opt7")
    public void setOpt7(boolean opt7) {
        this.opt7 = opt7;
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
