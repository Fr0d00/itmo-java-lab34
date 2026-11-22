package itmo.ru.SoundMaker;

import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.Records.SoundEvent;

public interface SoundMaker {
    SoundEvent makeSound() throws BrokenInstrumentException;
    String getName();
}
