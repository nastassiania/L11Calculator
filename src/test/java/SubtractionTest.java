import org.testng.Assert;
import org.testng.annotations.*;
import utils.Retry;

@Listeners(utils.Listeners.class)
public class SubtractionTest {
    private Calculator calculator = new Calculator();

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @Test(dataProvider = "sub", description = "Successful subtraction test", priority = 1, invocationCount = 2, threadPoolSize = 2)
    public void subtractionTest(double a, double b, double expected){
        double expectedResult = expected;
        double actualResult = calculator.subtraction(a,b);
        Assert.assertEquals(actualResult, expectedResult, "Actual result doesn't match the expected one!");
    }

    @Test(retryAnalyzer = Retry.class, description = "Invalid test data", priority = 2)
    public void subtractionRetryTest(){
        double a = 8;
        double b = 2.5;
        double expectedResult = 5;
        double actualResult = calculator.subtraction(a,b);
        Assert.assertEquals(actualResult, expectedResult, "Actual result doesn't match the expected one!");
    }

    @DataProvider(name = "sub")
    public Object[][] sub() {
        return new Object[][]{
                {24.3, 1.04, 23.26},
                {-12, 0, -12}};
    }

}
