
package Beans;
public class Pending {
	
    private int id;
    private Processes source;
    private Processes destination;
    private int sequence;
    private Float asset;
    private int seq;

    /* Default Pending Class constructor without parameters */
    public Pending() {

    }
    
    public Pending(Processes source, Processes destination, int sequence, Float asset, int seq) {
		this.source = source;
		this.destination = destination;
		this.sequence=sequence;
		this.asset = asset;
		this.seq = seq;
	}

    /* Pending Class constructor with parameters */
    public Pending(int id, Processes source, Processes destination, int sequence, Float asset, int seq) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.sequence = sequence;
        this.asset = asset;
        this.seq = seq;
    }

    /* Set of getter & setter for Pending Class*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Processes getSource() {
        return source;
    }

    public void setSource(Processes source) {
        this.source = source;
    }

    public Processes getDestination() {
        return destination;
    }

    public void setDestination(Processes destination) {
        this.destination = destination;
    }

    public Float getAsset() {
        return asset;
    }

    public void setAsset(Float asset) {
        this.asset = asset;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /* Customized print function of the Transaction Class*/
    public void printPending() {
        System.out.println("************ Pending ************\n-id: " + this.id + "\n-Source: "
                + this.getSource().ToStringProcess() + "\n-Destination: " + this.getDestination().ToStringProcess() + "\n-Asset: " + this.asset
                + "\n-Sequence: " + this.sequence + "\n*************************************"
                + "\n-Seq: " + this.seq + "\n*************************************");
    }
    
    public String ToStringPending() {
    	return "************ Pending ************\n-id: " + this.id + "\n-Source: "+ this.source.ToStringProcess() + "\n-Destination: " + this.destination.ToStringProcess() + "\n-Asset: " + this.asset
                + "\n-Sequence: " + this.sequence + "\n*************************************"
                + "\n-Seq: " + this.seq + "\n*************************************";
    }

}
