
package Beans;

public class Pending_deps {
	
    private int id;
    private Pending pending;
    private Transactions transaction;

    public Pending_deps() {
    }
    
    public Pending_deps(Pending pending, Transactions transaction) {
        this.pending = pending;
        this.transaction = transaction;
    }

    public Pending_deps(int id, Pending pending, Transactions transaction) {
        this.id = id;
        this.pending = pending;
        this.transaction = transaction;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transactions getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public Pending getPending() {
        return this.pending;
    }

    public void setPending(Pending pending) {
        this.pending = pending;
    }

    public void printPending_deps() {
        System.out.println("************ pending_deps ************\n-id: " + this.id + "\n-pending: " + this.pending.ToStringPending() + "\n-transactions: " + this.transaction.ToStringTransaction() + "\n*************************************");
    }
}
