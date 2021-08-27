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

import Beans.Current_process;

public class Current_processDAO implements DAO<Current_process> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Current_process> getbyid(int id) {
    	return null;
    }

    /* Get All Transactions from database*/
    @Override
    public List<Current_process> getAll() {
        Connection conn = null;
        Statement stmt = null;
        List<Current_process> listCurrent_processes = new ArrayList<>();

        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from current_process;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Current_process current_process = new Current_process(rs.getString("identifier"), rs.getString("public_key"),
                        rs.getString("private_key"));
                listCurrent_processes.add(current_process);
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
        return listCurrent_processes;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Current_process cp) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into current_process values(?,?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, cp.getIdentifier());
	        myStmt.setString(2, cp.getPublic_key());
	        myStmt.setString(3, cp.getPrivate_key());
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " record inserted to current_process");
	       
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
    public void update(Current_process cp, String[] param) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update current_process set public_key=?, private_key=? where identifier=?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, param[0]);
	        myStmt.setString(2, param[1]);
	        myStmt.setString(3, cp.getIdentifier());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " current_process updateted");
	       
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
    public void delete(Current_process cp) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from current_process where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, cp.getIdentifier());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Current process deleted");
	       
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
	public Current_process getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		Connection conn = null;
        Statement stmt = null;
        Current_process current_process = null;

        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from current_process where identifier = '" + identifier + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
            	 current_process = new Current_process(rs.getString("identifier"), rs.getString("public_key"),
                        rs.getString("private_key"));
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
        return current_process;
	}
	
	
	public Current_process getcurrent_process() {
		Current_process current_process = null;
		List<Current_process> l = getAll();
		if(l.size()>0) {
			current_process = l.get(0);
		}
		return current_process;
	}
}

