package Middleware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Beans.*;

import DAO.DAOGenerator;

public class To_validate extends ArrayList<To_validate_deps>{
	
	DAOGenerator daog;
	int len;
	
	public To_validate() {
		this.daog = new DAOGenerator();
		this.len = daog.getTovalidate_dao().getAll().size();
	}
	
	
	
	public void add(Beans.To_validate to_validate, List<Transactions> process_dep) {
		
		for(int	i = 0;i<process_dep.size();i++) {
			this.daog.getTdao().save(process_dep.get(i)); //insert transactions into transactions
			this.daog.getTovalidate_dep_dao().save(new Beans.To_validate_deps(to_validate, process_dep.get(i))); // associate dependencies
			}
		this.len++;
		}
	
	
	public int size() {
		return this.len;
	}
	
	public void removebyid(int id) {
		
		this.daog.getTovalidate_dep_dao().deletebyTovalidateID(id); //delete dependecies
		this.daog.getTovalidate_dao().deletebyId(id); //delete tovalidate			
		
			}
	
	public void removeAll() {
		
		List<Beans.To_validate_deps> l_tov_deps = this.daog.getTovalidate_dep_dao().getAll();
		for(int i=0;i<l_tov_deps.size();i++) {
			Beans.To_validate tv = l_tov_deps.get(i).getTo_validate();  // get To_validate
			remove(tv.getId());
			}
		}
		
	
	public	HashMap<Beans.To_validate, List<Transactions>> getbytovalidateID(int key){
		// get dependencies associated to a specific pending
		List<Transactions> transactions = new ArrayList<Transactions>();
		HashMap<Beans.To_validate, List<Transactions>>	hashmap = new HashMap<Beans.To_validate, List<Transactions>>();
		
		if(this.daog.getTovalidate_dao().getbyid(key).isPresent()) {	// Check if pending exists
			Beans.To_validate tovalidate = 	this.daog.getTovalidate_dao().getbyid(key).get(); //get to validate
			
			List<Beans.To_validate_deps> list_tovalidate_deps = this.daog.getTovalidate_dep_dao().getbytovalidate(tovalidate);
			for(int i=0;i<list_tovalidate_deps.size();i++) {
				transactions.add(list_tovalidate_deps.get(i).getTransaction());
			}
			hashmap.put(tovalidate, (List<Transactions>) transactions);
			return hashmap;		
		}else {
			System.out.println("To_validate is not found");
			return null;
		}
	}
	
	public List<HashMap<Beans.To_validate, List<Transactions>>> getAll(){
		// get All pending and related dependencies
		HashMap<Beans.To_validate, List<Transactions>> hashmap = new HashMap<Beans.To_validate, List<Transactions>>();
		List<HashMap<Beans.To_validate, List<Transactions>>> list = new ArrayList<HashMap<Beans.To_validate, List<Transactions>>>();
		
		List<Beans.To_validate> lp = this.daog.getTovalidate_dao().getAll();
		for(int i=0;i<lp.size();i++) {
			list.add(getbytovalidateID(lp.get(i).getId()));
		}
		return (List<HashMap<Beans.To_validate, List<Transactions>>>) list;
	}

	
}

