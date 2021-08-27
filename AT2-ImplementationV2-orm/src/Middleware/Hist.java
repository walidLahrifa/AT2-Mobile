package Middleware;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import DAO.DAOGenerator;
import Beans.*;
import java.util.Collections;

public class Hist extends HashMap<String, List<Transactions>>{
	
	DAOGenerator daog;
	//constructor with connectivity to DB
	public Hist() {
		this.daog = new DAOGenerator();
		System.out.println("Her is hist");
	}
	
	public List<Transactions> put(String key, List<Transactions> list) {
		
		Processes process = daog.getPdao().getbyidentifier(key);
		if(process!=null && list!=null) {
			for(Transactions t:list) {
			this.daog.getTdao().save(t);
			Transactions transaction = this.daog.getTdao().getAll().get(-1);
		
			//Transactions transaction = daog.getTdao().getbyid(value).get(); 
			this.daog.getHdao().save(new Beans.Hist(process, transaction));
			}
		}
		else {
			System.out.println("Process or transaction is null");
		}
		return null;
	}
	
	public List<Transactions> get(String key) {
		// get history of transaction of a specific process key
		System.out.println("Getting hist for a specific process: "+key);
		// retreive process history
		List<Beans.Hist> process_history = daog.getHdao().getAllbyprocess(key);
		// find transactions
		List<Transactions> list_transactions = new ArrayList<Transactions>();
		if(process_history.size()>0) {
			for(int i = 0; i < process_history.size(); i++) {
				list_transactions.add(process_history.get(i).getTransactions());
			}
			System.out.println("wld aisha"+list_transactions);
			return list_transactions;
		}else {
			System.out.println("No History is found");
			return Collections.emptyList();
		}
	}
	
	public int remove(String key) {
		return 0;
	}
}
