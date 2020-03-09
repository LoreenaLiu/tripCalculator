package calculator;

import model.Tap;
import model.TapType;
import model.Trip;
import model.TripStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class TripCalculatorTest {

    public TripCalculator tripCalculator;
    Tap firstTap;
    Tap lastTap;

    @BeforeEach
    public void setup(){
       tripCalculator = new TripCalculator();
        tripCalculator.tapMap = new HashMap<>();
        tripCalculator.tripList = new ArrayList<>();
       Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        firstTap = new Tap(1, TapType.ON, date, "Stop1", "company1","bus1","4444333322221111");
        lastTap = new Tap(1, TapType.OFF, date, "Stop2", "company1","bus1","4444333322221111");

    }


    @Test
    public void shouldGetCorrectAmount(){

        Date date = new Date();
        Tap tap1 = firstTap;
        Tap tap2 = lastTap;
        tap1.setStopId("Stop1");
        tap2.setStopId("Stop3");
        tripCalculator.addCompletedTrip(tap1,tap2);
        Trip trip = tripCalculator.tripList.get(0);
        Assertions.assertEquals(7.3, trip.getChargeAmount());
        Assertions.assertEquals(TripStatus._COMPLETED, trip.getTripStatus());
    }

    @Test
    public void shouldGetCorrectAmount2(){

        Date date = new Date();
        Tap tap1 = firstTap;
        Tap tap2 = lastTap;
        tap1.setStopId("Stop1");
        tap2.setStopId("Stop3");
        tripCalculator.addCompletedTrip(tap2,tap1);
        Trip trip = tripCalculator.tripList.get(0);
        Assertions.assertEquals(7.3, trip.getChargeAmount());
        Assertions.assertEquals(TripStatus._COMPLETED, trip.getTripStatus());
    }

    @Test
    public void shouldAddCorrectIncompleteTrip(){
        tripCalculator.addIncompletedTrip(firstTap);
        Trip trip = tripCalculator.tripList.get(0);
        Assertions.assertEquals(7.30, trip.getChargeAmount());
        Assertions.assertEquals(TripStatus._INCOMPLETED, trip.getTripStatus());
    }

    @Test
    public void shouldAddCorrectIncompleteTrip2(){
        tripCalculator.addIncompletedTrip(lastTap);
        Trip trip = tripCalculator.tripList.get(0);
        Assertions.assertEquals(5.50, trip.getChargeAmount());
        Assertions.assertEquals(TripStatus._INCOMPLETED, trip.getTripStatus());
    }


}
