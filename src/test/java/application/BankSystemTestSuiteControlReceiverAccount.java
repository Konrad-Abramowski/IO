package application;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@Categories.SuiteClasses({BankSystemTestSuiteControl.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(TestControl.class)
@Categories.ExcludeCategory(TestReceiverAccount.class)
public class BankSystemTestSuiteControlReceiverAccount {
}
