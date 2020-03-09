package model;

public enum TripStatus {
    _COMPLETED("COMPLETED"),
    _INCOMPLETED("INCOMPLETED"),
    _CANCELED("CANCELED");


    private String val;

    TripStatus(String val) {
        this.val = val;
    }

    public String getVal(){
        return this.val;
    }

}
