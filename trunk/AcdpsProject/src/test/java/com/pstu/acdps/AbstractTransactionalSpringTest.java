/**
 * 
 */
package com.pstu.acdps;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 * Extend this class for testing Meeting application so you don't need to write
 * annotation on all the tests.
 */
@Configurable
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-security.xml"})
@Transactional
public abstract class AbstractTransactionalSpringTest extends AbstractTransactionalJUnit4SpringContextTests {

}
