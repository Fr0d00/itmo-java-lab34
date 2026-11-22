package itmo.ru.Exceptions;

public class EmptyOrchestraException extends Exception {

    private final String orchestraName;

    public EmptyOrchestraException(String orchestraName) {
        super();

        if (orchestraName == null || orchestraName.isBlank()) {
            throw new IllegalArgumentException("orchestraName не может быть null или пустым");
        }
        this.orchestraName = orchestraName;
    }

    public String getName(){
        return orchestraName;
    }

    @Override
    public String getMessage() {
        return "Оркестр '" + orchestraName + "' пуст";
    }
}
