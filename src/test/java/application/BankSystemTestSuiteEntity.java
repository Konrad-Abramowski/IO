package application;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@Categories.SuiteClasses({VerifyLoginTest1.class,VerifyLoginTest2.class, GetCurrencyExchangeRateTest.class,})
@RunWith(Categories.class)
@Categories.ExcludeCategory(TestControl.class)
public class BankSystemTestSuiteEntity {
}
