package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Voting;

public class VotingImpl implements Voting {
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

    public VotingImpl(String voteID, String userID, String meetupID, boolean opt1, boolean opt2, boolean opt3, boolean opt4, boolean opt5, boolean opt6, boolean opt7) {
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

    @Override
    public boolean getOpt1() {
        return opt1;
    }

    @Override
    public boolean getOpt2() {
        return opt2;
    }

    @Override
    public boolean getOpt3() {
        return opt3;
    }

    @Override
    public boolean getOpt4() {
        return opt4;
    }

    @Override
    public boolean getOpt5() {
        return opt5;
    }

    @Override
    public boolean getOpt6() {
        return opt6;
    }

    @Override
    public boolean getOpt7() {
        return opt7;
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

}
