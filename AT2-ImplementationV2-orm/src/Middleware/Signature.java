package Middleware;
import Beans.*;
import java.util.HashMap;
import java.util.List;

import DAO.DAOGenerator;


public class Signature extends HashMap<String, String>{ // <process_identifier, signature>
	
	DAOGenerator daog; //generate all daos in question in a single instance 
	Beans.Pending pending;
	int len;
	
	
	public Signature(Beans.Pending pending) {
		this.daog = new DAOGenerator();
		this.pending = pending;
		this.len = daog.getDep_dao().getAll().size();
	}
	public Beans.Pending getPending() {
		return pending;
	}

	public void setPending(Beans.Pending pending) {
		this.pending = pending;
	}
	
	public String put(String key, String value) {
		Beans.Processes process = this.daog.getPdao().getbyidentifier(key);
		this.daog.getSign_dao().save(new Beans.Signature(pending, process, value));
		this.len=len+1;
		return key+ " : " + value;
		
	}
	
	public String get(String key) {
		System.out.println("Getting signature by process and identifier");
		if(this.daog.getPdao().getbyidentifier(key)!=null) {
			return this.daog.getSign_dao().getbypending_and_identifier(this.pending, key).get(0).getSig();
		}else {
			System.out.println("Getting signature by process and identifier failed: No such process");
			return "null";
		}
	}
	public int size() {
		return this.len;
	}
	
	public List<Beans.Signature> getSignaturesByPending(){
		return this.daog.getSign_dao().getbypending(this.pending);
	}
	
	public void remove() {
		this.len=len-1;
	}

}
