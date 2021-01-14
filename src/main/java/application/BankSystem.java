package application;

import model.*;
import model.Currency;

import java.util.*;

public class BankSystem {
    private List<Employee> employees = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public Client verifyLogin(String login, String password) {
        for (Client client : clients) {
            if (client.getLogin().compareTo(login) == 0 && client.getPassword().compareTo(password) == 0) {
                return client;
            }
        }
        return null;
    }

    public void insertData() {
        Client client1 = new Client();
        client1.setId(1);
        client1.setLogin("client");
        client1.setPassword("client");
        HashSet<Account> accounts = new HashSet<>();
        Account account1 = new Account(1, 100, Currency.Polski_zloty);
        accounts.add(account1);
        Account account2 = new Account(2, 200, Currency.Euro);
        accounts.add(account2);
        client1.setAccounts(accounts);
        clients.add(client1);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Employee verifyLoginEmployee(String login, String password) {
        return new Employee();
    }

    public ArrayList<TransferHistory> getTransactionHistory(int clientId) {
        return new ArrayList<TransferHistory>();
    }

    public Map<Account, Integer> getMonthlyBalance(int clientID, int month) {
        HashMap<Account, Integer> hashMap = new HashMap<>();
        return hashMap;
    }

    String transaction(Account senderAccount, int receiverAccount,
                       String title, String address, float value) {
        Currency receiverCurrency = getReceiverCurrency(receiverAccount);
        if (senderAccount.sendMoney(value))
            if (senderAccount.getCurrency().equals(receiverCurrency))
                return "Ta sama waluta, wykonano przelew";
            else
                return "Inna waluta, wykonano przelew";
        else
            return "Zbyt mała liczba środków na koncie";
    }

  /*  String transaction(Account senderAccount, int receiverAccount, String title,
                       String address, float value, Currency receiverCurrency) {
        if(senderAccount.sendMoney(value))
            return "Inna waluta, wykonano przelew";
        else
            return "Inna waluta, zbyt mała liczba środków na koncie";
    }*/

    float getCurrencyExchangeRate(Currency senderCurrency, Currency receiverCurrency) {
        float ratio = 1.0f;
        if (senderCurrency == receiverCurrency) {
            ratio = 1.0f;
        } else if (senderCurrency == Currency.Euro) {
            if (receiverCurrency == Currency.Polski_zloty)
                ratio = 4.54f;
            else
                ratio = 1.22f;
        } else if (senderCurrency == Currency.Polski_zloty) {
            if (receiverCurrency == Currency.Euro)
                ratio = 0.22f;
            else
                ratio = 0.27f;
        } else {
            if (receiverCurrency == Currency.Euro)
                ratio = 0.82f;
            else
                ratio = 3.74f;
        }
        return ratio;
    }

    public boolean verifyReceiverAccount(int accountId) {
        if (accountId < 3) {
            return true;
        }
        return false;
    }

    public Currency getReceiverCurrency(int accountId) {
        if (accountId % 3 == 0)
            return Currency.Euro;
        else if (accountId % 3 == 1)
            return Currency.Polski_zloty;
        else
            return Currency.US_Dollar;
    }

}


