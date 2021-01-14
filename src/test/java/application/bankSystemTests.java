package application;

import model.Account;
import model.Client;
import model.Currency;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

@Category({TestEntity.class, TestControl.class})
@RunWith(Parameterized.class)
public class bankSystemTests {
    Dane dane = new Dane();

    @Parameterized.Parameter(value=0)
    public int accountID;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data1 = new Object[][]{ {0}, {1}, {2} };
        return Arrays.asList(data1);
    }

    @Test
    public void testVerifyReceiverAccount(){
        System.out.println("testVerifyReceiverAccount");
        assertTrue(dane.bankSystem().verifyReceiverAccount(accountID));

        // Dodatkowe testy
        for(int j=0; j<6; j++){
            if(j<3)
                assertTrue(dane.bankSystem().verifyReceiverAccount(j));
            else
                assertFalse(dane.bankSystem().verifyReceiverAccount(j));
        }
    }

    @Test
    public void verifyGetReceiverCurrency(){
        System.out.println("testGetReceiverCurrency");

        Map<Integer, Currency> map = dane.getIdToCurrencyMap();
        Iterator<Map.Entry<Integer, Currency>> itr = map.entrySet().iterator();

        while(itr.hasNext()){
            Map.Entry<Integer, Currency> entry = itr.next();
            assertEquals(dane.bankSystem().getReceiverCurrency(entry.getKey()), entry.getValue());
        }

    }
}
