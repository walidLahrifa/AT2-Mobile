package DAO;


public class DAOGenerator {
	ProcessesDAO pdao;TransactionDAO tdao;DepsDAO dep_dao;HistDAO hdao; PendingDAO pending_dao; SignatureDAO sign_dao;
	Pending_depsDAO pending_dep_dao; To_validateDAO tovalidate_dao; To_validate_depsDAO tovalidate_dep_dao;Current_processDAO currentprocess_dao;
	RecDAO recdao ; SeqDAO seqdao; SequenceDAO sequencedao ; SignatureDAO signaturedao;
	

	public DAOGenerator() {
		
		this.pdao = new ProcessesDAO();
		this.tdao = new TransactionDAO();
		this.dep_dao = new DepsDAO();
		this.hdao = new HistDAO();
		this.pending_dao = new PendingDAO();
		this.sign_dao = new SignatureDAO();
		this.pending_dep_dao = new Pending_depsDAO();
		this.tovalidate_dao = new To_validateDAO();
		this.tovalidate_dep_dao = new To_validate_depsDAO();
		this.currentprocess_dao = new Current_processDAO();
		this.recdao = new RecDAO();
		this.seqdao = new SeqDAO();
		this.sequencedao= new SequenceDAO();
		this.signaturedao = new SignatureDAO();
	}
	
	
	public SignatureDAO getSignaturedao() {
		return signaturedao;
	}


	public void setSignaturedao(SignatureDAO signaturedao) {
		this.signaturedao = signaturedao;
	}


	public RecDAO getRecdao() {
		return recdao;
	}


	public void setRecdao(RecDAO recdao) {
		this.recdao = recdao;
	}


	public SeqDAO getSeqdao() {
		return seqdao;
	}


	public void setSeqdao(SeqDAO seqdao) {
		this.seqdao = seqdao;
	}


	public SequenceDAO getSequencedao() {
		return sequencedao;
	}


	public void setSequencedao(SequenceDAO sequencedao) {
		this.sequencedao = sequencedao;
	}


	public Current_processDAO getCurrentprocess_dao() {
		return this.currentprocess_dao;
	}


	public void setCurrentprocess_dao(Current_processDAO currentprocess_dao) {
		this.currentprocess_dao = currentprocess_dao;
	}


	public To_validate_depsDAO getTovalidate_dep_dao() {
		return this.tovalidate_dep_dao;
	}


	public void setTovalidate_dep_dao(To_validate_depsDAO tovalidate_dep_dao) {
		this.tovalidate_dep_dao = tovalidate_dep_dao;
	}


	public To_validateDAO getTovalidate_dao() {
		return this.tovalidate_dao;
	}


	public void setTovalidate_dao(To_validateDAO tovalidate_dao) {
		this.tovalidate_dao = tovalidate_dao;
	}


	public Pending_depsDAO getPending_dep_dao() {
		return this.pending_dep_dao;
	}

	public void setPending_dep_dao(Pending_depsDAO pending_dep_dao) {
		this.pending_dep_dao = pending_dep_dao;
	}

	public SignatureDAO getSign_dao() {
		return this.sign_dao;
	}

	public void setSign_dao(SignatureDAO sign_dao) {
		this.sign_dao = sign_dao;
	}

	public ProcessesDAO getPdao() {
		return this.pdao;
	}

	public void setPdao(ProcessesDAO pdao) {
		this.pdao = pdao;
	}

	public TransactionDAO getTdao() {
		return this.tdao;
	}

	public void setTdao(TransactionDAO tdao) {
		this.tdao = tdao;
	}

	public DepsDAO getDep_dao() {
		return this.dep_dao;
	}

	public void setDep_dao(DepsDAO dep_dao) {
		this.dep_dao = dep_dao;
	}

	public HistDAO getHdao() {
		return this.hdao;
	}

	public void setHdao(HistDAO hdao) {
		this.hdao = hdao;
	}

	public PendingDAO getPending_dao() {
		return this.pending_dao;
	}

	public void setPending_dao(PendingDAO pending_dao) {
		this.pending_dao = pending_dao;
	}
	
}
