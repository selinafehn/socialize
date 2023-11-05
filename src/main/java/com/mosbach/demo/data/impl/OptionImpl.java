package com.mosbach.demo.data.impl;

import java.sql.Timestamp;
import java.util.Date;

public class OptionImpl {

    private String optionID;
    private String optionserial;
    private String meetupid;
    private Timestamp dateAndTme;

    public OptionImpl(String optionID, String optionserial, String meetupid, Timestamp dateAndTme) {
        this.optionID = optionID;
        this.optionserial = optionserial;
        this.meetupid = meetupid;
        this.dateAndTme = dateAndTme;
    }

    public String getOptionID() {
        return optionID;
    }

    public void setOptionID(String optionID) {
        this.optionID = optionID;
    }

    public String getOptionserial() {
        return optionserial;
    }

    public void setOptionserial(String optionserial) {
        this.optionserial = optionserial;
    }

    public String getMeetupid() {
        return meetupid;
    }

    public void setMeetupid(String meetupid) {
        this.meetupid = meetupid;
    }

    public Timestamp getDateAndTme() {
        return dateAndTme;
    }

    public void setDateAndTme(Timestamp dateAndTme) {
        this.dateAndTme = dateAndTme;
    }
}
