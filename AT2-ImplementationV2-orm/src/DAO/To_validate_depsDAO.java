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
import Beans.To_validate;
import Beans.To_validate_deps;
import Beans.Transactions;

public class To_validate_depsDAO implements DAO<To_validate_deps> {

    /* Get a To_validate_deps by Id from database*/
    @Override
    public Optional<To_validate_deps> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        To_validate_deps to_validate_deps=null;
        To_validateDAO tovdao = new To_validateDAO();
        TransactionDAO tdao = new TransactionDAO();
        To_validate to_validate=null;Transactions transaction=null;
        
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from to_validate_deps where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				int to_validate_id = rs.getInt("to_validate");
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if(tovdao.getbyid(to_validate_id).isPresent() && tdao.getbyid(transaction_id).isPresent()) {
					// get value
					to_validate = tovdao.getbyid(to_validate_id).get();
					transaction = tdao.getbyid(transaction_id).get();
				}
				to_validate_deps = new To_validate_deps(rs.getInt("id"), to_validate, transaction);
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
        return Optional.of(to_validate_deps);
    }
    
    
    public List<To_validate_deps> getbytovalidate(To_validate p) {
    	Connection conn = null;
        Statement stmt = null;
        To_validate_deps to_validate_deps=null;
        To_validateDAO pdao = new To_validateDAO();
        TransactionDAO tdao = new TransactionDAO();
        To_validate tovalidate=null;Transactions transaction=null;
        List<To_validate_deps> listTo_validate_deps = new ArrayList<>();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from to_validate_deps where to_validate = " + p.getId() + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				int tovalidate_id = rs.getInt("to_validate");
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if(pdao.getbyid(tovalidate_id).isPresent() && tdao.getbyid(transaction_id).isPresent()) {
					// get value
					tovalidate = pdao.getbyid(tovalidate_id).get();
					transaction = tdao.getbyid(transaction_id).get();
				}
				to_validate_deps = new To_validate_deps(rs.getInt("id"), tovalidate, transaction);
				listTo_validate_deps.add(to_validate_deps);
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
        return listTo_validate_deps;
    }

    /* Get All To_validate_deps from database*/
    @Override
    public List<To_validate_deps> getAll() {
    	//initialization
    	Connection conn = null;
		Statement stmt = null;
		To_validateDAO tovdao = new To_validateDAO();
        TransactionDAO tdao = new TransactionDAO();
		List<To_validate_deps> listTo_validate_deps = new ArrayList<>();
		To_validate to_validate=null;Transactions transaction=null;

		try {
			String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			String sql = "select * from to_validate_deps;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int to_validate_id = rs.getInt("to_validate");
				
				int transaction_id = rs.getInt("transactions");
				// get corresponding pending and transaction
				if((tovdao.getbyid(to_validate_id)).isPresent() && (tdao.getbyid(transaction_id)).isPresent()) {
					// get value
					
					to_validate = tovdao.getbyid(to_validate_id).get();
					transaction = tdao.getbyid(transaction_id).get();
					To_validate_deps to_validate_dep = new To_validate_deps(rs.getInt("id"), to_validate, transaction);
					listTo_validate_deps.add(to_validate_dep);
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
        return listTo_validate_deps;
    }

    /* Save a To_validate_deps into a database*/
    @Override
    public void save(To_validate_deps tvd) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into to_validate_deps(to_validate, transactions) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, tvd.getTo_validate().getId());
	        myStmt.setInt(2, tvd.getTransaction().getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate_deps inserted\n");
	       
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

    /* Update a To_validate_deps into a database*/
    @Override
    public void update(To_validate_deps t, String[] params) {
        // TODO Auto-generated method stub

    }

    /* Delete a To_validate_deps from a database*/
    @Override
    public void delete(To_validate_deps tvd) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from to_validate_deps where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, tvd.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate_deps deleted\n");
	       
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
    
    public void deletebyID(int ide) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from to_validate_deps where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, ide);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate_deps with id = "+ide+" deleted\n");
	       
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
    
    public void deletebyTovalidateID(int ide) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from to_validate_deps where to_validate = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, ide);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate_deps with to_validate_id = "+ide+" deleted\n");
	       
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
	public To_validate_deps getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
