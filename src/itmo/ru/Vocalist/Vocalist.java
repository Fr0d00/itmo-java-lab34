package itmo.ru.Vocalist;

import static itmo.ru.Enums.MusicianRole.VOCALIST;

import java.util.Objects;

import itmo.ru.Enums.SoundType;
import itmo.ru.Musician.Musician;
import itmo.ru.Records.SoundEvent;

public abstract class Vocalist extends Musician {
    protected final SoundType voiceType;

    public Vocalist(String name, SoundType voiceType) {
        super(name, VOCALIST);
        this.voiceType = voiceType;
    }

    public abstract String voiceOf();

    @Override
    public SoundEvent makeSound() {
        return new SoundEvent(name, voiceOf(), voiceType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vocalist other = (Vocalist) obj;
        return name.equals(other.getName()) && role.equals(other.getRole()) && voiceType.equals(other.voiceType)
                && voiceOf().equals(other.voiceOf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), role, name, voiceType, voiceOf());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + " role=" + role + " voiceType=" + voiceType + " voiceOf="
                + voiceOf()
                + "}";
    }

}
