import org.testng.Assert;
import org.testng.annotations.*;
import utils.Retry;

@Listeners(utils.Listeners.class)
public class MultiplicationTest {
    private Calculator calculator = new Calculator();

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @Test(dataProvider = "mult", description = "Successful multiplication test", priority = 1, invocationCount = 2, threadPoolSize = 2)
    public void multiplicationTest(double a, double b) {
        double expectedResult = a * b;
        double actualResult = calculator.multiplication(a, b);
        Assert.assertEquals(actualResult, expectedResult, "Actual result doesn't match the expected one!");
    }

    @Test(retryAnalyzer = Retry.class, description = "Invalid test data", priority = 2)
    public void multiplicationRetryTest() {
        double a = 2;
        double b = 2;
        double expectedResult = 5;
        double actualResult = calculator.multiplication(a, b);
        Assert.assertEquals(actualResult, expectedResult, "Actual result doesn't match the expected one!");
    }

    @DataProvider(name = "mult")
    public Object[][] mult() {
        return new Object[][]{
                {4.2, 5},
                {-6, 2.5}};
    }

}
