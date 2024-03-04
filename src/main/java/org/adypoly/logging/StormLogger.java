package org.adypoly.logging;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Parent class of any concrete subclass loggers.
 *
 * It has all the basic functionality inherited from 'java.util.logging.Logger'.
 * Additionally, any subtype is forced to implement getInstance() method to provide
 * proper concrete instance accessing.
 *
 * @param <T> Can be any subclass type
 */
public abstract class StormLogger<T> extends Logger {

    // this format pattern can be used or overwritten
    protected static DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // this functional interface used to access simple record to date converter
    protected static Function<LogRecord, LocalDateTime> LOG_RECORD_TO_DATE_CONVERTER = (LogRecord record) ->
            record.getInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    private String resource;

    /**
     * This can be accessed by subclasses.
     * Method setUseParentHandlers() is used to disable default handlers.
     *
     * @param name for concrete logger
     * @param resource might be null or a resource file is needed
     */
    StormLogger(String name, String resource) {
        super(name, resource);
        this.setUseParentHandlers(false);
    }

    /**
     * This method template must be implemented for all subclasses.
     * At compile time the subtype is determined, so logic must be created for
     * runtime access.
     *
     * @return T subclass
     * @throws RuntimeException Exception that forces implementation
     */
    public static <T> T getInstance() throws RuntimeException {
        throw new RuntimeException("Must provide a concrete implementation.");
    }

    /**
     * This might throw an Illegal Pattern exception in case a wrong one is provided.
     *
     * @param pattern for new output format
     * @throws IllegalArgumentException Exception if pattern argument is invalid
     */
    public static void setDateTimePattern(String pattern) {
        StormLogger.DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(pattern);
    }

}
