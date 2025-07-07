import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

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
    void HorseNameChecking() {
        String expectedName = "Daniil";
        Horse horse = new Horse(expectedName, 12, 12);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    void HorseSpeedChecking() {
        double expectedSpeed = 12;
        Horse horse = new Horse("Daniil", expectedSpeed, 12);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void HorseDistanceChecking() {
        double expectedDistance = 12;
        Horse horse = new Horse("Daniil", 12, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistanceReturnsZeroWhenCreatedWithTwoParameters() {
        Horse horse = new Horse("Daniil", 12);
        assertEquals(0, horse.getDistance());
    }

    @ExtendWith(MockitoExtension.class)
    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.4, 0.7})
    void move(double randomNumbers) {
        double distance = 12;
        double speed = 12;

        Horse horse = new Horse("Daniil", speed, distance);

        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomNumbers);

            horse.move();
            double expectedDistance = distance + (speed * randomNumbers);
            assertEquals(expectedDistance, horse.getDistance());

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }
}