package com.mosbach.demo.data.api;

import java.util.List;

public interface MeetupManager {

    List<Meetup> readAllMeetup();
    List<Meetup> readMyMeetups();
    void createMeetup(String meetupID, String title, String friends, String option, String location, long validUntil, String description);
    void createMeetupTable();

}
