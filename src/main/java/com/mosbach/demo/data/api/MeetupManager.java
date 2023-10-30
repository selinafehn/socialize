package com.mosbach.demo.data.api;

import java.util.List;

public interface MeetupManager {

    List<Meetup> readAllMeetup();
    void createMeetup(String meetupID, String friends, String description, String title, String option,  long validUntil, String location);
    void createMeetupTable();

}
