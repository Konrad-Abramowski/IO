package application;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@Categories.SuiteClasses({VerifyLoginTest1.class,VerifyLoginTest2.class, GetCurrencyExchangeRateTest.class, AccountTests.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(TestControl.class)
public class BankSystemTestSuiteControl {
}
