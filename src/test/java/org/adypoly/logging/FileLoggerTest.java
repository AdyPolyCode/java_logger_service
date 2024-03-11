package org.adypoly.logging;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Handler;
import java.util.logging.Level;

import static org.testng.Assert.*;

@Test(
        suiteName = "TestFileLoggerTest",
        description = "Checking for logger instance attributes and methods"
)
public class FileLoggerTest {

    private static FileLogger logger;

    @BeforeClass
    static void init() {
        logger = new FileLogger("Logger2", "logger2.txt", true);
    }

    @Test(
            testName = "Check if logger instance has correct attributes"
    )
    void testInstanceAttributes() {
        Assert.assertEquals(logger.getName(), "Logger2");
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