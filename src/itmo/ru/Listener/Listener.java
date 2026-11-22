package itmo.ru.Listener;

import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.SoundMaker.SoundMaker;

public interface Listener {
    void listen(SoundMaker source) throws BrokenInstrumentException;
    boolean isEarsHurting();
}
