package itmo.ru.Vocalist.Squeaker;

import itmo.ru.Enums.SoundType;
import itmo.ru.Vocalist.Vocalist;

public class Squeaker extends Vocalist {

    public Squeaker(String name) {
        super(name, SoundType.SCREECH);
    }

    public Squeaker(String name, SoundType voiceType) {
        super(name, voiceType);
    }

    @Override
    public String voiceOf() {
        return "пищит";
    }
}
