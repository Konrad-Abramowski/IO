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

@Category({TestEntity.class})
@RunWith(Parameterized.class)
public class AccountTests {
    private Dane dane = new Dane();

    @Parameterized.Parameter(value = 50)
    public float value;

    @Test
    public void testSendMoney(){
        System.out.println("testSendMoney");
        Account account = dane.getAccount();
        account.sendMoney(value);
        assertTrue(account.getValue()==(350.0f));
    }

    // Testowanie setterow/getterow jest chyba bez sensu
}
