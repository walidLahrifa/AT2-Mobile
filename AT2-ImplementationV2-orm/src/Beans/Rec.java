
package Beans;

public class Rec {
    private int id;
    private Processes identifier;
    private int rec_number;

    public Rec() {
    }

    public Rec(Processes identifier, int rec_number) {
        this.identifier = identifier;
        this.rec_number = rec_number;
    }
    public Rec(int id, Processes identifier, int rec_number) {
        this.id = id;
        this.identifier = identifier;
        this.rec_number = rec_number;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRec_number() {
        return this.rec_number;
    }

    public void setRec_number(int rec_number) {
        this.rec_number = rec_number;
    }

    public Processes getProcess() {
        return this.identifier;
    }

    public void setProcess(Processes identifier) {
        this.identifier = identifier;
    }

    public void printRec() {
    	if(this.identifier == null) {
			System.out.println(" process is not found");
		}else {
			System.out.println("************ Rec ************************************\n-id: " + this.id + "\n-process: " + this.identifier.ToStringProcess() + "\n-Rec_number: " + this.rec_number + "\n*************************************");
		}
    }
}
