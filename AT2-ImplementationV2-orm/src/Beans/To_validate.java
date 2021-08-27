package Beans;


public class To_validate {
	
    private int id;
    private Processes issuer;
    private Processes source;
    private Processes destination;
    private Float asset;
    private int seq;

    /* Default To_validate Class constructor without parameters */
    public To_validate() {

    }

    public To_validate(Processes issuer,Processes source, Processes destination, Float asset, int seq) {
        this.issuer = issuer;
        this.source = source;
        this.destination = destination;
        this.asset = asset;
        this.seq = seq;
    }

    /* To_validate Class constructor with parameters */
    public To_validate(int id, Processes issuer,Processes source, Processes destination, Float asset, int seq) {
        this.id = id;
        this.issuer = issuer;
        this.source = source;
        this.destination = destination;
        this.asset = asset;
        this.seq = seq;
    }

    /* Set of getter & setter for To_validate Class*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public void setIssuer(Processes issuer) {
        this.issuer = issuer;
    }

    public Processes getIssuer() {
        return issuer;
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

    /* Customized print function of the To_validate Class*/
    public void printTo_validate() {
        System.out.println("************ To_validate ************\n-id: " + this.id + "\n-Issuer: "
                + this.issuer.ToStringProcess() + "\n-Source: " + this.source.ToStringProcess() + "\n-Destination: " + this.destination.ToStringProcess() + "\n-Asset: " + this.asset
                + "\n-Sequence: " + this.seq + "\n*************************************");
    }
    public String ToStringTo_validate() {
        return "************ To_validate ************\n-id: " + this.id + "\n-Issuer: "
                + this.issuer.ToStringProcess() + "\n-Source: " + this.source.ToStringProcess() + "\n-Destination: " + this.destination.ToStringProcess() + "\n-Asset: " + this.asset
                + "\n-Sequence: " + this.seq + "\n*************************************";
    }

}
