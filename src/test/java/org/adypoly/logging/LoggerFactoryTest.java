package org.adypoly.logging;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(
        suiteName = "TestLoggerFactoryTest",
        description = "Checking for factory method functionalities, if proper object is returned or not"
)
public class LoggerFactoryTest {

    @Test(
            testName = "Check returned FileLogger instance"
    )
    void testFileLoggerFactoryMethod() {
        String name = "Logger1";
        String fileName = "logger1.txt";
        boolean haAppend = true;
        FileLogger logger = LoggerFactory.createFileLogger(name, fileName, haAppend);

        Assert.assertNotNull(logger);
        Assert.assertEquals(logger.getName(), name);
    }

}