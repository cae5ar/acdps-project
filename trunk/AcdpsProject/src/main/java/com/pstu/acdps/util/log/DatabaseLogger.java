/**
 * 
 */
package com.pstu.acdps.util.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This method logs information about the business method call into the
 * database.
 * 
 */
@Component
public class DatabaseLogger extends AbstractLogger {

    @Autowired
    private MessageRepository messageRepository;

    /* (non-Javadoc)
     * @see com.prognoz.meeting.util.log.Logger#log(com.prognoz.meeting.util.log.Message)
     */
    public void log(Message m) {
        messageRepository.save(m);
    }

}
