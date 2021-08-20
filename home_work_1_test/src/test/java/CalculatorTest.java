import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void validPlus() {
        Assertions.assertEquals(2, calculator.plus(1, 1));
    }
}
