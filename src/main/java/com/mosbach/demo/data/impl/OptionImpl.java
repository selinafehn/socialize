package com.mosbach.demo.data.impl;

import java.util.Date;

public class OptionImpl {

    private int optionID;

    private Date dateAndTme;

    public OptionImpl(int optionID, Date dateAndTme) {
        this.optionID = optionID;
        this.dateAndTme = dateAndTme;
    }

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }

    public Date getDateAndTme() {
        return dateAndTme;
    }

    public void setDateAndTme(Date dateAndTme) {
        this.dateAndTme = dateAndTme;
    }
}
