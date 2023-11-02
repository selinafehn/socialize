package com.mosbach.demo.data.api;

public interface VotingManager {

    void createVotingTable();
    public Voting createVoting(String voteID, String userID, String meetupID, boolean opt1, boolean opt2, boolean opt3, boolean opt4, boolean opt5, boolean opt6, boolean opt7, boolean loc1, boolean loc2, boolean loc3, boolean loc4, boolean loc5, boolean loc6, boolean loc7);

    }
