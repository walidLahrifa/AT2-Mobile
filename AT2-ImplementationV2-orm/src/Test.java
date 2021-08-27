
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import Beans.*;
import Beans.Deps;
import Beans.Hist;
import Beans.Pending;
import Beans.Rec;
import Beans.Sequence;
import Beans.Signature;
import Beans.To_validate;
import DAO.*;
import Middleware.*;
import Core.AT2;
import java.security.KeyFactory;

public class Test {
	public static DAOGenerator daog = new DAOGenerator();
	public static ProcessesDAO pdao = new ProcessesDAO();
	public static RecDAO rdao = new RecDAO();
	public static TransactionDAO dao = new TransactionDAO();
	public static PendingDAO penddao = new PendingDAO();
	public static To_validateDAO tovaldao = new To_validateDAO();
	
	
	public static void TestMiddlewareRec() {
		Middleware.Rec mrec = new Middleware.Rec();
		mrec.put("Ashraf", 1);
		mrec.put("Account2", 0);
		mrec.put("Account3", 0);
		
		System.out.println(mrec.get("Ashraf"));
		
		Processes recprocess = new Processes("Ashraf", "2lkhadsaddsaddjkabjk", true);
        pdao.save(recprocess);
        
        //Transactions transaction0 = new Transactions(process, process1, (float) 100.00, 1);
        Rec rec = new Rec(recprocess, 45454);
        rdao.save(rec);
        List<Rec> rs= rdao.getAll();
		for (int i = 0; i < rs.size(); i++) { 		      
        	rs.get(i).printRec();}
		
		//mrec.remove("Ashraf");
		//mrec.remove("fafsfasdfsa");
	}
	public static void TestTransactions() {
		
        Processes process0 = new Processes("Ashraf", "202456205454ewq1", true);
        pdao.save(process0);
        Processes process1 = new Processes("Nizar", "2024562021", true); 
        pdao.save(process1);
        //Transactions transaction0 = new Transactions(process, process1, (float) 100.00, 1);
        Transactions transaction1 = new Transactions(process0, process1, (float) 10440.00, 1);
        // test add a new transaction
        dao.save(transaction1);
        transaction1=dao.getAll().get(0);
        transaction1.printTransaction();
        String[] param2={"Moha", "Mohamed", "20000", "0"};
        //dao.update(transaction1, param2);
        //transaction1=dao.getAll().get(0);
        //transaction1.printTransaction();
        // test Optional getbyid
        if(dao.getbyid(1).isPresent()) {
        	(dao.getbyid(1).get()).printTransaction();}}
	
	public static void TestMiddlewareHist() {
		Middleware.Hist mhist = new Middleware.Hist();
		Processes process0 = new Processes("Ashraf", "202456205454ewq1", true);
        pdao.save(process0);
        Processes process1 = new Processes("Nizar", "2024562021", true); 
        pdao.save(process1);
        //Transactions transaction0 = new Transactions(process, process1, (float) 100.00, 1);
        List<Transactions> transactions = null;
        transactions.add(new Transactions(process0, process1, (float) 104510.00, 1));
		mhist.put("Ashraf", transactions);
		List<Transactions> l = mhist.get("Ashraf");
		for (int i = 0; i < l.size(); i++) { 		      
        	l.get(i).printTransaction();}
	}
	
	
	public static void TestMiddlewareSignature() {
		Processes process0 = new Processes("Fatima", "20245d6205454ewq1", true);
		pdao.save(process0);
		Pending p = new Pending(1, process0, process0, 0, (float) 5000, 0);
		penddao.save(p);
		Middleware.Signature msig = new Middleware.Signature(p);
		System.out.println(msig.put("Fatima", "signature:dsad54454dsdsda"));
		
		System.out.println("Sig = " + msig.get("Fatima"));
		Processes process1 = new Processes("Mohamed", "20245d6205454ewq1", true);
		pdao.save(process1);
		Pending pe = new Pending(1, process1, process1, 1, (float) 3000, 1);
		penddao.save(pe);
		msig.remove();
		List<Signature> l = msig.getSignaturesByPending();
		for(Signature e:l ){
			e.printSignature();
		};
		
	}
	
	
	public static void TestMiddlewarePending() {
		
		Middleware.Pending mpend = new Middleware.Pending();
		Processes process0 = new Processes("Moha", "20245d6205454ewq1", true);
		pdao.save(process0);
		Pending p = new Pending(1, process0, process0, 0, (float) 5000, 0);
		penddao.save(p);
        Transactions transaction1 = new Transactions(5, process0, process0, (float) 445.00, 1);
		ArrayList<Deps> l = new ArrayList<Deps>();
		l.add(new Deps(transaction1));
		mpend.add(p, l);
		
		HashMap<Beans.Pending, List<Transactions>> map = mpend.getAll();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
		        HashMap.Entry pair = (HashMap.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		}
	
	
	public static void TestMiddlewareDeps() {
			
			Middleware.Deps mdeps = new Middleware.Deps();
			Processes process0 = new Processes("Aisha", "dsdasdsafsdfaswq", true);
			pdao.save(process0);
	        Transactions transaction1 = new Transactions(8, process0, process0, (float) 4485.00, 1);
	        mdeps.add(0, transaction1);
			
	        List<Deps> list =	mdeps.get();
			for(Deps e:list ){
				e.printDeps();
			}
			
			
		}
	
	public static void TestMiddlewareTovalidate() {
		
		Middleware.To_validate mtovalidate = new Middleware.To_validate();
		Processes process0 = new Processes("Samir", "20245d6205454ewq1", true);
		pdao.save(process0);
		Processes process1 = new Processes("Abdelkader", "20245d6205454ewq1", true);
		pdao.save(process1);
		Pending p = new Pending(1, process0, process0, 0, (float) 700, 0);
		penddao.save(p);
		To_validate tv = new To_validate(3, process0, process0, process1, (float) 700.00, 1);
		tovaldao.save(tv);
        Transactions transaction1 = new Transactions(9, process0, process1, (float) 700.00, 1);
		ArrayList<Transactions> l = new ArrayList<Transactions>();
		l.add(transaction1);
		mtovalidate.add(tv, l);
		
		List<HashMap<To_validate, List<Transactions>>> list =	mtovalidate.getAll();
		
		for(HashMap<To_validate, List<Transactions>> e:list ){
			Iterator it = e.entrySet().iterator();
		    while (it.hasNext()) {
		        HashMap.Entry pair = (HashMap.Entry)it.next();
		        System.out.println(((To_validate) pair.getKey()).ToStringTo_validate() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		};
		
		
	}
	
	public static void TestDAO() {
		
		/****************process*****************/
		
		Processes process = new Processes("Ali", "20202021", false);
		// add
		pdao.save(process);
		process = pdao.getAll().get(0);
		process.printProcess();
		// update
		String[] param= {"19981997", "false"};
		pdao.update(process, param);
		process = pdao.getAll().get(0);
		process.printProcess();
		// update valid
		pdao.updatevalid(process);
		process = pdao.getAll().get(0);
		process.printProcess();
		// delete
		//pdao.delete(process);
		//process = pdao.getAll().get(0);
		//process.printProcess();
		
		/****************current process*****************/
		// update current process

		Current_processDAO cdao = new Current_processDAO();
        Current_process current_process = new Current_process("Ali", "19981997", "1555154564");
        cdao.save(current_process);
        current_process = cdao.getAll().get(0);
        current_process.printCurrentProcess();
        // update current process

        String[] param1 = {"18941998", "54435343634454"};
        cdao.update(current_process, param1);
        current_process = cdao.getAll().get(0);
        current_process.printCurrentProcess();
        // delete
        //cdao.delete(current_process);
		
        
        /****************Transactions*****************/
        TransactionDAO dao = new TransactionDAO();
        Processes process0 = new Processes("Moha", "202456205454ewq1", true);
        pdao.save(process0);
        Processes process1 = new Processes("Mohamed", "2024562021", true); 
        pdao.save(process1);
        //Transactions transaction0 = new Transactions(process, process1, (float) 100.00, 1);
        Transactions transaction1 = new Transactions(process0, process1, (float) 10440.00, 1);
        // test add a new transaction
        dao.save(transaction1);
        transaction1=dao.getAll().get(0);
        transaction1.printTransaction();
        String[] param2={"Moha", "Mohamed", "20000", "0"};
        dao.update(transaction1, param2);
        transaction1=dao.getAll().get(0);
        transaction1.printTransaction();
        //test Optional getbyid
        if(dao.getbyid(1).isPresent()) {
        	(dao.getbyid(1).get()).printTransaction();
        	
        };
        
        // get all transactions
        List<Transactions> ltran = new ArrayList<>();
        ltran = dao.getallbysource("Moha");
        System.out.println("//////////////get all transactions  byprocess////");
        for (int i = 0; i < ltran.size(); i++) { 		      
        	ltran.get(i).printTransaction();}
        /****************Rec*****************/
        Processes recprocess = new Processes("Ashraf", "2lkhadsadsaddjkabjk", true);
        pdao.save(recprocess);
        
        //Transactions transaction0 = new Transactions(process, process1, (float) 100.00, 1);
        Beans.Rec rec = new Beans.Rec(recprocess, 45454);
        // test add a new transaction
        rdao.save(rec);
        rec=rdao.getAll().get(0);
        rec.printRec();
        //update
        rdao.update(rec, new String[] {"Ashraf", "5"});
        rec=rdao.getAll().get(0);
        rec.printRec();
        // update just rec_number
        rdao.updatebyrec_number("Ashraf", 75);
        rec=rdao.getAll().get(0);
        rec.printRec();
        
        
        /****************Seq*****************/
        SeqDAO sdao = new SeqDAO();
        Processes seqprocess = new Processes("Nizar", "2lkhadsads7857855454k", true);
        pdao.save(seqprocess);
        
        //Transactions transaction0 = new Transactions(process, process1, (float) 100.00, 1);
        Beans.Seq seq = new Beans.Seq(seqprocess, 45);
        // test add a new transaction
        sdao.save(seq);
        seq=sdao.getAll().get(0);
        seq.printSeq();
        //update
        sdao.update(seq, new String[] {"Nizar", "522"});
        seq=sdao.getAll().get(0);
        seq.printSeq();
        // update just rec_number
        sdao.updatebyseq_number("Nizar", 25);
        seq=sdao.getAll().get(0);
        seq.printSeq();
        
        /****************Sequence*****************/
        SequenceDAO sequencedao = new SequenceDAO();
        Processes sequenceprocess = new Processes("Aisha", "2lkhadfqarfqre4wk", true);
        pdao.save(sequenceprocess);
        
        Sequence sequence = new Sequence(sequenceprocess, 33);
        // test add a new transaction
        sequencedao.save(sequence);
        sequence=sequencedao.getAll().get(0);
        sequence.printSequence();
        //update
        sequencedao.update(sequence, new String[] {"Aisha", "52"});
        sequence=sequencedao.getAll().get(0);
        sequence.printSequence();
        // update just rec_number
        sequencedao.updatesequence("Aisha", 252);
        sequence=sequencedao.getAll().get(0);
        sequence.printSequence();
        // delete
        Processes sequenceprocess1 = new Processes("Salmaton", "2lkhadfqarfqre4dsadsadawk", true);
        Sequence s = new Sequence(sequenceprocess1, 20);
        sequencedao.delete(s);
        s=sequencedao.getAll().get(0);
        s.printSequence();
        
        
        /******************Pending*******************/
        
        PendingDAO pendao = new PendingDAO();
        Pending pending = new Pending(pdao.getAll().get(0), pdao.getAll().get(1), 54, (float) 16000 ,71);
        pendao.save(pending);
        pending = pendao.getAll().get(0);
        pending.printPending();
        
/******************Pending_deps*******************/
        
        Pending_depsDAO pendepdao = new Pending_depsDAO();
        //Pending_deps pending_dep = new Pending_deps(pending, t);
        Pending_deps pending_dep1 = new Pending_deps(pendao.getbyid(1).get(), dao.getbyid(1).get());
        pendepdao.save(pending_dep1);
        //pendepdao.update(pending_dep1, new String[] {"2", "2"});
        pending_dep1 = pendepdao.getAll().get(0);
        pending_dep1.printPending_deps();
        
        
        
/******************To_validate*******************/
        
        To_validateDAO tvdao = new To_validateDAO();
        To_validate to_validate = new To_validate(pdao.getAll().get(0), pdao.getAll().get(0), pdao.getAll().get(1), (float) 16000 ,71);
        tvdao.save(to_validate);
        to_validate = tvdao.getAll().get(0);
        to_validate.printTo_validate();
        
        
        
        
        
/******************To_validate_dep*******************/
        
        To_validate_depsDAO tvaldepdao = new To_validate_depsDAO();
        To_validate_deps to_validate_dep = new To_validate_deps(tvdao.getAll().get(0), dao.getAll().get(0));
        tvaldepdao.save(to_validate_dep);
        tvaldepdao.deletebyID(0);tvaldepdao.deletebyID(1);
        to_validate_dep = tvaldepdao.getAll().get(0);
        to_validate_dep.printTo_validate_deps();
        
        
        
/******************Signature*******************/
        
        SignatureDAO signaturedao = new SignatureDAO();
        Signature signature = new Signature(pendao.getAll().get(0), pdao.getAll().get(0), "5454dsa454545da4d5s4adsa");
        signaturedao.save(signature);
        signature = signaturedao.getAll().get(0);
        signature.printSignature();
        
        
        
/******************Hist*******************/
        
        HistDAO histdao = new HistDAO();
        Hist hist = new Hist(pdao.getAll().get(0), dao.getAll().get(0));
        histdao.save(hist);
        hist = histdao.getAll().get(0);
        hist.printHist();
        //get all hist by process
        System.out.println("//////////////get all history////////////////");
        List<Hist> lishist = new ArrayList<>();
        lishist = histdao.getAllbyprocess("Ali");
        for (int i = 0; i < lishist.size(); i++) { 		      
            lishist.get(i).printHist(); 		
        }   		
        
/******************Deps*******************/
        
        DepsDAO depdao = new DepsDAO();
        
        Deps dep = new Deps(dao.getAll().get(1));
        depdao.save(dep);
        dep = depdao.getAll().get(0);
        dep.printDeps();
        
        System.out.println("//////////////get all deps/////////////");
        List<Deps> lisdeps = new ArrayList<>();
        lisdeps = depdao.getAll();
        for (int i = 0; i < lisdeps.size(); i++) { 		      
        	lisdeps.get(i).printDeps();
        }
	}
	
	
	
	
	public static void TestORMIntegration() throws Exception {
		   AT2 at2 = new AT2();
		   List<String> keys = pubkey_generator();
		   Processes p1 = new Processes("0a21daz1daz65", "2lkhadfqarfqre4wk", true); // AccountI
		   pdao.save(p1);
		   Processes p2 = new Processes("1dza61dza6651", "fsadsa54qarfqre4wk", true); // AccountII
		   pdao.save(p2);
		   
		   Processes p3 = new Processes("3zg8rz2681z16", keys.get(0), true); // AccountIV
		   pdao.save(p3);
		   //daog.getSeqdao().create_seq("0a21daz1daz65"); daog.getSeqdao().create_seq("1dza61dza6651"); 
		   //daog.getSequencedao().create_sequence("0a21daz1daz65"); daog.getSequencedao().create_sequence("1dza61dza6651");
		   //daog.getRecdao().create_rec("0a21daz1daz65"); ; daog.getRecdao().create_rec("1dza61dza6651");
		   //daog.getSeqdao().create_seq("3zg8rz2681z16");daog.getSequencedao().create_sequence("3zg8rz2681z16");daog.getRecdao().create_rec("3zg8rz2681z16");
		   //daog.getSequencedao().getbyidentifier("0a21daz1daz65").printSequence();
		   //Middleware.Sequence something = new Middleware.Sequence();
		   //System.out.println(something.get("0a21daz1daz65"));
		  
	       daog.getCurrentprocess_dao().save(new Current_process("3zg8rz2681z16",keys.get(0), keys.get(1)));
	       System.out.println(daog.getCurrentprocess_dao().getAll().get(0).getPublic_key());
	       System.out.println(at2.hist);
		   at2.Transfer("AccountI", "AccountII", 7000);
		   
		   }
		public static List<String> pubkey_generator() throws Exception {
		       List<String> ListStringlist= new ArrayList<>();
		       KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
		       SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		       keyGen.initialize(1024, random);
		       KeyPair keyPair = keyGen.generateKeyPair();
		       PrivateKey privateKey = keyPair.getPrivate();
		       PublicKey publicKey = keyPair.getPublic();
		       // Base64 encoded string
		       // get encoded form (byte array)
		       // Base64 encoded string
		       byte[] publicKeyByte = publicKey.getEncoded();
		       byte[] privateKeyByte = privateKey.getEncoded();
		       String publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);
		       String privateKeyString = Base64.getEncoder().encodeToString(privateKeyByte);
		       System.out.println(publicKeyString);
		       ListStringlist.add(publicKeyString);
		       ListStringlist.add(privateKeyString);
		       return ListStringlist;
		}
		
	
	
	
	
	/**
	 * @param args the command line arguments
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/**************** middleware *****************/
		//TestDAO();
		//TestMiddlewareRec();
		//TestTransactions();
		//TestMiddlewareHist();
		//TestMiddlewareSignature();
		//TestMiddlewarePending();
		//TestMiddlewareDeps();
		//TestMiddlewareTovalidate();
		TestORMIntegration();
        
        
      
        
        
        
        
        
        
        
        
        
        
		
	}

}
