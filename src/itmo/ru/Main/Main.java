package itmo.ru.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import itmo.ru.Enums.InstrumentState;
import itmo.ru.Enums.SoundType;
import itmo.ru.Exceptions.BrokenInstrumentException;
import itmo.ru.Exceptions.EmptyOrchestraException;
import itmo.ru.Exceptions.InvalidToleranceException;
import itmo.ru.Instrument.Instrument;
import itmo.ru.Instrumentalist.Instrumentalist;
import itmo.ru.Musician.CanPlayer.CanPlayer;
import itmo.ru.Musician.Musician;
import itmo.ru.Musician.PanBeater.PanBeater;
import itmo.ru.Orchestra.Orchestra;
import itmo.ru.Records.InstrumentSpec;
import itmo.ru.Records.SoundEvent;
import itmo.ru.Spectator.Spectator;
import itmo.ru.Vocalist.Croaker.Croaker;
import itmo.ru.Vocalist.Grunter.Grunter;
import itmo.ru.Vocalist.Meower.Meower;
import itmo.ru.Vocalist.Screecher.Screecher;
import itmo.ru.Vocalist.Singer.Singer;
import itmo.ru.Vocalist.Squeaker.Squeaker;
import itmo.ru.Vocalist.Vocalist;

public class Main {
    private static final String[] SHORTY_NAMES = {
            "Петя", "Вася", "Коля", "Миша", "Саша", "Гриша", "Толик", "Витя", "Сева", "Лёша"
    };

    private static final String[] SPECTATOR_NAMES = {
            "Гость", "Меломан", "Прохожий", "Слушатель", "Любитель музыки"
    };

    public static void main(String[] args) {
        Random rnd = new Random();

        Orchestra orchestra = createOrchestra(rnd);
        System.out.println("Оркестр \"" + orchestra.getName() + "\" состоит из "
                + orchestra.getMusicians().size() + " коротышек:");
        for (Musician musician : orchestra.getMusicians()) {
            System.out.println("  " + describe(musician));
        }

        System.out.println();
        System.out.println("Любители музыки собираются на концерт:");
        List<Spectator> spectators = createSpectators(rnd);
        for (Spectator spectator : spectators) {
            System.out.println("  " + spectator.getName() + " пришёл, терпение: "
                    + spectator.getToleranceLevel());
        }

        System.out.println();
        System.out.println("Концерт начинается!");
        BrokenInstrumentException failure = null;
        try {
            orchestra.playConcert();
        } catch (BrokenInstrumentException e) {
            failure = e;
        } catch (EmptyOrchestraException e) {
            System.out.println("  Концерт не состоялся: " + e.getMessage());
        }

        for (SoundEvent event : orchestra.getJournal()) {
            System.out.println("  " + event.musicianName() + ": " + event.sound());
        }

        if (failure != null) {
            System.out.println("  Во время концерта: " + failure.getMessage());
        }

        System.out.println();
        System.out.println("Зрители слушают...");
        for (Spectator spectator : spectators) {
            int before = spectator.getToleranceLevel();
            try {
                spectator.listen(orchestra);
                System.out.println("  " + spectator.getName() + ": терпение " + before
                        + " -> " + spectator.getToleranceLevel());
                if (spectator.isEarsHurting()) {
                    System.out.println("  " + spectator.getName() + ": уши болят!");
                }
            } catch (BrokenInstrumentException e) {
                System.out.println("  " + spectator.getName() + " не выдержал: " + e.getMessage());
            }
        }

        System.out.println();
        for (Spectator spectator : spectators) {
            spectator.goHome();
            if (spectator.isEarsHurting()) {
                System.out.println("  " + spectator.getName()
                        + " пошёл домой, проклиная какофонию и ветрофонию.");
            } else {
                System.out.println("  " + spectator.getName() + " пошёл домой.");
            }
        }

        System.out.println();
        try {
            Orchestra failedOrchestra = new Orchestra("Ветрофон-2");
            SoundEvent silence = failedOrchestra.makeSound();
            System.out.println("Оркестр \"" + failedOrchestra.getName()
                    + "\" так и не собрался: " + silence.sound() + ".");
        } catch (BrokenInstrumentException e) {
            System.out.println("Оркестр \"Ветрофон-2\" не смог выступить: " + e.getMessage());
        }
    }

    private static Orchestra createOrchestra(Random rnd) {
        List<String> names = new ArrayList<>(List.of(SHORTY_NAMES));
        Collections.shuffle(names, rnd);

        Orchestra orchestra = new Orchestra("Ветрофон");
        orchestra.addMusician(new CanPlayer(names.remove(0), randomWorkingInstrument(rnd, "консервная банка")));
        orchestra.addMusician(new Singer(names.remove(0)));
        orchestra.addMusician(new Squeaker(names.remove(0)));
        orchestra.addMusician(new Screecher(names.remove(0)));
        orchestra.addMusician(new Grunter(names.remove(0)));
        orchestra.addMusician(new Meower(names.remove(0)));
        orchestra.addMusician(new Croaker(names.remove(0)));
        orchestra.addMusician(new PanBeater(names.remove(0), randomInstrument(rnd, "сковорода")));
        orchestra.addMusician(new PanBeater(names.remove(0), randomInstrument(rnd, "сковорода")));
        orchestra.addMusician(new PanBeater(names.remove(0), randomInstrument(rnd, "сковорода")));
        return orchestra;
    }

    private static List<Spectator> createSpectators(Random rnd) {
        List<String> names = new ArrayList<>(List.of(SPECTATOR_NAMES));
        Collections.shuffle(names, rnd);

        int count = 3 + rnd.nextInt(3);
        List<Spectator> spectators = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int tolerance = rnd.nextInt(11);
            if (i == 0 && rnd.nextBoolean()) {
                tolerance = -1 - rnd.nextInt(3);
            }

            try {
                spectators.add(new Spectator(names.get(i), tolerance));
            } catch (InvalidToleranceException e) {
                System.out.println("  " + names.get(i) + " не попал на концерт: " + e.getMessage());
            }
        }
        return spectators;
    }

    private static Instrument randomInstrument(Random rnd, String name) {
        InstrumentState[] states = InstrumentState.values();
        InstrumentState state = states[rnd.nextInt(states.length)];
        return new Instrument(new InstrumentSpec(name, SoundType.NOISE), state);
    }

    private static Instrument randomWorkingInstrument(Random rnd, String name) {
        InstrumentState state = rnd.nextBoolean() ? InstrumentState.OK : InstrumentState.OUT_OF_TUNE;
        return new Instrument(new InstrumentSpec(name, SoundType.NOISE), state);
    }

    private static String describe(Musician musician) {
        if (musician instanceof Vocalist vocalist) {
            return vocalist.getName() + " " + vocalist.voiceOf();
        }
        if (musician instanceof Instrumentalist instrumentalist) {
            Instrument instrument = instrumentalist.getInstrument();
            return instrumentalist.getName() + " играет на инструменте \""
                    + instrument.getName() + "\" (" + instrument.getState() + ")";
        }
        return musician.getName();
    }
}
