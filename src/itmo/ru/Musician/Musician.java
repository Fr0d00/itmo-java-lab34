package itmo.ru.Musician;

import java.util.Objects;

import itmo.ru.Enums.MusicianRole;
import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.Records.SoundEvent;
import itmo.ru.SoundMaker.SoundMaker;

public abstract class Musician implements SoundMaker {

    protected final String name;
    protected final MusicianRole role;

    public Musician(String name, MusicianRole role) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя музыканта не может быть пустым");
        }
        if (role == null) {
            throw new IllegalArgumentException("Роль музыканта не может быть пустой");
        }

        this.name = name;
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    public MusicianRole getRole() {
        return role;
    }

    @Override
    public abstract SoundEvent makeSound() throws BrokenInstrumentException;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Musician other = (Musician) obj;
        return name.equals(other.name) && role == other.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + " role=" + role + "}";
    }
}
