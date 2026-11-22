package itmo.ru.Exceptions;

public class BrokenInstrumentException extends Exception {
    private final String instrumentName;

    public BrokenInstrumentException(String instrumentName) {
        super();
        if (instrumentName == null || instrumentName.isBlank()) {
            throw new IllegalArgumentException("instrumentName не может быть null или пустым");
        }
        this.instrumentName = instrumentName;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    @Override
    public String getMessage() {
        return "Инструмент '" + instrumentName + "' сломан";
    }
}
