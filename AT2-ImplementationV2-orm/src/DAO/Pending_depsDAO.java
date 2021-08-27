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
import Beans.Pending_deps;
import Beans.Transactions;
public class Pending_depsDAO implements DAO<Pending_deps> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Pending_deps> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        Pending_deps pending_deps=null;
        PendingDAO pdao = new PendingDAO();
        TransactionDAO tdao = new TransactionDAO();
        Pending pending=null;Transactions transaction=null;
        
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from pending_deps where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				int pending_id = rs.getInt("pending");
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if(pdao.getbyid(pending_id).isPresent() && tdao.getbyid(transaction_id).isPresent()) {
					// get value
					pending = pdao.getbyid(pending_id).get();
					transaction = tdao.getbyid(transaction_id).get();
				}
				pending_deps = new Pending_deps(rs.getInt("id"), pending, transaction);
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
        return Optional.of(pending_deps);
    }
    
    
    public List<Pending_deps> getbypending(Pending p) {
    	Connection conn = null;
        Statement stmt = null;
        Pending_deps pending_deps=null;
        PendingDAO pdao = new PendingDAO();
        TransactionDAO tdao = new TransactionDAO();
        Pending pending=null;Transactions transaction=null;
        List<Pending_deps> listPending_deps = new ArrayList<>();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from pending_deps where pending = " + p.getId() + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				int pending_id = rs.getInt("pending");
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if(pdao.getbyid(pending_id).isPresent() && tdao.getbyid(transaction_id).isPresent()) {
					// get value
					pending = pdao.getbyid(pending_id).get();
					transaction = tdao.getbyid(transaction_id).get();
				}
				pending_deps = new Pending_deps(rs.getInt("id"), pending, transaction);
				listPending_deps.add(pending_deps);
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
        return listPending_deps;
    }

    /* Get All Transactions from database*/
    @Override
    public List<Pending_deps> getAll() {
    	//initialization
    	Connection conn = null;
		Statement stmt = null;
		PendingDAO pdao = new PendingDAO();
        TransactionDAO tdao = new TransactionDAO();
		List<Pending_deps> listPending_deps = new ArrayList<>();
        Pending pending=null;Transactions transaction=null;

		try {
			String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			String sql = "select * from pending_deps;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int pending_id = rs.getInt("pending");
				
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if((pdao.getbyid(pending_id)).isPresent() && (tdao.getbyid(transaction_id)).isPresent()) {
					// get value
					
					pending = pdao.getbyid(pending_id).get();
					transaction = tdao.getbyid(transaction_id).get();
					Pending_deps pending_dep = new Pending_deps(rs.getInt("id"), pending, transaction);
					listPending_deps.add(pending_dep);
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
        return listPending_deps;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Pending_deps pd) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "INSERT INTO pending_deps(pending, transactions) VALUES(?,?)";
			System.out.print(query);
			myStmt = conn.prepareStatement(query);
			// Set Parameters
	        myStmt.setInt(1, pd.getPending().getId());
	        myStmt.setInt(2, pd.getTransaction().getId());
			// Execute SQL query
	        
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Pending_dep inserted\n");
	       
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
    public void update(Pending_deps pending_dep, String[] params) {
        // TODO Auto-generated method stub

    }

    /* Delete a Transaction from a database*/
    @Override
    public void delete(Pending_deps pd) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from pending_deps where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, pd.getId());
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Pending_dep deleted\n");
	       
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
    
    
    public void deletebypending(Pending p) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from pending_deps where pending = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, p.getId());
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Pending_dep deleted\n");
	       
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
	public Pending_deps getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
