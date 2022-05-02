import org.testng.Assert;
import org.testng.annotations.*;
import utils.Retry;
@Listeners(utils.Listeners.class)
public class AdditionTest {

    private Calculator calculator = new Calculator();

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @Test(dataProvider = "add", description = "Successful addition test", priority = 2, invocationCount = 3, threadPoolSize = 3)
    public void additionTest(double a, double b){
        double expectedSum = a+b;
        double actualSum = calculator.addition(a,b);
        Assert.assertEquals(actualSum, expectedSum, "Actual sum doesn't match the expected one!");
    }

    @Test(retryAnalyzer = Retry.class, description = "Invalid test data")
    public void additionRetryTest(){
        double a = 2;
        double b = 2.5;
        double expectedSum = 5;
        double actualSum = calculator.addition(a,b);
        Assert.assertEquals(actualSum, expectedSum, "Actual sum doesn't match the expected one!");
    }

    @DataProvider(name = "add")
    public Object[][] add() {
        return new Object[][]{
                {10, 10.2},
                {1, -1}};
    }

}
