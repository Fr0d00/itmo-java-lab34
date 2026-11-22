package itmo.ru.Vocalist.Croaker;

import itmo.ru.Enums.SoundType;
import itmo.ru.Vocalist.Vocalist;

public class Croaker extends Vocalist {

    public Croaker(String name) {
        super(name, SoundType.NOISE);
    }

    public Croaker(String name, SoundType voiceType) {
        super(name, voiceType);
    }

    @Override
    public String voiceOf() {
        return "квакает";
    }
}
