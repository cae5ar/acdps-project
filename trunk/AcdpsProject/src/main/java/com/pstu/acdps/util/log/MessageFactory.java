/**
 * 
 */
package com.pstu.acdps.util.log;

/**
 */
public abstract class MessageFactory {
    /**
     * Creates new blank message.
     * 
     * @return appropriate instance of the {@link Message}
     */
    public static Message createMessage() {
        return new MessageObject();
    }
}
