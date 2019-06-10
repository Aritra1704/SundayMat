package com.arpaul.sundatmat_aritra.constants;

public class Constant {

    private Constant(){}

    private static volatile Constant INSTANCE = null;
    private static final String SWITCH_ON = "SWITCH_ON";
    private static final String SWITCH_OFF = "SWITCH_OFF";
    private static final String DATE_TIME_FORMAT_UTC_MILIS   = "yyyy-MM-dd'T'HH:mm:ss.SSS";


    public static Constant getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new Constant();
        return INSTANCE;
    }

    public static String getSwitchOn() {
        return SWITCH_ON;
    }

    public static String getSwitchOff() {
        return SWITCH_OFF;
    }
}
