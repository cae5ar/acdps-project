package com.pstu.acdps.shared.type;

import java.util.Date;

@SuppressWarnings("deprecation")
public class SystemConstants {
    public static final Date startDate = new Date();
    public static final Date endDate = new Date();
    public static final String roleDirectoryIdent = "ROLE_DIRECTORY";
    public static final String rolePaymentIdent = "ROLE_PAYMENT";
	public static final String roleEstimateIdent = "ROLE_ESTIMATE";
	public static final String roleReportIdent = "ROLE_REPORT";
    static{
        startDate.setYear(2013);
        startDate.setMonth(1);
        startDate.setDate(1);
        endDate.setYear(endDate.getYear() + 10);
        endDate.setMonth(0);
        endDate.setDate(1);
    }
}
