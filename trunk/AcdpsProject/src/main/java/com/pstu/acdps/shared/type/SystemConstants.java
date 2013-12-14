package com.pstu.acdps.shared.type;

import java.util.Date;

@SuppressWarnings("deprecation")
public class SystemConstants {
    public static final Date startDate = new Date();
    public static final Date endDate = new Date();
    static{
        startDate.setYear(2013);
        startDate.setMonth(1);
        startDate.setDate(1);
        endDate.setYear(endDate.getYear() + 10);
        endDate.setMonth(0);
        endDate.setDate(1);
    }
}
