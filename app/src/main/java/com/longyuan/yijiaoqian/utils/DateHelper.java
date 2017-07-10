package com.longyuan.yijiaoqian.utils;

import java.util.Date;

/**
 * Created by loxu on 10/07/2017.
 */

public class DateHelper {

    public static String dateDifference(Date dateStart, Date dateEnd){

        long difference = dateEnd.getTime() - dateStart.getTime();

        String outPutDifference = "";

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = difference / daysInMilli;

        if(elapsedDays> 0)
        {
            outPutDifference =   String.format("%d days ago", elapsedDays);

            return outPutDifference;

        }

        difference = difference % daysInMilli;

        long elapsedHours = difference / hoursInMilli;

        if(elapsedHours> 0)
        {
            outPutDifference =   String.format("%d hours ago", elapsedHours);

            return outPutDifference;

        }

        difference = difference % hoursInMilli;

        long elapsedMinutes = difference / minutesInMilli;

        if(elapsedMinutes> 0)
        {
            outPutDifference =   String.format("%d minutes ago", elapsedMinutes);
            return outPutDifference;

        }
        difference = difference % minutesInMilli;

        long elapsedSeconds = difference / secondsInMilli;

        if(elapsedSeconds> 0)
        {
            outPutDifference =   String.format("%d seconds ago", elapsedSeconds);
            return outPutDifference;

        }

        return outPutDifference;
    }



}
