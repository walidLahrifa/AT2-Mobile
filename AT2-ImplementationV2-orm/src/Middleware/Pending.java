package Middleware;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Beans.*;
import DAO.DAOGenerator;
public class Pending extends ArrayList<Pending_deps> {
	
	DAOGenerator daog;
	int len;
	
	public Pending() {
		this.daog = new DAOGenerator();
		this.len = daog.getPending_dao().getAll().size();
	}
	
	
	
	@SuppressWarnings("null")
	public void add(Beans.Pending pending, List<Beans.Deps> deps) {
		List<Transactions> transactions = null;
		for(int i=0;i<deps.size();i++) {
			transactions.add(deps.get(i).getTransactions());
		}
		for(int	i = 0;i<transactions.size();i++) {
			this.daog.getTdao().save(transactions.get(i)); //insert transactions into transactions
			this.daog.getPending_dep_dao().save(new Beans.Pending_deps(pending, transactions.get(i))); // associate dependencies
			}
		this.len++;
		}
	
	
	public int size() {
		return this.len;
	}
	
	public void removeAll() {
		
		for(int i=0;i<this.size();i++) {
			if(this.daog.getPending_dao().getbyid(i).isPresent()) {	// Check if pending exists
				Beans.Pending p = this.daog.getPending_dao().getbyid(i).get();  // get pending
				this.daog.getPending_dep_dao().deletebypending(p); // delete all dependencies of pending
				this.daog.getPending_dao().delete(p); // delete pending
			}
		}
		
	}
	
	public void removepending(Beans.Pending p) {
		
		if(this.daog.getPending_dao().getbyid(p.getId()).isPresent()) {	// Check if pending exists
			Beans.Pending pend = this.daog.getPending_dao().getbyid(p.getId()).get();  // get pending
			this.daog.getPending_dep_dao().deletebypending(p); // delete  all dependencies of pending
			this.daog.getPending_dao().delete(p); // delete pending
			}
	}

	public	List<Transactions> getbypending(int key){
		// get dependencies associated to a specific pending
		List<Transactions> transactions = new ArrayList<Transactions>();
		//HashMap<Beans.Pending, List<Transactions>>	hashmap = new HashMap<Beans.Pending, List<Transactions>>();
		//List<Tra> pending = null;
		if(this.daog.getPending_dao().getbyid(key).isPresent()) {	// Check if pending exists
			Beans.Pending p = this.daog.getPending_dao().getbyid(key).get();
			List<Beans.Pending_deps> pending_deps = this.daog.getPending_dep_dao().getbypending(p);
			for(int i=0;i<pending_deps.size();i++) {
				transactions.add(pending_deps.get(i).getTransaction());
			}
			//pending.add(p);
			//pending.add(transactions);
			return transactions;		
		}else {
			System.out.println("Pending is not found");
			return null;
		}
	}
	
	public HashMap<Beans.Pending, List<Transactions>>	getAll(){
		// get All pending and related dependencies
		HashMap<Beans.Pending, List<Transactions>> hashmap = new HashMap<Beans.Pending, List<Transactions>>();
		//HashMap<Integer, HashMap<Beans.Pending, ArrayList<Transactions>>>> list = new ArrayList<HashMap<Integer, HashMap<Beans.Pending, ArrayList<Transactions>>>>();
		
		List<Beans.Pending> lp = this.daog.getPending_dao().getAll();
		for(Beans.Pending elem:lp) {
			hashmap.put(elem, getbypending(elem.getId()));
			//list.add(hashmap);
		}
		return hashmap;
	}

	
}
