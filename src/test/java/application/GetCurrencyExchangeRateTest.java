package application;


import model.Currency;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

@Category({TestEntity.class})
@RunWith(Parameterized.class)
public class GetCurrencyExchangeRateTest {

    Dane dane = new Dane();

    @Parameterized.Parameter(value = 0)
    public Currency senderCurrency;

    @Parameterized.Parameter(value = 1)
    public Currency receiverCurrency;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data1 = new Object[][]{
                {Currency.Euro, Currency.Euro},
                {Currency.Polski_zloty, Currency.US_Dollar}};
        return Arrays.asList(data1);
    }

    @Test
    public void getCurrencyExchangeRateForTheSameCurrency() {
       if(senderCurrency == receiverCurrency){
           assertTrue(dane.bankSystem()
                   .getCurrencyExchangeRate(senderCurrency,receiverCurrency) == 1.0f);
       } else {
           assertFalse(dane.bankSystem()
                   .getCurrencyExchangeRate(senderCurrency,receiverCurrency) == 1.0f);
       }
    }
}
