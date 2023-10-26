package com.mosbach.demo.data.impl;

public class LocationImpl {

    private int locID;
    private String place;
    private String specification;

    public LocationImpl(int locID, String place, String specification) {
        this.locID = locID;
        this.place = place;
        this.specification = specification;
    }

    public int getLocID() {
        return locID;
    }

    public void setLocID(int locID) {
        this.locID = locID;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
