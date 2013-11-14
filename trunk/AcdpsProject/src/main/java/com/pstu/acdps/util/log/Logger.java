/**
 * 
 */
package com.pstu.acdps.util.log;

public interface Logger {
    /**
     * Saves log message.
     * 
     * @param m message to be saved.
     */
    public void log(Message m);

    /**
     * Creates new blank message.
     * 
     * @see MessageFactory#createMessage()
     * @return appropriate instance of the {@link Message}
     */
    public Message createMessage();
}
