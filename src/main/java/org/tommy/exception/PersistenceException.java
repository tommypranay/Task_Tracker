package org.tommy.exception;

public class PersistenceException extends RuntimeException {
    /**
     * A custom unchecked exception used to indicate failures during data persistence operations,
     * such as reading from or writing to a file or database.
     */

    /**
     * Constructs a new {@code PersistenceException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public PersistenceException(String message) { super(message); }

    /**
     * Constructs a new {@code PersistenceException} with the specified cause.
     *
     * @param cause The underlying cause of the exception.
     */
    public PersistenceException (Throwable cause) { super(cause); }

    /**
     * Constructs a new {@code PersistenceException} with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The underlying cause.
     */
    public PersistenceException (String message, Throwable cause) { super(message, cause); }
}
