package itmo.ru.Exceptions;

public class InvalidToleranceException extends RuntimeException {
    private final int invalidValue;

    public InvalidToleranceException(int invalidValue) {
        super();
        this.invalidValue = invalidValue;
    }

    public int getInvalidValue() {
        return invalidValue;
    }

    @Override
    public String getMessage() {
        return "недопустимое значение терпения: " + invalidValue;
    }
}
