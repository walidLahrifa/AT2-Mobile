
package Beans;

public class Signature {
    private int id;
    private Pending pending;
    private Processes identifier;
    private String sig;

    public Signature() {
    }
    
    public Signature(Pending pending, Processes identifier, String sig) {
        this.pending = pending;
        this.identifier = identifier;
        this.sig = sig;
    }

    public Signature(int id, Pending pending, Processes identifier, String sig) {
        this.id = id;
        this.pending = pending;
        this.identifier = identifier;
        this.sig = sig;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pending getPending() {
        return this.pending;
    }

    public void setPending(Pending pending) {
        this.pending = pending;
    }

    public Processes getProcess() {
        return this.identifier;
    }

    public void setProcess(Processes identifier) {
        this.identifier = identifier;
    }

    public String getSig() {
        return this.sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public void printSignature() {
        System.out.println("************ Signature ************\n-id: " + this.id + "\n-pending: " + this.pending.ToStringPending() + "\n-process: " + this.identifier.ToStringProcess() + "\n*************************************");
    }
}
