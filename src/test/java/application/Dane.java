package application;

import model.Account;
import model.Client;
import model.Currency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Dane {

    public BankSystem bankSystem(){
        BankSystem bankSystem = new BankSystem();
        List<Client> clients = new ArrayList<>();
        clients.add(getExampleClient());
        bankSystem.setClients(clients);
        return bankSystem;
    }

    public Client getExampleClient() {
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
        return client1;
    }

    public List<Client> getExampleClients(){
        List<Client> clients = new ArrayList<>(){};
        clients.add(getExampleClient());
        Client client1 = new Client();
        client1.setId(1);
        client1.setLogin("client1");
        client1.setPassword("client1");
        HashSet<Account> accounts = new HashSet<>();
        Account account1 = new Account(3, 200, Currency.Polski_zloty);
        accounts.add(account1);
        Account account2 = new Account(4, 300, Currency.Euro);
        accounts.add(account2);
        client1.setAccounts(accounts);
        clients.add(client1);
        return clients;
    }

}
