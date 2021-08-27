
package Beans;

public class Deps {
    private int id;
    private Transactions transactions;

    public Deps() {
    }
    
    public Deps(Transactions transactions) {
        this.transactions = transactions;
    }

    public Deps(int id, Transactions transactions) {
        this.id = id;
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
    

    public void printDeps() {
        System.out.println("************ Deps ************\n-id: " + this.id + "\n-transactions: " + this.transactions.ToStringTransaction() + "\n*************************************");
    }
    public String ToStringDeps() {
        return "************ Deps ************\n-id: " + this.id + "\n-transactions: " + this.transactions.ToStringTransaction() + "\n*************************************";
    }
}
