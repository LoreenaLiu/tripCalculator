package calculator;

import model.Tap;
import model.TapType;
import model.Trip;
import model.TripStatus;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TripCalculator {


    Map<String, Tap> tapMap;

    List<Trip> tripList;

    public void tapProcess(){

        FileProcessor fileProcessor = new FileProcessor();
        List<Tap> tapList = fileProcessor.csvReader();
        tapMap = new HashMap<>();
        tripList = new ArrayList<>();
        for(Tap tap : tapList){
            if(!tapMap.containsKey(tap.getPan()) && tap.getTapType().equals(TapType.ON)){
                tapMap.put(tap.getPan(),tap);
            } else if(tapMap.containsKey(tap.getPan()) && tap.getTapType().equals(TapType.OFF)){
                if(tap.getStopId().equals(tapMap.get(tap.getPan()).getStopId())){
                    addCancelledTrip(tapMap.get(tap.getPan()), tap);
                    tapMap.remove(tap.getPan());
                }
                else {
                    addCompletedTrip(tapMap.get(tap.getPan()), tap);
                    tapMap.remove(tap.getPan());
                }
            }
            else if(tapMap.containsKey(tap.getPan()) && tap.getTapType().equals(TapType.ON)){
                    addIncompletedTrip(tapMap.get(tap.getPan()));
                    tapMap.remove(tap.getPan()); //remove the previous tapOn record, set it as a incomplete trip
                    tapMap.put(tap.getPan(),tap); // add the new one as a new record
            }

        }
        if(!tapMap.isEmpty()){
            for(Tap tap : tapMap.values()){
                addIncompletedTrip(tap);
                tapMap.remove(tap.getPan());
            }
        }


        fileProcessor.csvWriter(tripList);


    }

    public void addCancelledTrip(Tap firstTap, Tap lastTap){
        Trip trip = new Trip(firstTap.getDateTimeUTC(),lastTap.getDateTimeUTC(),getDurationSec(firstTap.getDateTimeUTC()
                , lastTap.getDateTimeUTC()),firstTap.getStopId(),lastTap.getStopId(),0.00, lastTap.getCompanyId(),lastTap.getBusID(),
                lastTap.getPan(), TripStatus._CANCELED);
        tripList.add(trip);
    }

    public void addCompletedTrip(Tap firstTap, Tap lastTap){
        String busStop = firstTap.getStopId()+lastTap.getStopId();
        Double amount = 0.00;
        switch(busStop)
        {
            case "Stop1Stop2" :
            case "Stop2Stop1" :
               amount = 3.25;
                break;
            case "Stop1Stop3" :
            case "Stop3Stop1" :
                amount = 7.30;
                break;
            case "Stop2Stop3" :
            case "Stop3Stop2" :
                amount = 5.50;
                break;

        }
        Trip trip = new Trip(firstTap.getDateTimeUTC(),lastTap.getDateTimeUTC(),getDurationSec(firstTap.getDateTimeUTC()
                , lastTap.getDateTimeUTC()),firstTap.getStopId(),lastTap.getStopId(),amount, lastTap.getCompanyId(),lastTap.getBusID(),
                lastTap.getPan(), TripStatus._COMPLETED);
        tripList.add(trip);
    }

    public void addIncompletedTrip(Tap tap){
        String busStop = tap.getStopId();
        Double amount = 0.00;
        switch(busStop)
        {
            case "Stop1" :
            case "Stop3" :
                amount = 7.30;
                break;
            case "Stop2" :
                amount = 5.50;
                break;

        }
        Trip trip = new Trip(tap.getDateTimeUTC(),tap.getDateTimeUTC(),getDurationSec(tap.getDateTimeUTC()
                , tap.getDateTimeUTC()),tap.getStopId(),tap.getStopId(),amount, tap.getCompanyId(),tap.getBusID(),
                tap.getPan(), TripStatus._INCOMPLETED);
        tripList.add(trip);
    }


    public long getDurationSec(Date from, Date to){
        long diff = to.getTime() - from.getTime();
        return TimeUnit.MILLISECONDS.toSeconds(diff);
    }

}
