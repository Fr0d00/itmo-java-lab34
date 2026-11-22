package itmo.ru.Spectator;

import java.util.Objects;

import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.Exceptions.InvalidToleranceException;
import itmo.ru.Listener.Listener;
import itmo.ru.Records.SoundEvent;
import itmo.ru.SoundMaker.SoundMaker;

public class Spectator implements Listener {
    private final String name;
    private int toleranceLevel;
    private boolean earsHurting;
    private boolean atHome;

    public Spectator(String name, int toleranceLevel) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя зрителя не может быть пустым");
        }
        if (toleranceLevel < 0) {
            throw new InvalidToleranceException(toleranceLevel);
        }
        this.name = name;
        this.toleranceLevel = toleranceLevel;
        this.earsHurting = false;
        this.atHome = false;
    }

    @Override
    public void listen(SoundMaker source) throws BrokenInstrumentException {
        if (source == null) {
            throw new IllegalArgumentException("Источник звука не может быть null");
        }

        SoundEvent event = source.makeSound();
        int damage = switch (event.type()) {
            case MELODIC -> 1;
            case NOISE -> 3;
            case SCREECH -> 5;
            case SILENCE -> 0;
        };

        toleranceLevel -= damage;
        if (toleranceLevel <= 0) {
            toleranceLevel = 0;
            earsHurting = true;
        }
    }

    @Override
    public boolean isEarsHurting() {
        return earsHurting;
    }

    public void goHome() {
        atHome = true;
    }

    public String getName() {
        return name;
    }

    public int getToleranceLevel() {
        return toleranceLevel;
    }

    public boolean isAtHome() {
        return atHome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Spectator other = (Spectator) obj;
        return name.equals(other.name) && toleranceLevel == other.toleranceLevel && earsHurting == other.earsHurting
                && atHome == other.atHome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name, toleranceLevel, earsHurting, atHome);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + " toleranceLevel=" + toleranceLevel + " earsHurting="
                + earsHurting + " atHome=" + atHome + "}";
    }
}
