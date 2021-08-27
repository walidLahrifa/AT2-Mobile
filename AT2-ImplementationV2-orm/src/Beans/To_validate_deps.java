
package Beans;
//import java.util.List;

public class To_validate_deps {
    private int id;
    private To_validate to_validate;
    private Transactions transaction;

    public To_validate_deps() {
    }
    
    public To_validate_deps(To_validate to_validate, Transactions transaction) {
        this.to_validate = to_validate;
        this.transaction = transaction;
    }

    public To_validate_deps(int id, To_validate to_validate, Transactions transaction) {
        this.id = id;
        this.to_validate = to_validate;
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

    public To_validate getTo_validate() {
        return this.to_validate;
    }

    public void setTo_validate(To_validate to_validate) {
        this.to_validate = to_validate;
    }

    public void printTo_validate_deps() {
        System.out.println("************ To_validate_deps ************\n-id: " + this.getId() + "\n-To_validate: " + this.to_validate.ToStringTo_validate() + "\n-transactions: " + this.transaction.ToStringTransaction() + "\n*************************************");
    }
}
