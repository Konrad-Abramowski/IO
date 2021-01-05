package model;

public enum Currency {
    US_Dollar("USD"),
    Euro("EURO"),
    Polski_zloty("PLN");

    String currencyType;

    Currency(String currencyType) {
        this.currencyType = currencyType;
    }
}
