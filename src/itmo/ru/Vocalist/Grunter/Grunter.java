package itmo.ru.Vocalist.Grunter;

import itmo.ru.Enums.SoundType;
import itmo.ru.Vocalist.Vocalist;

public class Grunter extends Vocalist {

    public Grunter(String name) {
        super(name, SoundType.NOISE);
    }

    public Grunter(String name, SoundType voiceType) {
        super(name, voiceType);
    }

    @Override
    public String voiceOf() {
        return "хрюкает";
    }
}
