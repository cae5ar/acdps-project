/**
 * 
 */
package com.pstu.acdps.util.log;

/**
 * Abstract logger for logging events in the Meeting application.
 * 
 */
public abstract class AbstractLogger implements Logger {

    public Message createMessage() {
        return MessageFactory.createMessage();
    }

}
