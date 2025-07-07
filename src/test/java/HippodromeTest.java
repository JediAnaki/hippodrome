import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void throwsExceptionListHorsesIsNull() {
        List<Horse> horses = null;
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void throwsExceptionListHorsesOfBlank() {
        List<Horse> horses = List.of();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void returnsListOfHorsesPassedToConstructor() {
        List<Horse> expectedHorses = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            expectedHorses.add(new Horse("Horse" + i, i, i*2));
        }

        Hippodrome hippodrome = new Hippodrome(expectedHorses);

        List<Horse> actualHorses = hippodrome.getHorses();

        assertEquals(expectedHorses, actualHorses);
        assertEquals(30, actualHorses.size());

    }

    @Test
    void moveCallsMoveOnAllHorses() {
        List<Horse> expectedHorses = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            expectedHorses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(expectedHorses);
        hippodrome.move();

        for (Horse horse : expectedHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void findsHorseWithMaximumDistance() {
        Horse horse = new Horse("Daniil", 12, 12);
        Horse horse1 = new Horse("Daniil", 120, 120);
        Horse horse2 = new Horse("Daniil", 1200, 1200);

        List<Horse> horses = new ArrayList<>();

        horses.add(horse);
        horses.add(horse1);
        horses.add(horse2);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse2, hippodrome.getWinner());

    }
}