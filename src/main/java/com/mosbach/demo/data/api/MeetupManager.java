package com.mosbach.demo.data.api;

import java.util.List;

public interface MeetupManager {

    List<Meetup> readAllMeetup();
    void createMeetup(String meetupID, String description, String title, String option, String location, int validUntil);
    void createMeetupTable();

}
