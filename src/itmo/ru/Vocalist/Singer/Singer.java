package itmo.ru.Vocalist.Singer;

import itmo.ru.Enums.SoundType;
import itmo.ru.Vocalist.Vocalist;

public class Singer extends Vocalist {

    public Singer(String name) {
        super(name, SoundType.MELODIC);
    }

    public Singer(String name, SoundType voiceType) {
        super(name, voiceType);
    }

    @Override
    public String voiceOf() {
        return "поёт";
    }
}
