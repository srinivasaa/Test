package com.barclays.test.main;

import com.barclays.test.data.GateData;
import com.barclays.test.handler.BaggageHandler;
import com.barclays.test.handler.ConveyorPlotHandler;
import com.barclays.test.handler.FlightDepartureHandler;
import com.barclays.test.impl.Baggage;
import com.barclays.test.impl.ConveyorNode;
import com.barclays.test.impl.FlightDeparture;
import com.barclays.test.operations.ConveyorPlot;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Srinivasa
 */
public class AutomatedBaggageSystem {

    public static void main(String[] args) {

        ConveyorPlot conveyorPlot = null;
        Map<String, FlightDeparture> flightIdToDepartureMap = null;
        Map<String, Baggage> bagIdToBagMap = null;

        ConveyorPlotHandler conveyorGraphHandler = new ConveyorPlotHandler();
        try {
            conveyorGraphHandler.process();
            conveyorPlot = conveyorGraphHandler.getConveyorGraph();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        FlightDepartureHandler flightDepartureHandler = new FlightDepartureHandler();
        try {
            flightDepartureHandler.process();
            flightIdToDepartureMap = flightDepartureHandler.getFlightIdToDepartureMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        BaggageHandler bagHandler = new BaggageHandler();
        try {
            bagHandler.process();
            bagIdToBagMap = bagHandler.getBagIdToBagMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        StringBuffer output = new StringBuffer();

        for (Map.Entry<String, Baggage> entry : bagIdToBagMap.entrySet()) {
            Baggage bag = entry.getValue();
            String bagId = bag.getId();
            String flightId = bag.getFlightId();
            GateData sourceGate = bag.getEntryPoint();

            output.append(bagId + " ");

            GateData departureGate = null;
            if (flightId.equals("ARRIVAL")) {
                departureGate = sourceGate.BAGGAGE_CLAIM;
            } else {
                departureGate = flightIdToDepartureMap.get(flightId).getDepartureGate();
            }

            ConveyorNode sourceNode = new ConveyorNode(sourceGate, sourceGate.getValue());
            ConveyorNode targetNode = new ConveyorNode(departureGate, departureGate.getValue());
            List<ConveyorNode> shortestPath = conveyorPlot.findShortestPath(sourceNode, targetNode);

            if (!shortestPath.isEmpty()) {
                output.append(sourceGate.getValue() + " ");
                ConveyorNode prevNode = shortestPath.get(0);
                output.append(prevNode.getNodeId().getValue() + " ");

                for (int i = 1; i < shortestPath.size(); i++) {
                    ConveyorNode current = shortestPath.get(i);
                    prevNode = current;
                    output.append(current.getNodeId().getValue() + " ");
                }
                output.append(": " + prevNode.getMinDistance());
                output.append(System.lineSeparator());
            } else { //PATH NOT FOUND
                output.append("PATH_NOT_EXISTS");
                output.append(System.lineSeparator());
            }
        }

        System.out.println(output.toString());
    }
}
