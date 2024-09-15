/**
 * 
 */
package com.indven.framework.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Deba prasad
 *
 */
public final class IndvenLogger {
	/**
	 * The Log used to log.
	 * 
	 */
	private static Log log = null;

	/**
	 * The private constructor for ProjectLogger.
	 * 
	 * @param clazz
	 *            Holds the Class.
	 */
	@SuppressWarnings("rawtypes")
	private IndvenLogger(final Class inputClass) {
		log = LogFactory.getLog(inputClass);
	}

	/**
	 * Returns the logger object, for a given client's class.
	 * 
	 * @param clazz
	 *            The class name to be logged.
	 * @return ProjectLogger The instantiated logger object.
	 */
	@SuppressWarnings("rawtypes")
	public static IndvenLogger getInstance(final Class inputClass) {
		return new IndvenLogger(inputClass);
	}

	/**
	 * Log message at DEBUG level.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void debug(final Object message) {
		log.debug(message);
	}

	/**
	 * Log message at DEBUG level.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The exception to be logged.
	 */
	public void debug(final Object message, final Throwable throwable) {
		log.debug(message, throwable);
	}

	/**
	 * Log message at WARN level.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void warn(final Object message) {
		log.warn(message);
	}

	/**
	 * Log message at WARN level.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The exception to be logged.
	 */
	public void warn(final Object message, final Throwable throwable) {
		log.warn(message, throwable);
	}

	/**
	 * Log message at TRACE level.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void trace(final Object message) {
		log.trace(message);
	}

	/**
	 * Log message at TRACE level.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The exception to be logged.
	 */
	public void trace(final Object message, final Throwable throwable) {
		log.trace(message, throwable);
	}

	/**
	 * Log message at ERROR level.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void error(final Object message) {
		log.error(message);
	}

	/**
	 * Log message at ERROR level.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The exception to be logged.
	 */
	public void error(final Object message, final Throwable throwable) {

		log.error(message, throwable);
	}

	/**
	 * Log message at FATAL level.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void fatal(final Object message) {
		log.fatal(message);
	}

	/**
	 * Log message at FATAL level.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The exception to be logged.
	 */
	public void fatal(final Object message, final Throwable throwable) {
		log.fatal(message, throwable);
	}

	/**
	 * Log message at INFO level.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void info(final Object message) {
		log.info(message);
	}

	/**
	 * Log message at INFO level.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param throwable
	 *            The exception to be logged.
	 */
	public void info(final Object message, final Throwable throwable) {
		log.info(message, throwable);
	}
}
