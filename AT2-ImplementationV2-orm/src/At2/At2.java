package At2;
import java.security.PublicKey;
import java.security.Signature;
import java.util.*;
import Beans.*;
import DAO.DAOGenerator;
import Signature.*;
import Dict_List_manip.*;
import Middleware.Seq;




public class At2 {
	DAO.DAOGenerator daog ;
	HashMap<String, List<Transactions>> hist; //Set of validated transfers involving q
	List<Deps> deps; //Set of last incoming transfers for account of local process p
	HashMap<String, Integer> rec; //Number of delivered transfers from q
	HashMap<String, Integer> seq ; //Number of validated transfers outgoing from q
	HashMap<String, Integer> sequence;
	List<To_validate_deps> tovalidate; //Set of delivered (but not validated) transfers
	List<Pending_deps> pending; //messages waiting for confirmation
	HashMap<String, String> signature;
	
	
	public HashMap<String, String> PID ;
	public static int Initial_Balance = 100000000;// initial balance for a process P
    //indicators for processes
    public String current_process="AccountI";
    //public static ArrayList<byte[]> List_of_confirmed_signatures = new ArrayList<>();// Make a list of confirmed signatures
    
    public At2() throws Exception {
    	daog = new DAO.DAOGenerator(); 
    	this.PID = init_Process();
    	System.out.println("At2 initilaizing variables");
    	init_variables();
    	System.out.println("At2 finish initilaizing variables");
    	List<Beans.Processes> list_of_process = getprocesses();
    	
    	//this.current_process = current_process;	//should be current_process() in the future
    	}
    
    public void init_variables(){//Initializing Variables for processes
    	//is.daog = new DAO.DAOGenerator(); 
        System.out.println("The hell is here");
        this.seq = init_seq(this.seq);
        this.sequence= init_sequence(this.sequence);
        this.rec = init_rec(this.rec);
        this.hist = init_hist(this.hist);
        this.deps = init_deps();
        this.pending = init_pending();
        this.tovalidate = init_tovalidate();
        this.signature = new HashMap<String, String>();
    }
    
    public HashMap<String, Integer> init_sequence(HashMap<String, Integer> sequence){//Initializing Sequence numbers for variables
    	sequence.put(PID.get("AccountI"), 0);
    	sequence.put(PID.get("AccountII"), 0);
    	sequence.put(PID.get("AccountIII"), 0);
    	sequence.put(PID.get("AccountIV"), 0);
    	sequence.put(PID.get("AccountV"), 0);
    	sequence.put(PID.get("AccountVI"), 0);
        return sequence;
    }

    public HashMap<String, String> init_Process() {////Initializing Identifications for processes
    	HashMap<String, String> PID = new HashMap<String, String>();
    	PID.put("AccountI", "0a21daz1daz65");
        PID.put("AccountII", "1dza61dza6651");
        PID.put("AccountIII", "2zfe561fze616");
        PID.put("AccountIV", "3zg8rz2681z16");
        PID.put("AccountV", "4rgrhzzrgzrgzg");
        PID.put("AccountVI", "5fgfszfzzfrfzf");

        return PID;

    }

    public HashMap<String, Integer> init_seq(HashMap<String, Integer> seq) {//Initializing seq number for variables
        seq.put(PID.get("AccountI"), 0);
        seq.put(PID.get("AccountII"), 0);
        seq.put(PID.get("AccountIII"), 0);
        seq.put(PID.get("AccountIV"), 0);
        seq.put(PID.get("AccountV"), 0);
        seq.put(PID.get("AccountVI"), 0);
        return seq;
    }


    public HashMap<String, Integer> init_rec(HashMap<String, Integer> rec) {//Initializing rec number for variables
        rec.put(PID.get("AccountI"), 0);
        rec.put(PID.get("AccountII"), 0);
        rec.put(PID.get("AccountIII"), 0);
        rec.put(PID.get("AccountIV"), 0);
        rec.put(PID.get("AccountV"), 0);
        rec.put(PID.get("AccountVI"), 0);
        return rec;
    }
    public HashMap<String, List<Transactions>> init_hist(HashMap<String, List<Transactions>> hist) {//Initializing hist for variables
    	hist = new HashMap<String, List<Transactions>>();
    	hist.put(PID.get("AccountI"), new ArrayList<Transactions>());
    	hist.put(PID.get("AccountII"), new ArrayList<Transactions>());
    	hist.put(PID.get("AccountIII"), new ArrayList<Transactions>());
    	hist.put(PID.get("AccountIV"), new ArrayList<Transactions>());
    	hist.put(PID.get("AccountV"), new ArrayList<Transactions>());
    	hist.put(PID.get("AccountVI"), new ArrayList<Transactions>());
    	System.out.println("Salam " + hist);
        return hist;
    }
    
    public List<Deps> init_deps() {//Initializing rec number for variables
        List<Deps> list = new ArrayList<Deps>();
        return list;
    }
    public List<To_validate_deps> init_tovalidate() {//Initializing rec number for variables
    	List<To_validate_deps> list = new ArrayList<To_validate_deps>();
        return list;
    }
    public List<Pending_deps> init_pending(){
    	List<Pending_deps> list = new ArrayList<Pending_deps>();

    	return list;
    }
    public DAO.DAOGenerator getDaog() {
		return daog;
	}

	public void setDaog(DAO.DAOGenerator daog) {
		this.daog = daog;
	}


    public boolean Transfer(String sender_account, String receiver_account, int amount) throws Exception {
    	System.out.println(this.getHist().getClass());
    	//System.out.println(this.getPID());
        //Transfer an amount of x from account a to account b
    	//Map<String, Object> Parameter_transaction = new HashMap<String, Object>();//created to contain the list (a,b, x,seq[p] + 1)
    	System.out.println(this.getHist());
    	//this.hist=this.getHist();
        String sender_id = this.getPID().get(current_process); //get source_id in order to use it to fetch the histories of the sender
        String receiver_id = this.getPID().get(receiver_account);//get receiver_id in order to use it to fetch the histories of the receiver
        
        List<Transactions> process_hist = new ArrayList<Transactions>();
        process_hist = this.getHist().get(sender_id);//get the sender histories from hist
        System.out.println(process_hist);
        List<Transactions> process_dep = new ArrayList<Transactions>();
        
        if(this.getDeps() instanceof List<Deps>) { // ensure flexibility 
        	for(int i =0;i<this.getDeps().size();i++) {
        		process_dep.add(this.getDeps().get(i).getTransactions());} 			//get the sender dependencies from deps
        }
        else if(this.getDeps() instanceof Middleware.Deps){
        	for(Deps d:((Middleware.Deps) this.getDeps()).get()) {
        		process_dep.add(d.getTransactions());
        }}
        //------------------------------------- Checking Balance of the sender -----------------------------------------

        //ArrayList<Object> Parameter_transfer = new ArrayList<>(); // constructing [(a,b, x,seq[p] + 1), deps, sequence]
        List<Transactions> h = List_manup.merge(process_hist, process_dep); // merge transactions from hist and deps
        h = List_manup.removeDuplicates(h);
        
        if (balance(sender_account, h) < amount) { //check if the initial balance is superior to the amount to send
            System.out.println("fund not enough"); // Balance is not sufficient
            return false;
        }
        else {
            //--------------------------  Making of the list (a,b, x,seq[p] + 1)  ----------------------------------------
        	System.out.println(sender_id);
        	System.out.println(receiver_account);
        	System.out.println(receiver_id);
        	System.out.println("Here creating pending " + ((Middleware.Sequence) this.getSequence()).get(sender_id));
        	//Middleware.Sequence gg = new Middleware.Sequence();
        	//System.out.println(gg.get(sender_id));
        	//System.out.println(this.getSequence().get("1dza61dza6651"));
        	//
        	this.getDaog().getPending_dao().save(new Beans.Pending(this.getDaog().getPdao().getbyidentifier(sender_id), this.getDaog().getPdao().getbyidentifier(receiver_id),  ((Middleware.Sequence) this.getSequence()).get(sender_id), (float)amount, ((Middleware.Seq) this.getSeq()).get(sender_id) + 1));
        	Beans.Pending p = this.getDaog().getPending_dao().getAll().get(this.getDaog().getPending_dao().getAll().size()-1);
        	//Parameter_transaction.put("sender", sender_id);//Adding the sender to the message
            //Parameter_transaction.put("receiver", receiver_id);//Adding the receiver to the message
            //Parameter_transaction.put("amount", amount);//Adding the amount to the message
            //Parameter_transaction.put("seq", this.seq.get(sender_id) + 1);//Adding the seq number to the message

            //------------------- Making of the list of list [(a,b, x,seq[p] + 1), deps, sequence] ---------------------

            //Parameter_transfer.add(Parameter_transaction);//adding (a,b, x,seq[p] + 1)  to the list of list [(a,b, x,seq[p] + 1), deps,sequence]
            //Parameter_transfer.add(returnDeps());//adding deps  to the list of list [(a,b, x,seq[p] + 1), deps,sequence]
            //Parameter_transfer.add(this.sequence.get(sender_id));//adding sequence number  to the list of list [(a,b, x,seq[p] + 1), deps,sequence]

            //----------------------------------------------Secure Broadcast -------------------------------------------
        	
        	//((Middleware.Sequence) this.getSequence()).put(this.getPID().get(sender_account),((Middleware.Sequence)this.getSequence()).get(sender_id) + 1);//increment the sequence number f

            //((Middleware.Pending) this.getPending()).add(p, returnDeps());
        	((Middleware.Sequence)this.getSequence()).put(sender_id, 1);
        	((Middleware.Seq)this.getSeq()).put(sender_id, 1);
            
            HashMap<String, Object> message = new HashMap<String, Object>();
            message.put("transfer", p);
            message.put("deps", process_dep);
            message.put("Signature", null);
            message.put("List_VSignatures", null);
            // --------------------------------------- Broadcasting to Processes ---------------------------------------
            // should be replaced with broadcast([(a,b, x,seq[p] + 1), deps, sequence])
            // I used reliable deliver method because we don't have yet the reliable_Broadcast ready yet
            // We suppose here that the message is delivered to a process P to witness.
            reliable_deliver("3zg8rz2681z16", message); //TODO change to brodcast //first argument is AccountIV
        }
        //Emptying the dependencies of the sender because he is sending a transfer
        ((Middleware.Deps) this.getDeps()).removeAll();
        return true	;
    }


    public HashMap<String, List<Transactions>> getHist() {
		return hist;
	}

	public void setHist(HashMap<String, List<Transactions>> hist) {
		this.hist = hist;
	}
	

	public List<Deps> getDeps() {
		return deps;
	}

	public void setDeps(List<Deps> deps) {
		this.deps = deps;
	}

	public HashMap<String, Integer> getRec() {
		return rec;
	}

	public void setRec(HashMap<String, Integer> rec) {
		this.rec = rec;
	}

	public HashMap<String, Integer> getSeq() {
		return seq;
	}

	public void setSeq(HashMap<String, Integer> seq) {
		this.seq = seq;
	}

	public HashMap<String, Integer> getSequence() {
		return this.sequence;
	}

	public void setSequence(HashMap<String, Integer> sequence) {
		this.sequence = sequence;
	}

	public List<To_validate_deps> getTovalidate() {
		return tovalidate;
	}

	public void setTovalidate(List<To_validate_deps> tovalidate) {
		this.tovalidate = tovalidate;
	}

	public List<Pending_deps> getPending() {
		return pending;
	}

	public void setPending(List<Pending_deps> pending) {
		this.pending = pending;
	}

	public HashMap<String, String> getPID() {
		return PID;
	}

	public void setPID(HashMap<String, String> pID) {
		PID = pID;
	}

	public static int getInitial_Balance() {
		return Initial_Balance;
	}

	public static void setInitial_Balance(int initial_Balance) {
		Initial_Balance = initial_Balance;
	}

	public String getCurrent_process() {
		return current_process;
	}

	public void setCurrent_process(String current_process) {
		this.current_process = current_process;
	}

	public HashMap<String, String> getSignature() {
		return signature;
	}

	public void setSignature(HashMap<String, String> signature) {
		this.signature = signature;
	}

	public float read(String Process) {

        //Read balance of account a based on the histories of transfers and dependencies

        //String Process_id = this.PID.get(Process); //get source_id in order to use it to fetch the historic of the sender


        //List<Transactions> process_hist = this.hist.get(Process_id); //get the Process histories from hist
        //List<Deps> process_deps = returnDeps();//get the Process dependencies from deps

        //Map<String, Map<String, Object>> h = List_manup.union_dict(process_hist, process_deps);  //operate (hist[a] ∪ deps)

        return balance("", null); //return balance of current process, with no argument passed
        //executing the balance method to get balance
    }


    public void reliable_deliver(String delivered_from, HashMap<String, Object> message) throws Exception {
        //Executed when p delivers message m from process q

        //let m be [(q,d,y,s),h] with : q--> delivered_from, d --> receiver, y--> amount, s--> rec number and h is (hist[q] ∪ deps)
    	((Pending) message.get("transfer")).printPending();
    	
        // ---------------------------------- Getting transfer DATA from message ---------------------------------------
    	String sender = null; String receiver = null; float amount = -1; int seq = -1;
    	
//    	List<Transactions> process_dep = null;
//    	for(Deps d:this.getDeps()) {
//    		process_dep.add(d.getTransactions());
    		
        // Map<String, Object> get_message_parametersList = (Map<String, Object>) message.get(0); Make list to fetch transaction data from the message
    	Beans.Pending p = (Beans.Pending) message.get("transfer");
        sender = p.getSource().getIdentifier();//fetching sender
        receiver = p.getDestination().getIdentifier();//fetching receiver
        amount = p.getAsset();//fetching amount
        seq = p.getSeq();//fetching seq number
        // Add issuer a.k.a delivered from
        message.put("deliverd_from", delivered_from);
        // --------------------------------- Check well formedness of the received message -----------------------------
        //System.out.println(sender != null && receiver != null && amount !=-1  && seq != -1);
        if (sender != null && receiver != null && amount !=-1  && seq != -1) { //check the well_formedness of the message
        	System.out.println(sender);
        	//System.out.println(((Middleware.Sequence)this.getSequence()).get(sender));
        System.out.println((((Middleware.Sequence)this.getSequence()).get(sender) > p.getSequence()) && (message.get("Signature") == null) && (message.get("List_VSignatures")==null));
            //------------------------------------------------ Source Broadcast ----------------------------------------
            //Here i have a confusing of how to check that the message has been witnessed, so I relied on the sequence number
            // in the first message i attributed a sequence number to it, and augmented the sequence number of the sender by 1
            // I checked if the present process who is witnessing is the source of the transfer, then he must sign the message and send it to the issuer
            if (((Middleware.Sequence)this.getSequence()).get(sender) > p.getSequence() && message.get("Signature") == null && message.get("List_VSignatures")==null) {//checking the uniqueness of the
                // message seen first time Method is responsible for signing the message and replying
                // if the one signing the message is the source we augment the sequence number by one to avoid repeating the same process more than once
            	System.out.println("Salam ana hna 1");
                message_seen_first_time(message);}
//        	} else {//if (((Middleware.Sequence)this.sequence).get(sender) <= p.getSequence() && (Sign.signature == null)) {
//            //Firstly we check if the process is not the source of the transfer, we check if the signature has been accomplished or not
//            // the process has to sign the message and reply
//        		System.out.println("Salam ana hna 2");
//        		message_seen_first_time(message);}
        	}else if((String) message.get("Signature") != null && message.get("List_VSignatures")==null) {
        		reply_signed_message(message);
        	}else if(message.get("Signature") != null && message.get("List_VSignatures")!=null) {
        		
	            //get source_id in order to use it to fetch the historic of the sender
	            String deliverd_from_id = this.getPID().get(delivered_from); 
	
	
	            ArrayList<Object> set_to_validateList = new ArrayList<>();// Make list to create {(q,m)}
	
	            //get_message_parametersList.put("deliverd_from", deliverd_from_id); // we add the issuer to the message
	
	            //set_to_validateList.add(0, deliverd_from_id); // inserting q
	
	            //set_to_validateList.add(1, p); // inserting m
	            //set_to_validateList.add(2, deps);

	            if (p.getSeq() ==((Middleware.Rec)this.getRec()).get(deliverd_from_id) + 1) {  // if s = rec[q] + 1 then
	
	            	((Middleware.Rec)this.getRec()).put(deliverd_from_id, ((Middleware.Rec)this.getRec()).get(deliverd_from_id) + 1); // rec[q] := rec[q] + 1
	                // add to tovalidate
	            	List<Transactions> list_deps = new ArrayList<>();
	            	for(Beans.Deps l: (List<Beans.Deps>) message.get("deps")) {
	            		list_deps.add(l.getTransactions());
	            	}
	                ((Middleware.To_validate) this.tovalidate).add(new Beans.To_validate(this.daog.getPdao().getbyidentifier(deliverd_from_id), p.getSource(), p.getDestination(), p.getAsset(), p.getSeq()), list_deps);
	                
	//                if (toValidate.contains(set_to_validateList)) {//testing if {(q,m)} is already in tovalidate to avoid duplication
	//                    ;
	//                } else {
	//                    toValidate.add(deliverd_from_id);
	//                    toValidate.add(set_to_validateList);
	//
	//                }
	                valid(delivered_from,  message);
	            }
            // Performing toValidate := toValidate ∪ {(q,m)}


            // -------------------------------------------------- Executing to validated -------------------------------

            // Same as before for testing purposes i executed valid function here.
            // it shouldn't be here

	            


    }}


    public boolean valid(String delivered_from, HashMap<String, Object> message) { //Executed when a transfer delivered from q becomes valid
        Beans.Pending p = (Beans.Pending) message.get("transfer");
    	String sender_account = p.getSource().getIdentifier();
        String receiver_account = p.getDestination().getIdentifier();
        //String sender_id = this.getPID().get(sender_account);
        //String receiver_id = this.getPID().get(receiver_account);
        //String deliverd_from_id = this.getPID().get(delivered_from);
        
        //ArrayList<Object> to_check = new ArrayList<>();//created to make (q, [t,h])
        //ArrayList<Object> hist_and_TransList = new ArrayList<>();//created to make [t,h]
        //hist_and_TransList.add(0, t);//Inserting t into [t,h]
        //hist_and_TransList.add(1, h);//Inserting h into [t,h]
        //to_check.add(0, deliverd_from_id);//Inserting q into (q, [t,h])
        //to_check.add(1, hist_and_TransList);//Inserting [t,h] into (q, [t,h])
        
        //---------------------------- Performing (q, [t,h]) ∈ toValidate ∧ Valid(q,t,h) -------------------------------
        Beans.To_validate tovalidate = new Beans.To_validate(this.daog.getPdao().getbyidentifier(delivered_from), p.getSource(), p.getDestination(), p.getAsset(), p.getSeq());
        List<HashMap<Beans.To_validate, List<Transactions>>> l_tovalidate_deps =  ((Middleware.To_validate) this.tovalidate).getAll();
        
        List<Deps> deps = (List<Deps>) message.get("deps"); // get deps from transfer
        if (List_manup.contains_tovalidate(tovalidate, deps, l_tovalidate_deps) && is_valid(delivered_from, p, deps)
                || !(List_manup.contains_tovalidate(tovalidate, deps, l_tovalidate_deps) && !(is_valid(delivered_from, p, deps)))) {  //


            //------------------------ Update the history for the incoming & the outgoing Accounts ---------------------

        	List<Transactions> sender_process_hist = this.getHist().get(sender_account); // getting histories of the  source
        	List<Transactions> receiver_process_hist = this.getHist().get(receiver_account);// getting histories of the  receiver
        	List<Transactions> receiver_process_deps = null;// getting dependencies of the  receiver
        	List<To_validate_deps> tovalidate_deps = this.getDaog().getTovalidate_dep_dao().getbytovalidate(tovalidate);
        	for(To_validate_deps e:tovalidate_deps) {
        		receiver_process_deps.add(e.getTransaction());
        	} 


            //UUID uniqueKey = UUID.randomUUID(); // Creating a unique transaction identifier (unique key)

            //Map<String, Map<String, Object>> sender_process_newerh = new HashMap<String, Map<String, Object>>();
            //Map<String, Map<String, Object>> receiver_process_newerh = new HashMap<String, Map<String, Object>>();
            //Map<String, Map<String, Object>> receiver_process_newerd = new HashMap<String, Map<String, Object>>();

            // Here i wanted to reconstruct the data to make sure it's well-formed to be inserted to the histor

            //sender_process_newerh.put(uniqueKey.toString(), reconstract_data); // making a new frame of history for the sender
            //receiver_process_newerh.put(uniqueKey.toString(), reconstract_data); // making a new frame of history for the receiver
            List<Beans.Transactions> list_transactions = null;
            list_transactions.add(new Transactions(this.daog.getPdao().getbyidentifier(sender_account), this.daog.getPdao().getbyidentifier(sender_account), p.getAsset(), p.getSeq()));
            /* Add this constructed transaction to history */
            ((Middleware.Hist) this.getHist()).put(sender_account, list_transactions);
            ((Middleware.Hist) this.getHist()).put(receiver_account, list_transactions);
            ((Middleware.Seq) this.getSeq()).put(delivered_from, p.getSeq()); // updating the seq number for the process

            //----------------------------------- Updating the dependencies for the receiver ---------------------------

            if (receiver_account == current_process) { //checking here if the current process is the receiver
            	
            	((Middleware.Deps) this.getDeps()).add(new Deps(new Transactions(this.getDaog().getPdao().getbyidentifier(sender_account), this.getDaog().getPdao().getbyidentifier(sender_account), p.getAsset(), p.getSeq())));

                //List_manup.union_dict(receiver_process_deps, receiver_process_newerd);
            }

            if (sender_account == current_process) {

                return true;
            }
        }

        return true;


    }


    public boolean is_valid(String delivered_from, Beans.Pending p, List<Deps> deps) {
        String delivered_from_id = this.PID.get(delivered_from);
        String sender_account = p.getSource().getIdentifier();
        List<Transactions> process_dep = null;
    	//extract transctions type objects from deps
        for(Deps d:deps) {
    		process_dep.add(d.getTransactions());}
    	
    	boolean result = sender_account == delivered_from //checking if the process who delivered the transfer is the source
                && p.getSeq() == ((Middleware.Seq)this.getSeq()).get(delivered_from_id) + 1 // checking if the seq number has been updated
                && balance(delivered_from, ((Middleware.Hist) this.getHist()).get(delivered_from_id)) >= p.getAsset() // checking if the balance is superior to the amount
                && List_manup.contains(process_dep, ((Middleware.Hist)this.getHist()).get(delivered_from_id));// checking if the histories have been updated);
                
        System.out.println(result);
        return result;
    }


    public float balance(String Process, List<Transactions> h) {
        String Process_id = "";
        //--------------------------------- Initializing Variables -----------------------------------------------------
        float sum_income = 0;
        float sum_outgo = 0;
        float new_balance = 0;
        // ------------------------------- Calculating the Balance -----------------------------------------------------
        
        
        if (h == null || h.isEmpty()) { // treating the case of having (hist[a] ∪ deps) is empty because it is giving the code bad behaviors
//        	Process_id = current_process; //use getcurrentprocess() in the future 
//        	List<Transactions> process_hist = this.hist.get(Process_id); //get the sender histories from hist
//            List<Transactions> process_dep = new ArrayList<Transactions>();
//            if(this.deps instanceof ArrayList<Deps>) { // ensure flexibility 
//            	for(int i =0;i<this.deps.size();i++) {
//            		process_dep.add(this.deps.get(i).getTransactions());
//            	} 			//get the sender dependencies from deps
//            }else if(this.deps instanceof Middleware.Deps){
//            	for(Deps d:((Middleware.Deps) this.deps).get()) {
//            		process_dep.add(d.getTransactions());
//            	}
//            }
//            h = List_manup.merge(process_hist, process_dep); // merge transactions from hist and deps
//            h = List_manup.removeDuplicates(h);
        	return Initial_Balance;
         }
        	
        for(Transactions t:h) {
        	Process_id = this.PID.get(Process);
        	if(t.getSource().getIdentifier() == Process_id) {
        		sum_outgo = sum_outgo + t.getAsset();
        	}else if(t.getDestination().getIdentifier() == Process_id) {
        		sum_income = sum_income + t.getAsset();
        		}
        }
        return Initial_Balance + (sum_income - sum_outgo);
    }
    
    public void reply_signed_message(HashMap<String, Object> message) throws Exception  {
        
    	
    	Beans.Pending transfer = (Beans.Pending) message.get("transfer");//Get the Transfer information
    	String publi_key_of_process = this.getDaog().getPdao().getbyidentifier((String) message.get("deliverd_from")).getPublic_key(); // retreive public key
        // ----------------------------- Extracting signing Infos for verification -------------------------------------

    	byte[] signature = (byte[]) message.get("signature");// extract the signature of the process delivered_from for verification
        Signature sign = (Signature) message.get("sign");// extract the sign of the process delivered_from for verification

        //--------------------------- Removing signing infos to reconstruct the signed message -------------------------
        // Reconstruct to initial message
        message.remove("signature");
        message.remove("List_VSignatures");
        message.remove("sign");
        //message.remove("delivered_from");
        //TODO
        //Beans.Pending initial_transfer = new Pending(transfer.getSource(), transfer.getDestination(), transfer.getSequence(), transfer.getAsset(), transfer.getSeq());
        //Beans.Pending) message.get("transfer")).getSequence()
        //message.put("transfer", initial_transfer);
        //-------------------------------------- Verifying the signature -----------------------------------------------

        boolean verify = Sign.verify_sign(signature, message, sign, publi_key_of_process);
        
        // ----------------------------- Constructing the confirmed signature list -------------------------------------
        // get all confirmed signatures from db
        List<Beans.Signature> List_of_confirmed_signatures = this.daog.getSignaturedao().getAll();
        if (verify){//If message is verified
        	
        	Iterator it = ((Middleware.Pending) this.pending).getAll().entrySet().iterator();
        	
        	while (it.hasNext()) {
    	        Map.Entry pair = (Map.Entry)it.next();
    	        
    	        if (((Beans.Pending) pair.getKey()).getSequence() == transfer.getSequence()) {
                    if (!(List_of_confirmed_signatures.contains(signature))) { // checking if the list of confirmed signatures
                        // doesn't contain the present signature to avoid redundancy
                    	((Middleware.Signature) this.getSignature()).setPending(transfer);	// set pending to the actual transfer
                        ((Middleware.Signature) this.getSignature()).put((String)message.get("delivered_from"), signature.toString()); // save signature int db
                    	//this.getDaog().getSignaturedao().save(sign);//Adding the signature to the list
                        

                    }
    	        
    	        
    	        
    	        it.remove(); // avoids a ConcurrentModificationException
        	    
    	        }
        	
            // checking if the message with the exact sequence number is in the Pending messages
                
           }
        	// update list of confirmed signatures;
        	List_of_confirmed_signatures = this.daog.getSignaturedao().getAll();
        	message.put("signature", signature);
            message.put("List_VSignatures", List_of_confirmed_signatures);

            // ------------------------------------- Initiating the broadcast ------------------------------------------

            // If the message was witnessed and signed by 2/3 of the correct processes
            if (List_of_confirmed_signatures.size()>=2*this.getPID().size()/3) {
                ((Middleware.Pending) this.getPending()).removepending(transfer); //delete pending and pending_dep from db  
                //remove(Parameter_transaction);// Remove the message from pending messages

                
                //send data with quorum list
                reliable_broadcast(transfer.getSource().getIdentifier(), message);
            }
        }
    }

    public void message_seen_first_time(HashMap<String, Object> message) throws Exception {
        //check if the message was seen before by the process
        // the message form is ([(a,b, x,seq[p] + 1), deps, sequence])
    	System.out.println("message_seen_first_time");
        Beans.Pending transfer = (Beans.Pending) message.get("transfer");
        //Parameter_transaction = (Map<String, Object>) message.get(0);//Get the Transfer information
        String new_current_process = this.getPID().get("AccountIV"); // Warning this is just for test + get current_process is required
        //------------------------------------------------- Signing the message ----------------------------------------
        message.remove("Signature"); message.remove("List_VSignatures");
        String public_key = this.getDaog().getCurrentprocess_dao().getbyidentifier(new_current_process).getPublic_key();
        String private_key = this.getDaog().getCurrentprocess_dao().getbyidentifier(new_current_process).getPrivate_key();
        
        Signature message_signature = Sign.sign(private_key, message);//signing the message
        //Signature message_signature = (Signature) get_sig_data.get(1);//getting the signature

        //---------------------------------- Inserting signature info to message ---------------------------------------
        message.put("signature", message_signature.sign());// Adding the signature to the message
        message.put("List_VSignatures", null);
        //Parameter_transaction.put("signature", message_signature.sign());
        message.put("sign",message_signature);// Adding sign to the message ( to verify
        ((Middleware.Signature) this.getSignature()).setPending(transfer);	// set pending to the actual transfer
        ((Middleware.Signature) this.getSignature()).put(new_current_process, message_signature.sign().toString()); // save signature int db
        //--------------------------------------------- Sending Reply --------------------------------------------------
        System.out.println(message);
        //reliable_reply(message); //Sending the reply via the reliable delivery
        reply_signed_message(message);
    }
    
    
    
    public void reliable_broadcast(String Sender_Account, HashMap<String,Object> message){
        System.out.println(Sender_Account);
    }

    public void reliable_reply(HashMap<String, Object> message) {
    	System.out.println("Reliable_reply");
    }
    
    
    public List<Processes> getprocesses(){
		return null;
	}
    public String current_process() throws Exception{
		//Implementation for the current process identifier
    	return null;
    }
    
	public List<Deps> returnDeps() {
        List<Deps> list = new ArrayList<Deps>() ;
    	if(this.deps instanceof List<Deps>){
    		for(int i =0;i<this.deps.size();i++) {
            	list.add(this.deps.get(i));
    		}
    	}else if(this.deps instanceof Middleware.Deps) {
        	list = ((Middleware.Deps) this.getDeps()).get();
        	}
		return list;
    	}
}