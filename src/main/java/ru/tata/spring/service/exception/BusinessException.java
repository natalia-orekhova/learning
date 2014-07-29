package ru.tata.spring.service.exception;

public abstract class BusinessException extends Exception {

    protected BusinessException(String template, Object... arguments) {
        super(String.format(template, arguments));
    }

    protected BusinessException(Throwable cause, String template, Object... arguments) {
        super(String.format(template, arguments), cause);
    }

    protected BusinessException(Throwable cause) {
        super(cause);
    }
}
