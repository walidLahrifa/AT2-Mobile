package Middleware;
import java.util.HashMap;
import DAO.SequenceDAO;

public class Sequence extends HashMap<String, Integer>{
	
	SequenceDAO sdao;
	
	//constructor with connectivity to DB
	public Sequence() {
		this.sdao = new SequenceDAO();
	}
	
	public int put(String key, int value) {
		// associate or update a record number to a process
		
		System.out.println("Creatin a sequence");
		
		if(this.sdao.getbyidentifier(key)!=null) {
			
			this.sdao.updatesequence(key, value);
		}else {
			this.sdao.create_sequence(key);
		}
		
		return value;
	}
	
	public Integer get(String key) {
	
		System.out.println("Getting Sequence for a specific process: "+key);
		//System.out.println("salam salam salama" + this.sdao.getbyidentifier(key));
		if(this.sdao.getbyidentifier(key) == null) {
			
			System.out.println("Process doesnt exist");
			return -1;
		}else {
			//System.out.println(this.sdao.getbyidentifier(key).getSequence());

			return this.sdao.getbyidentifier(key).getSequence();
	}}
			
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
