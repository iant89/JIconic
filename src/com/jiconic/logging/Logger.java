package com.jiconic.logging;
/*
 * Copyright (c) 2022 JIconic
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 * @author Ian Thomas
 */

import java.io.PrintStream;

public class Logger {
    
	/** Minimum Log Level required to log data **/
    private LogType logLevel = LogType.TRACE;
    
    /** Standard printing output **/
    private PrintStream standardOutput = System.out;
    
    /** Error printing output **/
    private PrintStream errorOutput = System.err;

    /**
     * Requests to log output with user-defined severity.
     * See {@link Logger#getLogType()} to get what minimum log type is required for output to be written to stream.
     */
    public void log(LogType type, Object...objects) {
    	if ( type == null ) {
    		this.log(LogType.ERROR, "Can not log with log type null.");
    		return;
    	}
    	
        if ( type.level < logLevel.level || logLevel == LogType.NONE )
            return;
        
        PrintStream outputStream = (type == LogType.ERROR) ? errorOutput : standardOutput;
        if ( outputStream == null )
        	return;
        
        for(Object o : objects) {        	
        	if ( o instanceof Exception ) {
        		((Exception)o).printStackTrace(outputStream);
        	} else {
        		outputStream.print(o);
        	}
        }
        
		outputStream.println();
    }

    /**
     * Requests to log output with {@link LogType#TRACE} severity.
     * See {@link Logger#getLogType()} to get what minimum log type is required for output to be written to stream.
     */
    public void trace(Object...objects) {
        this.log(LogType.TRACE, objects);
    }

    /**
     * Requests to log output with {@link LogType#DEBUG} severity.
     * See {@link Logger#getLogType()} to get what minimum log type is required for output to be written to stream.
     */
    public void debug(Object...objects) {
        this.log(LogType.DEBUG, objects);
    }
    
    /**
     * Requests to log output with {@link LogType#WARN} severity.
     * See {@link Logger#getLogType()} to get what minimum log type is required for output to be written to stream.
     */
    public void warn(Object...objects) {
        this.log(LogType.WARN, objects);
    }

    public void info(Object...objects) {
        this.log(LogType.INFO, objects);
    }

    /**
     * Requests to log output with {@link LogType#ERROR} severity.
     * See {@link Logger#getLogType()} to get what minimum log type is required for output to be written to stream.
     */
    public void error(Object...objects) {
        this.log(LogType.ERROR, objects);
    }
    
    /**
     * Sets the minimum log level required to log output.
     * Output requested to be written that is below this log type will be ignored.
     */
    public void setLogType(LogType type) {
        this.logLevel = type;
    }
    
    /**
     * Returns the minimum log level required to log output.
     */
    public LogType getLogType() {
        return this.logLevel;
    }
    
    /**
     * Returns the print stream used for normal output.
     */
    public PrintStream getStandardOutput() {
    	return this.standardOutput;
    }
    
    /**
     * Sets the print stream used for normal output.
     */
    public void setStandardOutput(PrintStream stream) {
    	this.standardOutput = stream;
    }
    
    /**
     * Returns the print stream used for error output.
     */
    public PrintStream getErrorOutput() {
    	return this.errorOutput;
    }
    
    /**
     * Sets the print stream used for error output.
     */
    public void setErrorOutput(PrintStream stream) {
    	this.errorOutput = stream;
    }

    public static enum LogType {
        TRACE(1),
        DEBUG(2),
        WARN(25),
        ERROR(50),
        INFO(100),
        NONE(Integer.MAX_VALUE);
        
        private int level;
        
        LogType(int level) {
            this.level = level;
        }
    }
}