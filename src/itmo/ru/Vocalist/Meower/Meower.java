package itmo.ru.Vocalist.Meower;

import itmo.ru.Enums.SoundType;
import itmo.ru.Vocalist.Vocalist;

public class Meower extends Vocalist {

    public Meower(String name) {
        super(name, SoundType.SCREECH);
    }

    public Meower(String name, SoundType voiceType) {
        super(name, voiceType);
    }

    @Override
    public String voiceOf() {
        return "мяукает";
    }
}
