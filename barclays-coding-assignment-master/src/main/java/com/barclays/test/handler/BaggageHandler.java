package com.barclays.test.handler;

import com.barclays.test.data.GateData;
import com.barclays.test.impl.Baggage;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Srinivasa
 */
public class BaggageHandler implements InputHandler {

    private Map<String, Baggage> bagIdToBagMap;

    @Override
    public void process() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());

        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
            boolean startBagSection = false;
            boolean endBagSection = false;

            while (scanner.hasNextLine() && !endBagSection) {
                String line = scanner.nextLine();

                if (line.trim().equals("")){
                    continue;
                }

                if (line.startsWith("# Section:")) { //start of a new section
                    if (!line.endsWith("Bags")) { //skip
                        startBagSection = false;
                        continue;
                    } else if (line.endsWith("Bags")) {
                        startBagSection = true;
                        bagIdToBagMap = new LinkedHashMap<>();
                        continue;
                    } else if (startBagSection && !line.endsWith("Bags")) {
                        endBagSection = true;
                    }
                }

                if (startBagSection && !endBagSection) {
                    //Format: <bag_number> <entry_point> <flight_id>
                    String tokens[] = line.split(" ");
                    if (tokens.length != 3) {
                        throw new IOException("BAD INPUT FORMAT...");
                    }
                    String bagId = tokens[0];
                    GateData entryGate = GateData.getGate(tokens[1]);
                    String flightId = tokens[2];


                    if (entryGate == null) {
                        throw new IOException("INVALID ENTRY GATE FOUND...");
                    }

                    bagIdToBagMap.put(bagId, new Baggage(bagId, entryGate, flightId));
                }

            }
        }finally {
            scanner.close();
        }
    }

    public Map<String, Baggage> getBagIdToBagMap() {
        return bagIdToBagMap;
    }

    public void setBagIdToBagMap(Map<String, Baggage> bagIdToBagMap) {
        this.bagIdToBagMap = bagIdToBagMap;
    }
}
