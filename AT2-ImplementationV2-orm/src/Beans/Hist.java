
package Beans;

public class Hist {
    private int id;
    private Processes identifier;
    private Transactions transactions;

    public Hist() {
    }
    
    public Hist(Processes identifier, Transactions transactions) {
        this.identifier = identifier;
        this.transactions = transactions;
    }

    public Hist(int id, Processes identifier, Transactions transactions) {
        this.id = id;
        this.identifier = identifier;
        this.transactions = transactions;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transactions getTransactions() {
        return this.transactions;
    }

    public void setTransaction(Transactions transactions) {
        this.transactions = transactions;
    }

    public Processes getProcess() {
        return this.identifier;
    }

    public void setProcess(Processes identifier) {
        this.identifier = identifier;
    }

    public void printHist() {
        System.out.println("************ Hist ************\n-id: " + this.id + "\n-process: " + this.identifier.ToStringProcess() + "\n-transactions: " + this.transactions.ToStringTransaction() + "\n*************************************");
    }
}
