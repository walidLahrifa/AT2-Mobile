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

import Beans.Deps;
import Beans.Transactions;
public class DepsDAO implements DAO<Deps> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Deps> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        Deps dep=null;
        TransactionDAO tdao = new TransactionDAO();
        Transactions transaction=null;
        
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from deps where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if(tdao.getbyid(transaction_id).isPresent()) {
					// get value
					transaction = tdao.getbyid(transaction_id).get();
				}
				dep = new Deps(rs.getInt("id"), transaction);
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
        return Optional.of(dep);
    }

    /* Get All Deps from database*/
    @Override
    public List<Deps> getAll() {
    	Connection conn = null;
		Statement stmt = null;
        TransactionDAO tdao = new TransactionDAO();
        List<Deps> listDeps = new ArrayList<>();
        Transactions transaction=null;

        try {
			String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			String sql = "select * from deps;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
								
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if(tdao.getbyid(transaction_id).isPresent()) {
					// get value
					transaction = tdao.getbyid(transaction_id).get();
					Deps dep = new Deps(rs.getInt("id"), transaction);
					listDeps.add(dep);
				}else {
					continue;
				}
				
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
        return listDeps;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Deps dep) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into deps(transactions) values(?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, dep.getTransactions().getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Dep inserted\n");
	       
	        // Close the connection
	        conn.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Cannot insert a dep");}
		
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
    public void update(Deps dep, String[] params) {
        // TODO Auto-generated method stub

    }

    /* Delete a Transaction from a database*/
    @Override
    public void delete(Deps dep) {
        // TODO Auto-generated method stub
    	Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from deps where id = ?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, dep.getId());			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Dep deleted\n");
	       
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
    
    
    public int deleteAll() {
        // TODO Auto-generated method stub
    	Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		int a = -1;
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from deps;";
			myStmt = conn.prepareStatement(query);
			
			
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " All Deps are deleted\n");
	        a = 1;
	        // Close the connection
	        conn.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Cannot delete All Dependecies");}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
    				}
		return a;
		}

	@Override
	public Deps getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
