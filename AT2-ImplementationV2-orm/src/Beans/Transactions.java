/**
 * Author :           @ENNIARI
 * Creation Date :    11/08/2021
 * Last Update Date : 11/08/2021
 * Description :      Bean Class for Transaction Table
 * 
 */

package Beans;

import java.util.UUID;

public class Transactions {

	private int id;
	private Processes source;
	private Processes destination;
	private Float asset;
	private int seq;

	/* Default Transaction Class constructor without parameters */
	public Transactions() {

	}

	/* Transaction Class constructor with parameters */
	public Transactions(Processes source, Processes destination, Float asset, int seq) {
		this.source = source;
		this.destination = destination;
		this.asset = asset;
		this.seq = seq;
	}
	public Transactions(int id, Processes source, Processes destination, Float asset, int seq) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.asset = asset;
		this.seq = seq;
	}

/* Set of getter & setter for Transaction's Class*/

	public int getId() {
		return this.id;
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

	/* Customized print function of the Transaction Class*/
	public void printTransaction() {
		if(this.source == null || this.destination == null) {
			System.out.println("A source/destination process is not found");
		}else {
		
			System.out.println("************ Transaction ************\n-Id " + this.id+ "\n-Source: "
				+ this.source.getIdentifier() + "\n-Destination: " + this.destination.getIdentifier() + "\n-Asset: " + this.asset
				+ "\n-Sequence: " + this.seq + "\n*************************************");
	}}
	
	public String ToStringTransaction() {
		return "************ Transaction ************\n" + "\n-Source: "
				+ this.source.getIdentifier() + "\n-Destination: " + this.destination.getIdentifier() + "\n-Asset: " + this.asset
				+ "\n-Sequence: " + this.seq + "\n*************************************";
	}
	

}
