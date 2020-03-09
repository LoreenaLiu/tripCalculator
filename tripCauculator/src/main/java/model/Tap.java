package model;

import java.util.Date;

public class Tap {

    private int id;
    private TapType tapType;
    private Date dateTimeUTC;
    private String stopId;
    private String companyId;
    private String busID;
    private String pan;

    public Tap(int id, TapType tapType, Date dateTimeUTC, String stopId, String companyId, String busID, String pan) {
        this.id = id;
        this.tapType = tapType;
        this.dateTimeUTC = dateTimeUTC;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busID = busID;
        this.pan = pan;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Tap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TapType getTapType() {
        return tapType;
    }

    public void setTapType(TapType tapType) {
        this.tapType = tapType;
    }

    public Date getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(Date dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }
}
