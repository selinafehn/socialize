package com.mosbach.demo.data.api;

import java.util.List;

public interface AttendeesManager {

    void createAttendeesTable();
    List<Attendees> readAllAttendees();

}
