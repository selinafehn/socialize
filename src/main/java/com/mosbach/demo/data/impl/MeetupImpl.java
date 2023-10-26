package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.Meetup;

public class MeetupImpl implements Meetup {

    private String ID;
    private String description;
    private String title;
    private String option;
    private String location;
    private long validUntil;

    public MeetupImpl(String ID, String description, String title, String option, String location, long validUntil) {
        this.ID = ID;
        this.description = description;
        this.title = title;
        this.option = option;
        this.location = location;
        this.validUntil = validUntil;
    }

    @Override
    public String getMeetupID() {
        return ID;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOption() {
        return option;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public long getValidUntil() {
        return validUntil;
    }

    public void setMeetupID(String meetupID) {
        this.ID = meetupID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }
}
