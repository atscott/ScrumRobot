/*
 * Copyright 2012 Marius Volkhart
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.msoe.se2800.h4;

import com.google.common.io.Closeables;

import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;

/**
 * Provides a mechanism for reporting events to a file.
 * 
 * @author Marius Volkhart
 */
public enum Logger {

    /**
     * The global instance of the Logger. All operations are thread safe.
     */
    INSTANCE;

    public static final String FILE_NAME = "output.log";
    private static final String[] EMPTY = new String[0];

    /**
     * Adds the provided message to the log. Is thread safe.
     * 
     * @param tag of the class making the call.
     * @param message to add to the log
     */
    public void log(String tag, String message) {
        log(tag, message, EMPTY);
    }

    /**
     * Example usage:
     * <p/>
     * <code>
     * public class PerfectPerson {<br/>
     * public static final String TAG = "Logger";<br/>
     * public PerfectPerson() {<br/>
     * Logger.INSTANCE.log(TAG, "My eyes are %s and my hair is %s", new String[]{"Green", "Blonde"});<br/> 
     * }<br/>
     * }<br/>
     * </code><br/>
     * will produce (PerfectPerson, My eyes are Green and my hair is Blonde).
     * 
     * @param tag of the class making the call.
     * @param message to be logged. Use %s to indicate fields.
     * @param args Strings to populate fields with. These need to be in the order they are found in
     *            the message
     */
    public void log(String tag, String message, String[] args) {
        synchronized (this) {
            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(FILE_NAME));
                DateFormat format = DateFormat.getInstance();

                // Print the date/timestamp
                writer.write(format.format(new Date()));
                writer.write(" | ");

                // Print the tag
                writer.write(tag);
                writer.write(" | ");

                if (StringUtils.countMatches(message, "%s") != args.length) {
                    throw new IllegalArgumentException("The number of placeholders in (" + message
                            + ") was not the same as the number of args (" + args.length + ").");
                }
                for (String s : args) {
                    message = StringUtils.replaceOnce(message, "%s", s);
                }

                // Print the message
                writer.write(message);
                writer.write("\n");
            } catch (FileNotFoundException e1) {
                System.out.println("The specified file could not be found.");
            } catch (IOException e) {
                System.out
                        .println("Unable to connect to the logger. This call to log() will be ignored.");
            } finally {
                if (writer != null) {
                    try {
                        Closeables.close(writer, true);
                    } catch (IOException e) {
                    }
                }
            }
        }

    }
}
