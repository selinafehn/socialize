package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.Meetup;

public class MeetupImpl implements Meetup {

    private String meetupID;
    private String description;
    private String title;
    private String option;
    private String location;
    private long validUntil;

    public MeetupImpl(String meetupID, String description, String title, String option, String location, long validUntil) {
        this.meetupID = meetupID;
        this.description = description;
        this.title = title;
        this.option = option;
        this.location = location;
        this.validUntil = validUntil;
    }

    @Override
    public String getMeetupID() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getOption() {
        return null;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public long getValidUntil() {
        return 0;
    }

    public void setMeetupID(String meetupID) {
        this.meetupID = meetupID;
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
