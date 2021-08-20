import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DataContainerTest {

    /**
     * Метод для тестирования создания нового объекта
     * Проверка на длину создаваемого массива
     * @param number размер созданного массива
     * @param dataContainer массив различного типа, с пустым размером, либо с фиксированным
     */
    @ParameterizedTest
    @MethodSource("validNewArguments")
    public void validNewData(int number, DataContainer<Object> dataContainer) {
        Assertions.assertEquals(number, dataContainer.getItems().length);
    }

    public static Stream<Arguments> validNewArguments() {
        return Stream.of(
                Arguments.arguments(10, new DataContainer<Integer>()),
                Arguments.arguments(10, new DataContainer<String>()),
                Arguments.arguments(10, new DataContainer<Long>()),
                Arguments.arguments(10, new DataContainer<Double>()),
                Arguments.arguments(5, new DataContainer<Object>(new String[5]))
        );
    }

    @ParameterizedTest
    @MethodSource("validAddArguments")
    public void validAdd(int number, Object argument) {
        DataContainer<Object> data = new DataContainer<>();
        Assertions.assertEquals(number, data.add(argument));
    }

    public static Stream<Arguments> validAddArguments() {
        return Stream.of(
                Arguments.arguments(-1, null),
                Arguments.arguments(0, "qqqqq"),
                Arguments.arguments(0, "aaaaa"),
                Arguments.arguments(0, 1235)
        );
    }

    @Test
    public void validDelete() {

    }
}
