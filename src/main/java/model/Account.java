package model;

public class Account {
    private int id;
    private float value;
    private Currency currency;
    private TransferHistory transferHistory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TransferHistory getTransferHistory() {
        return transferHistory;
    }

    public void setTransferHistory(TransferHistory transferHistory) {
        this.transferHistory = transferHistory;
    }
}
