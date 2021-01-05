package application;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankSystem {

    public Client verifyLogin(String login, String password) {
        return new Client();
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

    boolean verifyReceiverAccount(int receiverAccount) {
        return true;
    }
}
