package com.barclays.test.impl;

import com.barclays.test.data.GateData;

/**
 *
 * @author Srinivasa
 */
public class FlightDeparture {
    private String flightId;
    private GateData departureGate;
    private String destination;
    private String departureTime;

    public FlightDeparture(){
    }

    public FlightDeparture(String flightId, GateData departureGate, String destination, String departureTime){
        this.flightId = flightId;
        this.departureGate = departureGate;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public GateData getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(GateData departureGate) {
        this.departureGate = departureGate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "FlightDeparture{" +
                "flightId='" + flightId + '\'' +
                ", departureGate=" + departureGate +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}
