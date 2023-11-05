package com.mosbach.demo.data.api;

import java.sql.Timestamp;

public interface OptionsManager {

    void createOptionsTable();
    Options createOptions(String optionid, String optionserial, String meetupid, Timestamp dateandtime);
}
