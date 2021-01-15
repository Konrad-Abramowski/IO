package application;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Categories.SuiteClasses({VerifyLoginTest1.class,VerifyLoginTest2.class, GetCurrencyExchangeRateTest.class, AccountTests.class, bankSystemTests.class})
@RunWith(Suite.class)
public class BankSystemTestSuite {
}
