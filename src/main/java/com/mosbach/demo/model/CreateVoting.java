
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
    "opt7",
    "loc1",
    "loc2",
    "loc3",
    "loc4",
    "loc5",
    "loc6",
    "loc7"
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
    private String opt1;
    @JsonProperty("opt2")
    private String opt2;
    @JsonProperty("opt3")
    private String opt3;
    @JsonProperty("opt4")
    private String opt4;
    @JsonProperty("opt5")
    private String opt5;
    @JsonProperty("opt6")
    private String opt6;
    @JsonProperty("opt7")
    private String opt7;
    @JsonProperty("loc1")
    private String loc1;
    @JsonProperty("loc2")
    private String loc2;
    @JsonProperty("loc3")
    private String loc3;
    @JsonProperty("loc4")
    private String loc4;
    @JsonProperty("loc5")
    private String loc5;
    @JsonProperty("loc6")
    private String loc6;
    @JsonProperty("loc7")
    private String loc7;
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
    public String getOpt1() {
        return opt1;
    }

    @JsonProperty("opt1")
    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    @JsonProperty("opt2")
    public String getOpt2() {
        return opt2;
    }

    @JsonProperty("opt2")
    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    @JsonProperty("opt3")
    public String getOpt3() {
        return opt3;
    }

    @JsonProperty("opt3")
    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    @JsonProperty("opt4")
    public String getOpt4() {
        return opt4;
    }

    @JsonProperty("opt4")
    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    @JsonProperty("opt5")
    public String getOpt5() {
        return opt5;
    }

    @JsonProperty("opt5")
    public void setOpt5(String opt5) {
        this.opt5 = opt5;
    }

    @JsonProperty("opt6")
    public String getOpt6() {
        return opt6;
    }

    @JsonProperty("opt6")
    public void setOpt6(String opt6) {
        this.opt6 = opt6;
    }

    @JsonProperty("opt7")
    public String getOpt7() {
        return opt7;
    }

    @JsonProperty("opt7")
    public void setOpt7(String opt7) {
        this.opt7 = opt7;
    }

    @JsonProperty("loc1")
    public String getLoc1() {
        return loc1;
    }

    @JsonProperty("loc1")
    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }

    @JsonProperty("loc2")
    public String getLoc2() {
        return loc2;
    }

    @JsonProperty("loc2")
    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    @JsonProperty("loc3")
    public String getLoc3() {
        return loc3;
    }

    @JsonProperty("loc3")
    public void setLoc3(String loc3) {
        this.loc3 = loc3;
    }

    @JsonProperty("loc4")
    public String getLoc4() {
        return loc4;
    }

    @JsonProperty("loc4")
    public void setLoc4(String loc4) {
        this.loc4 = loc4;
    }

    @JsonProperty("loc5")
    public String getLoc5() {
        return loc5;
    }

    @JsonProperty("loc5")
    public void setLoc5(String loc5) {
        this.loc5 = loc5;
    }

    @JsonProperty("loc6")
    public String getLoc6() {
        return loc6;
    }

    @JsonProperty("loc6")
    public void setLoc6(String loc6) {
        this.loc6 = loc6;
    }

    @JsonProperty("loc7")
    public String getLoc7() {
        return loc7;
    }

    @JsonProperty("loc7")
    public void setLoc7(String loc7) {
        this.loc7 = loc7;
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
