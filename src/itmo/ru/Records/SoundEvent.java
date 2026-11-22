package itmo.ru.Records;

import itmo.ru.Enums.SoundType;


public record SoundEvent(String musicianName, String sound, SoundType type) {
}
