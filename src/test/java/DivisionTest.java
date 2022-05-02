import org.testng.Assert;
import org.testng.annotations.*;
import utils.Retry;

@Listeners(utils.Listeners.class)
public class DivisionTest {
    private Calculator calculator = new Calculator();

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod");
    }

    @Test(dataProvider = "div", description = "Successful division test", priority = 2, invocationCount = 4, threadPoolSize = 2)
    public void divisionTest(double a, double b, double expected) {
        double expectedResult = expected;
        double actualResult = calculator.division(a, b);
        Assert.assertEquals(actualResult, expectedResult, "Actual result doesn't match the expected one!");
    }

    @Test(retryAnalyzer = Retry.class, description = "Invalid test data", priority = 2)
    public void divisionRetryTest() {
        double a = 2;
        double b = 2;
        double expectedResult = 5;
        double actualResult = calculator.division(a, b);
        Assert.assertEquals(actualResult, expectedResult, "Actual result doesn't match the expected one!");
    }

    @DataProvider(name = "div")
    public Object[][] div() {
        return new Object[][]{
                {6, -1.5, -4},
                {0, 0.33, 0}};
    }

}
