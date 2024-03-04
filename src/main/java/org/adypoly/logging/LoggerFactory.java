package org.adypoly.logging;

/**
 * Factory class of concrete Logger implementations.
 */
public class LoggerFactory {

    public static FileLogger createFileLogger(String name, String fileName, boolean hasAppend) {
        return new FileLogger(name, fileName, hasAppend);
    }

}
