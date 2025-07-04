import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    @DisplayName("Проверка имени на null")
    void throwsExceptionWhenFirstParameterIsNull() {
        String nullFirstParameterName = null;
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(nullFirstParameterName, 12, 12));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "   ", " \t \n "})
    @DisplayName("Проверка имени на пустые строки")
    void throwsExceptionForBlankName(String nullFirstParameterName) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(nullFirstParameterName, 12, 12));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -99999})
    @DisplayName("Проверка скорости на отрицательность")
    void throwsExceptionWhenSecondParameterIsNotNegative(Double secondSpeedArgument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Daniil", secondSpeedArgument, 12));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -99999})
    @DisplayName("Проверка дистанции на отрицательность")
    void throwsExceptionWhenThirdParameterIsNotNegative(Double thirdDistanceArgument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Daniil", 12, thirdDistanceArgument));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String expectedName = "Daniil";
        Horse horse = new Horse(expectedName, 12, 12);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 12;
        Horse horse = new Horse("Daniil", expectedSpeed, 12);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double expectedDistance = 12;
        Horse horse = new Horse("Daniil", 12, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistanceReturnsZeroWhenCreatedWithTwoParameters() {
        Horse horse = new Horse("Daniil", 12);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move() {

    }
}