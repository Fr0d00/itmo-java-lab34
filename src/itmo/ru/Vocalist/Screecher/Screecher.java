package itmo.ru.Vocalist.Screecher;

import itmo.ru.Enums.SoundType;
import itmo.ru.Vocalist.Vocalist;

public class Screecher extends Vocalist {

    public Screecher(String name) {
        super(name, SoundType.SCREECH);
    }

    public Screecher(String name, SoundType voiceType) {
        super(name, voiceType);
    }

    @Override
    public String voiceOf() {
        return "визжит";
    }
}
