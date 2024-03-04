package org.adypoly.logging;

import java.time.LocalDateTime;
import java.util.logging.*;

/**
 * A subtype of 'StormLogger', that can be used for console output.
 */
public final class ConsoleLogger extends StormLogger<ConsoleLogger> {

    private static ConsoleLogger instance;
    private static ConsoleHandler handler;
    private static SimpleFormatter formatter;

    /*
        Initializer block for preparing ConsoleHandler, and it's SimpleFormatter
        before an object is created via getInstance() method.
        Format pattern can be changed later if needed.
     */
    static {
        ConsoleHandler h = new ConsoleHandler();
        SimpleFormatter f = new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                LocalDateTime date = LOG_RECORD_TO_DATE_CONVERTER.apply(record);
                return "[" + record.getLevel() + "] " + DEFAULT_TIME_FORMATTER.format(date) + " - " + record.getMessage();
            }
        };

        h.setLevel(Level.WARNING);
        h.setFormatter(f);

        ConsoleLogger.handler = h;
        ConsoleLogger.formatter = f;
    }

    /**
     * Prevents creating more than one instances of this class.
     *
     * @param name
     * @param resource
     */
    private ConsoleLogger(String name, String resource) {
        super(name, resource);
        this.addHandler(ConsoleLogger.handler);
    }

    /**
     * Returns an only instance of this class.
     *
     * @return ConsoleLogger instance
     */
    public static ConsoleLogger getInstance() {
        if(ConsoleLogger.instance == null) {
            ConsoleLogger.instance = new ConsoleLogger(ConsoleLogger.class.getSimpleName(), null);
        }

        return ConsoleLogger.instance;
    }

    /**
     * Overwrite default formatter.
     * Proper format() method of 'SimpleFormatter' must be implemented.
     *
     * @param formatter
     * @throws RuntimeException Exception if argument is null type
     */
    public void setConsoleFormatter(SimpleFormatter formatter) throws RuntimeException {
        Handler[] handlers = ConsoleLogger.instance.getHandlers();

        if (formatter == null) {
            throw new RuntimeException("Formatter must not be null.");
        }

        for (Handler h:
             handlers) {
            if(h instanceof ConsoleHandler) {
                h.setFormatter(formatter);
            }
        }
    }

}
