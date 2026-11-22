package itmo.ru.Musician.PanBeater;

import itmo.ru.Enums.InstrumentState;
import itmo.ru.Enums.SoundType;
import itmo.ru.Instrument.Instrument;
import itmo.ru.Instrumentalist.Instrumentalist;
import itmo.ru.Records.InstrumentSpec;

public class PanBeater extends Instrumentalist {

    public PanBeater(String name) {
        this(name, new Instrument(new InstrumentSpec("сковорода", SoundType.NOISE), InstrumentState.OK));
    }

    public PanBeater(String name, Instrument instrument) {
        super(name, instrument);
    }
}
