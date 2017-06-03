package com.johnson.pablo.poesiaperuana.presentation.platform;

/**
 * Created by pjohnson on 15/03/17.
 */
public class Platform {

    private static Platform PLATFORM;

    /**
     * singleton method to retrieve the platform instance
     * @return a platform instance
     */
    public static Platform get() {
        if (PLATFORM == null) {
            PLATFORM = new Platform();
        }
        return PLATFORM;
    }

    /**
     * static method to set the platform
     * @param platform platform to be set
     */
    public static void setPlatform(Platform platform) {
        PLATFORM = platform;
    }

    protected Platform() {
    }
}
