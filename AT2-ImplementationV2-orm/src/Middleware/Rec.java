package Middleware;
import java.util.HashMap;
import DAO.RecDAO;

public class Rec extends HashMap<String, Integer>{
	
	RecDAO rdao;
	
	//constructor with connectivity to DB
	public Rec() {
		this.rdao = new RecDAO();
	}
	
	public int put(String key, int value) {
		// associate or update a record number to a process
		
		
		
		if(this.rdao.getbyidentifier(key)!=null) {
			
			this.rdao.updatebyrec_number(key, value);
		}else {
			System.out.println("Creatin a rec");
			this.rdao.create_rec(key);
		}
		
		return value;
	}
	
	public int get(String key) {

		System.out.println("Getting rec_number for a specific process: "+key);
		
		if(this.rdao.getbyidentifier(key) == null) {
			System.out.println("Process doesnt exist");
			return -1;
		}
		
		return this.rdao.getbyidentifier(key).getRec_number();
	}
	
	public int remove(String key) {
		
		int rnumber = this.get(key);
		if(rnumber<0) {
			return -1;
		}else {
			this.rdao.deletebyidentifier(key);
			return rnumber;
		}	
	}
}
