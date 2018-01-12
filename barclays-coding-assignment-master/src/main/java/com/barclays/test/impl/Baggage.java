package com.barclays.test.impl;

import com.barclays.test.data.GateData;

/**
 *
 * @author Srinivasa
 */
public class Baggage {
    private String id;
    private GateData entryPoint;
    private String flightId;

    public Baggage(){

    }

    public Baggage(String bagId, GateData entryPoint, String flightId){
        this.id = bagId;
        this.entryPoint = entryPoint;
        this.flightId = flightId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GateData getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(GateData entryPoint) {
        this.entryPoint = entryPoint;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "id='" + id + '\'' +
                ", entryPoint=" + entryPoint +
                ", flightId='" + flightId + '\'' +
                '}';
    }
}
