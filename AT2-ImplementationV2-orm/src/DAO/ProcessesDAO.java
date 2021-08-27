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

import Beans.Processes;

public class ProcessesDAO implements DAO<Processes> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Processes> getbyid(int id) {
        return null;
    }

    /* Get All Transactions from database*/
    @Override
    public List<Processes> getAll() {
        Connection conn = null;
        Statement stmt = null;
        List<Processes> listProcesses = new ArrayList<>();

        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from processes;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Processes process = new Processes(rs.getString("identifier"), rs.getString("public_key"),
                        rs.getBoolean("valid"));
                listProcesses.add(process);
            }
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
        return listProcesses;
    }
    
    /* Get a Process by identifier from database*/
    @Override
	public Processes getbyidentifier(String identifier) {
    	
    	Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	Processes process=null;
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from processes where identifier =?"); 
	    	                                   
	    	pstmt.setString(1,identifier);       
	
	    	rs = pstmt.executeQuery();        
	    	while (rs.next()) {               
	    		process = new Processes(rs.getString("identifier"), rs.getString("public_key"),
	                    rs.getBoolean("valid"));  
	    	}
	    	rs.close();                      
	    	pstmt.close();

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
        return process;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Processes p) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into processes values(?,?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, p.getIdentifier());
	        myStmt.setString(2, p.getPublic_key());
	        myStmt.setBoolean(3, p.getValid());
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " record inserted to process");
	       
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
    public void update(Processes p, String[] params) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update processes set public_key=?, valid=? where identifier=?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, params[0]);
	        myStmt.setBoolean(2, Boolean.getBoolean(params[1]));
	        myStmt.setString(3, p.getIdentifier());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " process updateted");
	       
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
    
    public void updatevalid(Processes p) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update processes set valid=? where identifier=?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setBoolean(1, true);
	        myStmt.setString(2, p.getIdentifier());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " valid status process updated");
	       
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

    /* Delete a Transaction from a database*/
    @Override
    public void delete(Processes p) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from processes where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, p.getIdentifier());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " process deleted");
	       
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

}
