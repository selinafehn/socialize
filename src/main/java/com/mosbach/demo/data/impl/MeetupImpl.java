package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.Meetup;

import java.util.List;

public class MeetupImpl implements Meetup {

    private String ID;
    private String title;
    private String friends;
    private String option;
    private String location;
    private String description;


    public MeetupImpl(String ID, String title, String friends, String option, String location, String description) {
        this.ID = ID;
        this.title = title;
        this.friends= friends;
        this.option = option;
        this.location = location;
        this.description = description;
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

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }
}
