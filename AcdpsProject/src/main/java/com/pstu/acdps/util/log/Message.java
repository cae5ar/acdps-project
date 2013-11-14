package com.pstu.acdps.util.log;

import java.util.Date;

public interface Message {

    /**
     * @return the user
     */
    public abstract String getUserName();

    /**
     * @param userName the user to set
     */
    public abstract Message setUserName(String userName);

    /**
     * @return the method
     */
    public abstract String getMethod();

    /**
     * @param method the info to set
     */
    public abstract Message setMethod(String method);

    /**
     * @return the start
     */
    public abstract Date getStart();

    /**
     * @param start the start to set
     */
    public abstract Message setStart(Date start);

    /**
     * @return the end
     */
    public abstract Date getEnd();

    /**
     * @param end the end to set
     */
    public abstract Message setEnd(Date end);

    /**
     * @return the message
     */
    public abstract String getMessage();

    /**
     * @param message the message to set
     */
    public abstract Message setMessage(String message);

}