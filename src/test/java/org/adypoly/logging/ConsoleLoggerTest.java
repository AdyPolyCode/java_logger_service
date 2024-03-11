package org.adypoly.logging;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Handler;
import java.util.logging.Level;

@Test(
        suiteName = "TestConsoleLoggerTest",
        description = "Checking for logger instance attributes and methods"
)
public class ConsoleLoggerTest {

    private static ConsoleLogger logger;

    @BeforeClass
    static void init() {
        logger = ConsoleLogger.getInstance();
    }

    @Test(
            testName = "Check for logger singleton"
    )
    public void testGetInstance() {
        ConsoleLogger logger0 = ConsoleLogger.getInstance();
        Assert.assertEquals(logger, logger0);
    }

    @Test(
            testName = "Check if logger instance has correct attributes"
    )
    void testInstanceAttributes() {
        Assert.assertEquals(logger.getName(), ConsoleLogger.class.getSimpleName());
        Assert.assertEquals(logger.getHandlers().length, 1);
    }

    @Test(
            testName = "Check if logger instance handlers are correctly set"
    )
    void testInstanceHandlerAttributes() {
        Handler[] handlers = logger.getHandlers();

        for (Handler handler : handlers) {
            Assert.assertEquals(handler.getLevel(), Level.WARNING);
        }
    }

    @Test(
            testName = "Check if logger instance set formatter throws error"
    )
    void testSetFormatter() {
        String message = "Formatter must not be null.";
        Assert.assertThrows(message, RuntimeException.class, () -> {
            logger.setConsoleFormatter(null);
        });
    }

}