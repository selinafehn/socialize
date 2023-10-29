package com.mosbach.demo.data.impl;

public class VotingImpl {

    private String voteID;
    private String userID;
    private String meetupID;
    private boolean opt1;
    private boolean opt2;
    private boolean opt3;
    private boolean opt4;
    private boolean opt5;
    private boolean opt6;
    private boolean opt7;
    private boolean loc1;
    private boolean loc2;
    private boolean loc3;
    private boolean loc4;
    private boolean loc5;
    private boolean loc6;
    private boolean loc7;

    public VotingImpl(String voteID, String userID, String meetupID, boolean opt1, boolean opt2, boolean opt3, boolean opt4, boolean opt5, boolean opt6, boolean opt7, boolean loc1, boolean loc2, boolean loc3, boolean loc4, boolean loc5, boolean loc6, boolean loc7) {
        this.voteID = voteID;
        this.userID = userID;
        this.meetupID = meetupID;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.opt5 = opt5;
        this.opt6 = opt6;
        this.opt7 = opt7;
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.loc3 = loc3;
        this.loc4 = loc4;
        this.loc5 = loc5;
        this.loc6 = loc6;
        this.loc7 = loc7;
    }

    public String getVoteID() {
        return voteID;
    }

    public void setVoteID(String voteID) {
        this.voteID = voteID;
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

    public boolean isOpt1() {
        return opt1;
    }

    public void setOpt1(boolean opt1) {
        this.opt1 = opt1;
    }

    public boolean isOpt2() {
        return opt2;
    }

    public void setOpt2(boolean opt2) {
        this.opt2 = opt2;
    }

    public boolean isOpt3() {
        return opt3;
    }

    public void setOpt3(boolean opt3) {
        this.opt3 = opt3;
    }

    public boolean isOpt4() {
        return opt4;
    }

    public void setOpt4(boolean opt4) {
        this.opt4 = opt4;
    }

    public boolean isOpt5() {
        return opt5;
    }

    public void setOpt5(boolean opt5) {
        this.opt5 = opt5;
    }

    public boolean isOpt6() {
        return opt6;
    }

    public void setOpt6(boolean opt6) {
        this.opt6 = opt6;
    }

    public boolean isOpt7() {
        return opt7;
    }

    public void setOpt7(boolean opt7) {
        this.opt7 = opt7;
    }

    public boolean isLoc1() {
        return loc1;
    }

    public void setLoc1(boolean loc1) {
        this.loc1 = loc1;
    }

    public boolean isLoc2() {
        return loc2;
    }

    public void setLoc2(boolean loc2) {
        this.loc2 = loc2;
    }

    public boolean isLoc3() {
        return loc3;
    }

    public void setLoc3(boolean loc3) {
        this.loc3 = loc3;
    }

    public boolean isLoc4() {
        return loc4;
    }

    public void setLoc4(boolean loc4) {
        this.loc4 = loc4;
    }

    public boolean isLoc5() {
        return loc5;
    }

    public void setLoc5(boolean loc5) {
        this.loc5 = loc5;
    }

    public boolean isLoc6() {
        return loc6;
    }

    public void setLoc6(boolean loc6) {
        this.loc6 = loc6;
    }

    public boolean isLoc7() {
        return loc7;
    }

    public void setLoc7(boolean loc7) {
        this.loc7 = loc7;
    }
}
