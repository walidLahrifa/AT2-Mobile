
package Beans;

public class Seq {
    private int id;
    private Processes identifier;
    private int seq_number;

    public Seq() {
    }
    
    public Seq(Processes identifier, int seq_number) {
        this.identifier = identifier;
        this.seq_number = seq_number;
    }

    public Seq(int id, Processes identifier, int seq_number) {
        this.id = id;
        this.identifier = identifier;
        this.seq_number = seq_number;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeq_number() {
        return this.seq_number;
    }

    public void setSeq_number(int seq_number) {
        this.seq_number = seq_number;
    }

    public Processes getProcess() {
        return this.identifier;
    }

    public void setProcess(Processes identifier) {
        this.identifier = identifier;
    }

    public void printSeq() {
        System.out.println("************ Seq ************\n-id: " + this.id + "\n-process: " + this.identifier.ToStringProcess() + "\n-Seq_number: " + this.seq_number + "\n*************************************");
    }
}
