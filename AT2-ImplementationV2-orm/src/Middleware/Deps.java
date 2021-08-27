package Middleware;
import java.util.ArrayList;
import java.util.List;

import Beans.*;
import DAO.DAOGenerator;

public class Deps extends ArrayList<Beans.Deps>{
	
	DAOGenerator daog;
	int len;
	
	public Deps() {
		this.daog = new DAOGenerator();
		this.len = daog.getDep_dao().getAll().size();
	}
	
	
	
	public void add(int index, Transactions element) {
		
		this.daog.getTdao().save(element);// insert the transaction into transactions
		this.daog.getDep_dao().save(new Beans.Deps(element)); // add dependency to the transaction in question
		this.len=len+1;
	}
	
	public int size() {
		return this.len;
	}
	
	public boolean removeAll() {
		
		if(this.daog.getDep_dao().deleteAll()==1) {
			this.len=0;
			return true;
		}else {
			return false;
		}
		
	}
	public List<Beans.Deps> get(){
		return this.daog.getDep_dao().getAll(); //get All dependencies in deps
	}
}
