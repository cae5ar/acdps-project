package com.pstu.acdps.util.log;

import java.util.Date;

/**
 * This class transfers information that we are logging.
 * 
 */
public class MessageObject implements Message {
    private String userName;
    private String method;
    private Date start;
    private Date end;
    private String message;

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#getUserName()
     */
    public String getUserName() {
        return userName;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#setUserName(java.lang.String)
     */
    public Message setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#getMethod()
     */
    public String getMethod() {
        return method;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#setMethod(java.lang.String)
     */
    public Message setMethod(String method) {
        this.method = method;
        return this;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#getStart()
     */
    public Date getStart() {
        return start;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#setStart(java.util.Date)
     */
    public Message setStart(Date start) {
        this.start = start;
        return this;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#getEnd()
     */
    public Date getEnd() {
        return end;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#setEnd(java.util.Date)
     */
    public Message setEnd(Date end) {
        this.end = end;
        return this;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#getMessage()
     */
    public String getMessage() {
        return message;
    }

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.IMessage#setMessage(java.lang.String)
     */
    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

}
