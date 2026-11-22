package itmo.ru.Orchestra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import itmo.ru.Enums.SoundType;
import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.Exceptions.EmptyOrchestraException;
import itmo.ru.Musician.Musician;
import itmo.ru.Records.SoundEvent;
import itmo.ru.SoundMaker.SoundMaker;

public class Orchestra implements SoundMaker {
    private final String name;
    private final List<Musician> musicians;
    private final List<SoundEvent> journal;

    public Orchestra(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя оркестра не может быть пустым");
        }
        this.name = name;
        this.musicians = new ArrayList<>();
        this.journal = new ArrayList<>();
    }

    public void addMusician(Musician musician) {
        if (musician == null) {
            throw new IllegalArgumentException("Музыкант не может быть пустым");
        }
        musicians.add(musician);
    }

    public void playConcert() throws BrokenInstrumentException, EmptyOrchestraException {
        if (musicians.isEmpty()) {
            throw new EmptyOrchestraException(name);
        }

        BrokenInstrumentException firstFailure = null;
        for (Musician musician : musicians) {
            try {
                SoundEvent event = musician.makeSound();
                journal.add(event);
            } catch (BrokenInstrumentException e) {
                journal.add(new SoundEvent(musician.getName(),
                        "жалобный скрежет из сломанного инструмента", SoundType.SCREECH));
                if (firstFailure == null) {
                    firstFailure = e;
                }
            }
        }

        if (firstFailure != null) {
            throw firstFailure;
        }
    }

    @Override
    public SoundEvent makeSound() throws BrokenInstrumentException {
        if (journal.isEmpty()) {
            try {
                playConcert();
            } catch (EmptyOrchestraException e) {
                return new SoundEvent(name, "тишина", SoundType.SILENCE);
            }
        }
        return new SoundEvent(name, "какофония", SoundType.NOISE);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Musician> getMusicians() {
        return Collections.unmodifiableList(musicians);
    }

    public List<SoundEvent> getJournal() {
        return Collections.unmodifiableList(journal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Orchestra other = (Orchestra) obj;
        return name.equals(other.name) && musicians.equals(other.musicians) && journal.equals(other.journal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name, musicians, journal);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + " musicians=" + musicians + " journal=" + journal + "}";
    }
}
