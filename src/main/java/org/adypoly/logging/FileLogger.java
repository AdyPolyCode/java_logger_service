package org.adypoly.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * A subtype of 'StormLogger', that can be used for file output.
 */
public class FileLogger extends StormLogger<FileLogger> {

    private static String LOG_PATH;

    private FileHandler handler;
    private SimpleFormatter formatter;

    /*
        Initializer block for preparing file path for any OS
        and a directory for all the log files if not yet exists.
    */
    static {
        String separator = File.separator;
        StringBuffer buffer = new StringBuffer();
        buffer.append("src");
        buffer.append(separator);
        buffer.append("main");
        buffer.append(separator);
        buffer.append("resources");
        buffer.append(separator);
        buffer.append("logs");
        LOG_PATH = buffer.toString();

        File file = new File(LOG_PATH);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public FileLogger(String name, String fileName, boolean hasAppend) {
        super(name, null);
        try {
            String filePath = LOG_PATH + File.separator + fileName;
            FileHandler h = new FileHandler(filePath, hasAppend);
            SimpleFormatter f = new SimpleFormatter();

            h.setLevel(Level.WARNING);
            h.setFormatter(f);

            this.handler = h;
            this.formatter = f;
            this.addHandler(handler);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Overwrite default formatter.
     * Proper format() method of 'SimpleFormatter' must be implemented.
     *
     * @param formatter
     * @throws RuntimeException Exception if formatter arguments is null type
     */
    public void setConsoleFormatter(SimpleFormatter formatter) throws RuntimeException {
        Handler[] handlers = this.getHandlers();

        if (formatter == null) {
            throw new RuntimeException("Formatter must not be null.");
        }

        for (Handler h:
                handlers) {
            if(h instanceof FileHandler) {
                h.setFormatter(formatter);
            }
        }
    }

}
