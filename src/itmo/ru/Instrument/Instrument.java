package itmo.ru.Instrument;

import static itmo.ru.Enums.InstrumentState.BROKEN;
import static itmo.ru.Enums.InstrumentState.OUT_OF_TUNE;

import java.util.Objects;

import itmo.ru.Enums.InstrumentState;
import itmo.ru.Enums.SoundType;
import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.Records.InstrumentSpec;
import itmo.ru.Records.SoundEvent;

public class Instrument {
    private final String name;
    private final SoundType baseType;
    private InstrumentState state;

    public Instrument(String name, InstrumentState state) {
        this(name, state, SoundType.MELODIC);
    }

    public Instrument(String name, InstrumentState state, SoundType baseType) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя инструмента не может быть пустым");
        }
        if (state == null) {
            throw new IllegalArgumentException("Состояние инструмента не может быть пустым");
        }
        if (baseType == null) {
            throw new IllegalArgumentException("Базовый тип звука не может быть пустым");
        }

        this.name = name;
        this.state = state;
        this.baseType = baseType;
    }

    public Instrument(InstrumentSpec spec, InstrumentState state) {
        this(spec.name(), state, spec.baseType());
    }

    public String getName() {
        return name;
    }

    public InstrumentState getState() {
        return state;
    }

    private String soundOf(SoundType type) {
        return switch (type) {
            case MELODIC -> "чистый звук из " + name;
            case NOISE -> "шум из " + name;
            case SCREECH -> "визг из " + name;
            case SILENCE -> name + " не издает звук";
        };
    }

    public SoundEvent play() throws BrokenInstrumentException {
        if (state == BROKEN) {
            throw new BrokenInstrumentException(name);
        }
        SoundType type = (state == OUT_OF_TUNE ? SoundType.NOISE : baseType);
        SoundEvent event = new SoundEvent(name, soundOf(type), type);

        if (state == InstrumentState.OK) {
            detune();
        } else {
            breakInstrument();
        }

        return event;
    }

    public void breakInstrument() {
        state = BROKEN;
    }

    public void detune() {
        state = OUT_OF_TUNE;
    }

    public void tune() {
        state = InstrumentState.OK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Instrument other = (Instrument) o;
        return name.equals(other.name) && state == other.state && baseType == other.baseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name, state, baseType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + " state=" + state + " baseType=" + baseType + "}";
    }
}
