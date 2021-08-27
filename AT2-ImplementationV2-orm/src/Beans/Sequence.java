
package Beans;

public class Sequence {
    private int id;
    private Processes identifier;
    private int sequence;

    public Sequence() {
    }
    public Sequence(Processes identifier, int sequence) {
        this.identifier = identifier;
        this.sequence = sequence;
    }
    public Sequence(int id, Processes identifier, int sequence) {
        this.id = id;
        this.identifier = identifier;
        this.sequence = sequence;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Processes getProcess() {
        return this.identifier;
    }

    public void setProcess(Processes identifier) {
        this.identifier = identifier;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void printSequence() {
        System.out.println("************ Sequence ************\n-id: " + this.id + "\n-identifier: " + this.identifier.ToStringProcess() + "\n-Sequence: " + this.sequence + "\n*************************************");
    }
}
