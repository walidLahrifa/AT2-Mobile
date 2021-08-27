package Beans;

public class Current_process {
    private String identifier;
    private String public_key;
    private String private_key;

    public Current_process() {
    }

    public Current_process(String identifier, String public_key, String private_key) {
        this.identifier = identifier;
        this.public_key = public_key;
        this.private_key = private_key;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPublic_key() {
        return this.public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPrivate_key() {
        return this.private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }


    public void printCurrentProcess() {
        System.out.println("************ Current Processes ************\n-identifier: " + this.identifier + "\n-public_key: " + this.public_key
                + "\n-private_key: " + this.private_key + "\n*************************************");
    }
}
