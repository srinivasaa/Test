package com.barclays.test.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Srinivasa
 */
public enum GateData {
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    A5("A5"),
    A6("A6"),
    A7("A7"),
    A8("A8"),
    A9("A9"),
    A10("A10"),
    CONCOURSE_A_TICKETING("Concourse_A_Ticketing"),
    BAGGAGE_CLAIM("BaggageClaim");

    public final String value;

    private GateData(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, GateData> GATE_MAP = new HashMap<String, GateData>();

    static {
        for (GateData gate : GateData.values()) {
            GATE_MAP.put(gate.getValue(), gate);
        }
    }

    public static GateData getGate(String value) {
        return GATE_MAP.get(value);
    }
}
