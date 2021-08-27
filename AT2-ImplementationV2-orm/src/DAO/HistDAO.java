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

import Beans.Hist;

public class HistDAO implements DAO<Hist> {

    /* Get a hist by Id from database*/
    @Override
    public Optional<Hist> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        Hist hist = null;
        ProcessesDAO pdao = new ProcessesDAO();
        TransactionDAO tdao = new TransactionDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from hist where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				String process = rs.getString("identifier");
				int transaction_id = rs.getInt("transactions");
				if(pdao.getbyidentifier(process) != null && tdao.getbyid(transaction_id).isPresent()) {
					hist = new Hist(rs.getInt("id"), pdao.getbyidentifier(process),
						tdao.getbyid(transaction_id).get());
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
        return Optional.of(hist);
    }

    /* Get All  from database*/
    @Override
    public List<Hist> getAll() {
        Connection conn = null;
        Statement stmt = null;
        List<Hist> listHists = new ArrayList<>();
        Hist hist = null;
        ProcessesDAO pdao = new ProcessesDAO();
        TransactionDAO tdao = new TransactionDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from hist;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				String process = rs.getString("identifier");
				int transaction_id = rs.getInt("transactions");
				if(pdao.getbyidentifier(process) != null && tdao.getbyid(transaction_id).isPresent()) {
					hist = new Hist(rs.getInt("id"), pdao.getbyidentifier(process),
						tdao.getbyid(transaction_id).get());
					listHists.add(hist);
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
        return listHists;
    }
    
    // get all hist of a specific process 
    public List<Hist> getAllbyprocess(String iden) {
        Connection conn = null;
        Statement stmt = null;
        List<Hist> listHists = new ArrayList<>();
        Hist hist = null;
        ProcessesDAO pdao = new ProcessesDAO();
        TransactionDAO tdao = new TransactionDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            String sql = "select * from hist where identifier = '" + iden + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
				int transaction_id = rs.getInt("transactions");
				if(pdao.getbyidentifier(iden) != null && tdao.getbyid(transaction_id).isPresent()) {
					hist = new Hist(rs.getInt("id"), pdao.getbyidentifier(iden),
						tdao.getbyid(transaction_id).get());
					listHists.add(hist);
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
        return listHists;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Hist h) {
        // TODO Auto-generated method stub
    	Connection conn = null;
    	String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
        try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into hist(identifier, transactions) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, h.getProcess().getIdentifier());
	        myStmt.setInt(2, h.getTransactions().getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Hist inserted\n");
	       
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
    public void update(Hist h, String[] params) {
        // TODO Auto-generated method stub

    }

    /* Delete a Transaction from a database*/
    @Override
    public void delete(Hist h) {
        // TODO Auto-generated method stub
    	// TODO Auto-generated method stub
    	Connection conn = null;
    	String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
        try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from hist where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, h.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();

	        // Display the record inserted
	        System.out.println(res + " Hist deleted\n");
	       
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
	public Hist getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
