package application;

import model.Account;
import model.Client;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category({TestEntity.class})
@RunWith(Parameterized.class)
public class AccountTests {
    private Dane dane = new Dane();

    @Parameterized.Parameter(value = 0)
    public float transactionValue;

    @Parameterized.Parameter(value = 1)
    public float finalBalance;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data1 = new Object[][]{
                {50, 350},{100,300}};
        return Arrays.asList(data1);
    }

    @Test
    public void testSendMoney(){
        System.out.println("testSendMoney");
        Account account = dane.getAccount();
        account.sendMoney(transactionValue);
        assertTrue(account.getValue()==(finalBalance));
    }

    // Testowanie setterow/getterow jest chyba bez sensu
}
