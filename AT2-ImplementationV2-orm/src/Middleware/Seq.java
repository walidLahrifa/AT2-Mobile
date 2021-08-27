package Middleware;
import java.util.HashMap;
import DAO.SeqDAO;

public class Seq extends HashMap<String, Integer>{
	
	SeqDAO sdao;
	
	//constructor with connectivity to DB
	public Seq() {
		this.sdao = new SeqDAO();
	}
	
	public int put(String key, int value) {
		// associate or update a record number to a process
		
		System.out.println("Creatin a seq");
		
		if(this.sdao.getbyidentifier(key)!=null) {
			
			this.sdao.updatebyseq_number(key, value);
		}else {
			this.sdao.create_seq(key);
		}
		
		return value;
	}
	
	public int get(String key) {
	
		System.out.println("Getting seq for a specific process: "+key);
		
		if(this.sdao.getbyidentifier(key) == null) {
			System.out.println("Process doesnt exist");
					return -1;
				}
				
		return this.sdao.getbyidentifier(key).getSeq_number();
			}
			
	public int remove(String key) {
				
		int snumber = this.get(key);
		if(snumber<0) {
			return -1;
		}else {
			this.sdao.deletebyidentifier(key);
			return snumber;
			}	
		}
}
