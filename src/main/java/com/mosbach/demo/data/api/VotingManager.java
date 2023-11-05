package com.mosbach.demo.data.api;

public interface VotingManager {

    void createVotingTable();
    public Voting createVoting(String voteID, String userID, String meetupID, boolean opt1, boolean opt2, boolean opt3, boolean opt4, boolean opt5, boolean opt6, boolean opt7);

    }
