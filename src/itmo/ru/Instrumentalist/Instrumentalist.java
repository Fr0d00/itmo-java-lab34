package itmo.ru.Instrumentalist;

import itmo.ru.Musician.Musician;
import itmo.ru.Records.SoundEvent;
import itmo.ru.Instrument.Instrument;

import static itmo.ru.Enums.MusicianRole.*;

import java.util.Objects;

import itmo.ru.Exceptions.BrokenInstrumentException;

public abstract class Instrumentalist extends Musician {

    protected final Instrument instrument;

    public Instrumentalist(String name, Instrument instrument) {
        super(name, INSTRUMENTALIST);
        if (instrument == null) {
            throw new IllegalArgumentException("Инструмент не может быть пустым");
        }

        this.instrument = instrument;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public SoundEvent makeSound() throws BrokenInstrumentException {
        SoundEvent event = instrument.play();
        return new SoundEvent(name, event.sound(), event.type());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Instrumentalist other = (Instrumentalist) obj;
        return name.equals(other.getName()) && role.equals(other.getRole()) && instrument.equals(other.instrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), role, name, instrument);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + " role=" + role + " instrument=" + instrument.getName()
                + "}";
    }
}
