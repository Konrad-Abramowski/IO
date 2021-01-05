package model;

import java.util.HashSet;

public class Client extends User {

    private HashSet<Account> accounts;

    public HashSet<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashSet<Account> accounts) {
        this.accounts = accounts;
    }
}
