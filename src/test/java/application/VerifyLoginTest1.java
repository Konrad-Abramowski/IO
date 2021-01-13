package application;

import model.Client;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;



@Category({TestEntity.class})
@RunWith(Parameterized.class)
public class VerifyLoginTest1 {

    private Dane dane = new Dane();

    @Parameterized.Parameter(value = 0)
    public String login;

    @Parameterized.Parameter(value = 1)
    public String password;

    @Parameterized.Parameters
    public static Collection<Object[]> correctClientCredentials(){
        Object[][] data1 = new Object[][]{
                {"client", "client"},{"client1","client1"}};
        return Arrays.asList(data1);
    }

    @Test
    public void testVerifyLoginForCorrectCredentials(){
        System.out.println("testVerifyLoginForCorrectCredentials");
        BankSystem bankSystem = dane.bankSystem();
        bankSystem.setClients(dane.getExampleClients());
        Client client = bankSystem.verifyLogin(login, password);
        assertEquals(login, client.getLogin());
        assertEquals(password,client.getPassword());

    }
}
