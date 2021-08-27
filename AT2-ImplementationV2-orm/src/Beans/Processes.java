
package Beans;

public class Processes {
    private String identifier;
    private String public_key;
    private boolean valid = false;

    public Processes() {
    }

    public Processes(String identifier, String public_key, boolean valid) {
        this.identifier = identifier;
        this.public_key = public_key;
        this.valid = valid;
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

    public boolean getValid() {
        return this.valid;
    }

    public void setValid() {
        this.valid = true;
    }


    public void printProcess() {
        System.out.println("************ Processes ************\n-identifier: " + this.identifier + "\n-public_key: " + this.public_key
                + "\n-valid: " + this.valid + "\n*************************************");
    }
    
    public String ToStringProcess() {
        return "************ Processes ************\n-identifier: " + this.identifier + "\n-public_key: " + this.public_key
                + "\n-valid: " + this.valid + "\n*************************************";
    }
}
