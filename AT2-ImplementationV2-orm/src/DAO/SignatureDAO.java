package DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Beans.Pending;
import Beans.Signature;

public class SignatureDAO implements DAO<Signature> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Signature> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        Signature signature = null;
        ProcessesDAO pdao = new ProcessesDAO();
        PendingDAO pendao = new PendingDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from signature where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				int pending_id = rs.getInt("pending");
				String proc = rs.getString("identifier");
				if(pendao.getbyid(pending_id).isPresent()) {
				signature = new Signature(rs.getInt("id"), pendao.getbyid(pending_id).get(),
						pdao.getbyidentifier(proc), rs.getString("sig"));
			}}
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return Optional.of(signature);
    }

    /* Get All Transactions from database*/
    @Override
    public List<Signature> getAll() {
        Connection conn = null;
        Statement stmt = null;
        List<Signature> listSignatures = new ArrayList<>();
        Signature signature = null;
        ProcessesDAO pdao = new ProcessesDAO();
        PendingDAO pendao = new PendingDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from signature;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				int pending_id = rs.getInt("pending");
				String proc = rs.getString("identifier");
				if(pendao.getbyid(pending_id).isPresent()) {
					signature = new Signature(rs.getInt("id"), pendao.getbyid(pending_id).get(),
							pdao.getbyidentifier(proc), rs.getString("sig"));
				listSignatures.add(signature);
			}}
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return listSignatures;
    }
    
    public List<Signature> getbypending(Pending p) {
    	Connection conn = null;
        Statement stmt = null;
        Signature signature = null;
        ProcessesDAO pdao = new ProcessesDAO();
        PendingDAO pendao = new PendingDAO();
        
        List<Signature> listSignatures = new ArrayList<>();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from signature where pending = " + p.getId() + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				int pending_id = rs.getInt("pending");
				String proc = rs.getString("identifier");
				if(pendao.getbyid(pending_id).isPresent()) {
					signature = new Signature(rs.getInt("id"), pendao.getbyid(pending_id).get(),
							pdao.getbyidentifier(proc), rs.getString("sig"));
				listSignatures.add(signature);
			}}
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return listSignatures;
    }
    
    
    public List<Signature> getbypending_and_identifier(Pending p, String identifier) {
    	Connection conn = null;
        Statement stmt = null;
        Signature signature = null;
        ProcessesDAO pdao = new ProcessesDAO();
        PendingDAO pendao = new PendingDAO();
        List<Signature> listSignatures = new ArrayList<>();

        if(pdao.getbyidentifier(identifier) != null) {
	        try {
	            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
	            conn = DriverManager.getConnection(url);
	            stmt = conn.createStatement();
	
	            String sql = "select * from signature where pending = " + p.getId() + " and identifier = '"+identifier+"';";
	            ResultSet rs = stmt.executeQuery(sql);
	
	            while (rs.next()) {
					int pending_id = rs.getInt("pending");
					String proc = rs.getString("identifier");
					if(pendao.getbyid(pending_id).isPresent()) {
						signature = new Signature(rs.getInt("id"), pendao.getbyid(pending_id).get(),
								pdao.getbyidentifier(proc), rs.getString("sig"));
					listSignatures.add(signature);
				}}
	            rs.close();
	
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	        	try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                System.out.println(ex.getMessage());
	            }

	        }}
        return listSignatures;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Signature s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into signature(pending, identifier, sig) values(?,?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, s.getPending().getId());
	        myStmt.setString(2, s.getProcess().getIdentifier());
	        myStmt.setString(3, s.getSig());
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Signature inserted\n");
	       
	        // Close the connection
	        conn.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());}
		
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
    				}
    	    
    }

    /* Update a Transaction into a database*/
    @Override
    public void update(Signature s, String[] params) {
        // TODO Auto-generated method stub

    }

    /* Delete a Transaction from a database*/
    @Override
    public void delete(Signature s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from signature where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, s.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Signature deleted\n");
	       
	        // Close the connection
	        conn.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());}
		
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
    				}
    	    
    }

	@Override
	public Signature getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
