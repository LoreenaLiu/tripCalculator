package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Trip {
//    Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN,
//    Status

    private Date started;
    private Date finished;
    private long durationSecs;
    private String fromStopId;
    private String toStopId;
    private double chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private TripStatus tripStatus;


    public Trip(Date started, Date finished, long durationSecs, String fromStopId, String toStopId, double chargeAmount, String companyId, String busId, String pan, TripStatus tripStatus) {
        this.started = started;
        this.finished = finished;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.tripStatus = tripStatus;
    }



    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public long getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        StringBuilder sb = new StringBuilder();

        sb.append(formatter.format(this.started));
        sb.append(", ");
        if (this.finished != null) {
            sb.append(formatter.format(this.finished));
        }
        sb.append(", ");
        sb.append(this.durationSecs);
        sb.append(", ");
        sb.append(this.fromStopId);
        sb.append(", ");
        if (this.toStopId != null) {
            sb.append(this.toStopId);
        }
        sb.append(", ");
        sb.append(this.chargeAmount);
        sb.append(", ");
        sb.append(this.companyId);
        sb.append(", ");
        sb.append(this.busId);
        sb.append(", ");
        sb.append(this.pan);
        sb.append(", ");
        sb.append(this.tripStatus.getVal());

        return sb.toString();
    }
}
