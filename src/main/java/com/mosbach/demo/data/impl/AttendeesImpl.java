package com.mosbach.demo.data.impl;

public class AttendeesImpl {

    private String relID;
    private String userID;
    private String meetupID;
    private byte host;

    public AttendeesImpl(String relID, String userID, String meetupID, byte host) {
        this.relID = relID;
        this.userID = userID;
        this.meetupID = meetupID;
        this.host = host;
    }

    public String getRelID() {
        return relID;
    }

    public void setRelID(String relID) {
        this.relID = relID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMeetupID() {
        return meetupID;
    }

    public void setMeetupID(String meetupID) {
        this.meetupID = meetupID;
    }

    public byte getHost() {
        return host;
    }

    public void setHost(byte host) {
        this.host = host;
    }
}
