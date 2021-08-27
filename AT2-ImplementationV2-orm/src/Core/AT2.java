package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import Middleware.*;
import Beans.*;
import Beans.Deps;
import DAO.DAOGenerator;
import DAO.DepsDAO;
import DAO.HistDAO;
import Signature.Sign;
import At2.*;


//  """implementation of the AT2 Deterministic protocol
public class AT2 extends At2{
	//DAO.DAOGenerator daog = new DAO.DAOGenerator();
	public	Middleware.Seq seq ;
	public  Middleware.Rec rec ;
	public  Middleware.Sequence sequence;
	public	Middleware.Deps deps ;
	public	Middleware.Hist hist ;
	public	Middleware.To_validate tovalidate;
	public	Middleware.Pending pending;
	public Middleware.Signature signature;
	
	public AT2() throws Exception {
		// construct with parent constructor;
		super();
		//init_variables(); //it is already in super() the function will be overrided by the one here
	}
	
	
	public void init_variables() {
		//Initializing all the variables
		System.out.println("Am here");
		this.seq = new Middleware.Seq();
		this.rec = new Middleware.Rec();
		this.sequence = new Middleware.Sequence();
		this.deps = new Middleware.Deps();
		this.hist = new Middleware.Hist();
		this.tovalidate = new Middleware.To_validate();
		this.pending = new Middleware.Pending();
		this.signature = new Middleware.Signature(null);
		//System.out.println(this.hist);
	}
	
	public Middleware.Signature getSignature() {
		return signature;
	}


	public void setSignature(Middleware.Signature signature) {
		this.signature = signature;
	}


	public Middleware.Seq getSeq() {
		return seq;
	}


	public void setSeq(Middleware.Seq seq) {
		this.seq = seq;
	}


	public Middleware.Rec getRec() {
		return rec;
	}


	public void setRec(Middleware.Rec rec) {
		this.rec = rec;
	}

	public Middleware.Sequence getSequence() {
		//System.out.println("Getting sequence middleware"+ this.sequence);
		return this.sequence;
	}


	public void setSequence(Middleware.Sequence sequence) {
		this.sequence = sequence;
	}


	public Middleware.Deps getDeps() {
		return deps;
	}


	public void setDeps(Middleware.Deps deps) {
		this.deps = deps;
	}


	public Middleware.To_validate getTovalidate() {
		return tovalidate;
	}


	public void setTovalidate(Middleware.To_validate tovalidate) {
		this.tovalidate = tovalidate;
	}


	public Middleware.Pending getPending() {
		return pending;
	}


	public void setPending(Middleware.Pending pending) {
		this.pending = pending;
	}


	public Middleware.Hist getHist() {
		return hist;
	}


	public void setHist(Middleware.Hist hist) {
		this.hist = hist;
	}


	public String current_process() throws Exception{
		//Implementation for the current process identifier
		Current_process current_process = this.getDaog().getCurrentprocess_dao().getcurrent_process();
		if(current_process != null) {
			return current_process.getIdentifier();
		}else {
			throw new Exception("Current Process doesnt exist");
		}
	}
	
	public List<Processes> getprocesses(){
		return this.getDaog().getPdao().getAll();
	}
	
	
	
	 public void reliable_broadcast(HashMap<String,Object> message) {
		 System.out.println("reliable_broadcast");
		 //message.put("delivered_from", this.current_process);
		 // // TODO send post request to all process
		 
	}
	 
	 
	 public void reliable_reply(HashMap<String,Object> message) {
		 System.out.println("reliable_reply");
		 String recipient = ((Beans.Pending) message.get("transfer")).getDestination().getIdentifier();
		 //message.put("delivered_from", this.current_process);
		 // Todo Send post request to recipient
	 }
	     
}
