package com.pstu.acdps.server.service;

import java.util.Date;

@SuppressWarnings("deprecation")
public class SystemConstants {
    public static final Date startDate = new Date();
    public static final Date endDate = new Date();
    static{
        startDate.setYear(2013);
        startDate.setMonth(1);
        startDate.setDate(1);
        endDate.setYear(2033);
        endDate.setMonth(1);
        endDate.setDate(1);
    }
}
