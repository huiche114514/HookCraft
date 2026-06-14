package com.nanaki.hookcraft.config;

public class Config {
    public static class Combat {
        public static boolean critical = false;
        public static boolean killAura = false;
        public static boolean noDelay = false;
        public static boolean range = false;
    }

    public static class Movement {
        public static boolean autoSprint = false;
        public static boolean bhop = false;
        public static boolean speed = false;
        public static boolean velocity = false;
    }

    public static class Visual {
        public static boolean nightVision = false;
        public static boolean noFov = false;
        public static boolean noHurtCam = false;
    }

    public static class Hud {
        public static boolean modulesList = true;
    }
}
