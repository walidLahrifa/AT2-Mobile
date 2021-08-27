/**
 * Author :           @ENNIARI
 * Creation Date :    11/08/2021
 * Last Update Date : 11/08/2021
 * Description :      DAO Class for Transaction Table
 * 
 */

package DAO;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Beans.Processes;
import Beans.Transactions;

public class TransactionDAO implements DAO<Transactions> {

/* Get a Transaction by Id from database*/ 
	@Override
	public Optional<Transactions> getbyid(int id) {
		Connection conn = null;
        Statement stmt = null;
        Transactions transaction=null;
        ProcessesDAO pdao = new ProcessesDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from transactions where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				transaction = new Transactions(rs.getInt("id"), pdao.getbyidentifier(source),
						pdao.getbyidentifier(destination), rs.getFloat("asset"), rs.getInt("seq"));
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
        return Optional.of(transaction);
	}
	
	
	public List<Transactions> getallbysource (String identifier) {
		Connection conn = null;
        Statement stmt = null;
        ProcessesDAO pdao = new ProcessesDAO();
        List<Transactions> listTransactions = new ArrayList<>();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from transactions where source= '" + identifier + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				Transactions transaction = new Transactions(rs.getInt("id"), pdao.getbyidentifier(source),
						pdao.getbyidentifier(destination), rs.getFloat("asset"), rs.getInt("seq"));
				listTransactions.add(transaction);
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
        return listTransactions;
    }

/* Get All Transactions from database*/ 
	@Override
	public List<Transactions> getAll() {
		Connection conn = null;
		Statement stmt = null;
		ProcessesDAO pdao = new ProcessesDAO();
		List<Transactions> listTransactions = new ArrayList<>();

		try {
			String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			String sql = "select * from transactions;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				Processes s = pdao.getbyidentifier(source);
				Processes d = pdao.getbyidentifier(destination);
				Transactions transaction = new Transactions(rs.getInt("id"), s,
						d, rs.getFloat("asset"), rs.getInt("seq"));
				listTransactions.add(transaction);
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
		return listTransactions;
	}

	/* Save a Transaction into a database*/ 
	@Override
	public void save(Transactions t) {
		// TODO Auto-generated method stub
		//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into transactions(source, destination, asset, seq) values(?,?,?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, t.getSource().getIdentifier());
	        myStmt.setString(2, t.getDestination().getIdentifier());
	        myStmt.setFloat(3,	t.getAsset());
	        myStmt.setInt(4, t.getSeq());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Transaction inserted\n");
	       
	        // Close the connection
	        conn.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(" Transaction cannot be  inserted\n");}
			
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
	public void update(Transactions t, String[] param) {
		// TODO Auto-generated method stub
		
		//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update transactions set source=?, destination=?, asset=?, seq=? where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, param[0]);
	        myStmt.setString(2, param[1]);
	        myStmt.setFloat(3, Float.parseFloat(param[2]));
	        myStmt.setInt(4, Integer.parseInt(param[3]));
	        myStmt.setInt(5, t.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Transaction updateted");
	       
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
	public void delete(Transactions t) {
		// TODO Auto-generated method stub
		//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from transactions where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, t.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Transaction deleted");
	       
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
	
	public void deletebyId(int ide) {
		// TODO Auto-generated method stub
		//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from transactions where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, ide);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Transaction deleted");
	       
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
	public Transactions getbyidentifier(String identifier) {
		return null;
	}
	}

