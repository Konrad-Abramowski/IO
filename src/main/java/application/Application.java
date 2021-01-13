package application;

import model.*;
import model.Currency;

import java.time.LocalDateTime;
import java.util.*;

public class Application {

    private static BankSystem bankSystem = new BankSystem();

    private Client client;
    private Employee employee;
    private ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        bankSystem.insertData();
        Application app = new Application();
        app.mainMenu();
    }

    //-------- login
    public void login() {
        if (this.client != null) {
            return;
        }
        // Zmiana widoku na panel logowania
        System.out.println("--Logowanie--");
        // Na potrzeby testów -> panel tekstowy z wyborem odpowiedenij opcji
        System.out.println("Do którego panelu chcesz przejść?");
        System.out.println("1. Logowanie - klient");
        System.out.println("2. Logowanie - pracownik");
        System.out.println("0. Wyjście");
        System.out.println(">");
        // Pobieramy wartość z konsoli
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        do {
            if (a == 0)
                mainMenu();
            else if (a == 1)
                loginClient();
            else if (a == 2)
                loginEmployee();
            // Bledna wartość -> pobieramy jeszcze raz
            System.out.println(">");
            // Pobieramy wartość z konsoli
            a = scanner.nextInt();
        } while (true);
    }

    //-------- loginClient
    public void loginClient() {
        System.out.println("--Logowanie klienta--");
        int max_iterations = 5;
        while (client == null && max_iterations > 0) {
            // Pobranie danych z logowania
            // Na potrzeby testów -> wpisanie tekstowo w konsoli
            System.out.println("Login: ");
            Scanner scanner = new Scanner(System.in);
            String login = scanner.next();
            System.out.println("Hasło: "); // Security 100%
            String password = scanner.next();
            client = new Client();
            client = bankSystem.verifyLogin(login, password);
            if (client == null) {
                showErrorMessage("Błędne dane logowania!");
            }
            max_iterations--;
        }
        if (client != null) {
            mainMenu();
        }
    }


    //-------- loginEmployee
    public void loginEmployee() {
        System.out.println("--Logowanie pracownika--");
        while (employee == null) {
            // Pobranie danych z logowania
            // Na potrzeby testów -> wpisanie tekstowo w konsoli
            System.out.println("Login: ");
            Scanner scanner = new Scanner(System.in);
            String login = scanner.nextLine();
            System.out.println("Hasło: "); // Security 100%
            String password = scanner.nextLine();
            employee = bankSystem.verifyLoginEmployee(login, password);
            if (employee == null) {
                showErrorMessage("Błędne dane logowania!");
            }
        }
        // Wywolanie loginClient
        mainMenu();
        if (client == null) { // logowanie klienta nie powiodlo sie
            employee = null;
            login();
        }

    }

    //-------- showTransactionHistory
    public void showTransactionHistory() {
        System.out.println("--Historia transakcji--");
        login(); // Zalogowanie

        // Pobranie wymaganych danych klienta
        int clientID = client.getId();

        // Pobranie historii transakcji
        java.util.ArrayList<TransferHistory> history = bankSystem.getTransactionHistory(clientID);

        Iterator<TransferHistory> it = history.iterator();

        while (it.hasNext()) {
            var transaction = it.next();
            float value = transaction.getValue();
            Account sender = transaction.getSenderAccount();
            Account receiver = transaction.getReceiverAccount();
            LocalDateTime date = transaction.getDate();

            // Dodanie do interfejsu graficznego -> na potrzeby testow -> tekstowo
            if (client.getAccounts().contains(sender)) {
                System.out.println("[Wychodzacy] " + sender.getId() + "->" + receiver.getId()
                        + "  " + date + "  " + value);
            } else {
                System.out.println("[Przychodzacy] " + sender.getId() + "->" + receiver.getId()
                        + "  " + date + "  " + value);
            }
        }

        // Tekstowa symulacja nacisniecia przycisku
        System.out.println("Następna akcja?");
        System.out.println("1. Podsumowanie miesięczne");
        System.out.println("0. Wyjscie");
        // Pobieramy wartość z konsoli
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        do {
            if (a == 0)
                mainMenu();
            else if (a == 1) {
                monthlyBalanceClicked();
                break;
            }
            // Bledna wartość -> pobieramy jeszcze raz
            System.out.println(">");
            // Pobieramy wartość z konsoli

            a = scanner.nextInt();
        } while (true);

        mainMenu();
    }

    //-------- monthlyBalanceClicked
    public void monthlyBalanceClicked() {
        int clientID = client.getId();
        // Uzyskanie miesiąca
        System.out.println("Podaj miesiąc: ");
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();
        if (month < 0 || month > 12) {
            showErrorMessage("Błędny miesiąc");
            return;
        }
        java.util.Map<Account, Integer> monthlyBalance = bankSystem.getMonthlyBalance(clientID, month);
        // Dodanie do interfejsu graficznego -> tekstowo na potrzeby testów
        for (Map.Entry<Account, Integer> entry : monthlyBalance.entrySet())
            System.out.println(entry.getKey().getId() + " :: " + entry.getValue());
    }

    //-------- mainMenu()
    public void mainMenu() {
        // Zmiana widoku na glowny panel
        System.out.println("--Glowne okno aplikacji--");
        // Na potrzeby testów -> panel tekstowy z wyborem odpowiedenij opcji
        System.out.println("Do którego panelu chcesz przejść?");
        System.out.println("1. Logowanie");
        System.out.println("2. Wyswietl historie transakcji");
        System.out.println("3. Wykonaj przelew");
        System.out.println("0. Wyjście");
        Scanner scanner = new Scanner(System.in);
        boolean repeat = false;
        do {
            repeat = false;
            System.out.println(">");
            int a = scanner.nextInt();
            if (a == 0)
                System.exit(1); // Wyjscie z programu
            else if (a == 1)
                login();
            else if (a == 2)
                showTransactionHistory();
            else if (a == 3)
                transaction();
            else
                repeat = true;
        } while (repeat);
    }


    //-------- showErrorMessage
    public static void showErrorMessage(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    //-------- showMessage
    public static void showMessage(String message) {
        System.out.println("MESSAGE: " + message);
    }

    //-------- sendClicked
    public void sendClicked(Account senderAccount, int receiverAccount, String title, String address, float value) {
        // Sprawdzenie poprawności danych wprowadzonych w formularzu
        if (senderAccount == null) {
            showErrorMessage("Nie wybrano konta, z którego ma być wykonany przelew!");
            return;
        }
        if (!bankSystem.verifyReceiverAccount(receiverAccount)) {
            showErrorMessage("Bledny numer konta, na który ma być wykonany przelew!");
            return;
        }
        if (value <= 0) {
            showErrorMessage("Kwota przelewu nie może być mniejsza bądź równa 0!");
            return;
        }

        Currency receiverCurrency = bankSystem.getReceiverCurrency(receiverAccount);
        String message;
        if (receiverCurrency == senderAccount.getCurrency()) {
            message = bankSystem.transaction(senderAccount, receiverAccount, title, address, value);
        } else {
            message = bankSystem.transaction(senderAccount, receiverAccount, title, address, value, receiverCurrency);
        }

        showMessage(message);
    }


    public void transaction() {
        login();
        int clientID = client.getId();

        // w sendClicked jest pobranie danych z formularza transakcji, ale ciezko to zrobic tekstowo
        // Rozwiazanie dla testow -> przekazanie wartosci do sendClicked()
        System.out.println("Transakcja: ");
        Scanner scanner = new Scanner(System.in);
        // Pobranie sender account
        Account senderAccount = new Account();
        Iterator<Account> it = client.getAccounts().iterator();
        Account tmp;
        while (it.hasNext()) {
            tmp = it.next(); // Pobranie wartosci
            // Spytanie czy to ma byc to konto
            System.out.print("ID: " + tmp.getId() + "\nŚrodki: " + tmp.getValue() + "\nWaluta: " +
                    tmp.getCurrency() + "\n");
            System.out.print("Czy wybierasz to konto?: [tak/nie]\n>");
            String answer = scanner.nextLine();
            if (answer.compareTo("tak")==0 ) {
                senderAccount = tmp;
                break;
            }

        }
        System.out.print("ID konta odbiorcy\n>");
        int receiverAccount = scanner.nextInt();
        System.out.print("Tytuł przelewu\n>");
        String notUsed = scanner.nextLine();
        String title = scanner.nextLine();
        System.out.print("Adres\n>");
        String address = scanner.nextLine();
        System.out.print("Kwota\n>");
        float value = scanner.nextFloat();

        sendClicked(senderAccount, receiverAccount, title, address, value);
        mainMenu();
    }
}


