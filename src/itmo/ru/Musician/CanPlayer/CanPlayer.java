package itmo.ru.Musician.CanPlayer;

import itmo.ru.Enums.InstrumentState;
import itmo.ru.Enums.SoundType;
import itmo.ru.Instrument.Instrument;
import itmo.ru.Instrumentalist.Instrumentalist;
import itmo.ru.Records.InstrumentSpec;

public class CanPlayer extends Instrumentalist {

    public CanPlayer(String name) {
        this(name, new Instrument(new InstrumentSpec("консервная банка", SoundType.NOISE), InstrumentState.OK));
    }

    public CanPlayer(String name, Instrument instrument) {
        super(name, instrument);
    }
}
