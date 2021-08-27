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

public class To_validateDAO implements DAO<To_validate> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<To_validate> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        To_validate to_validate=null;
        ProcessesDAO pdao = new ProcessesDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from to_validate where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
            	String issuer = rs.getString("issuer");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				to_validate = new To_validate(rs.getInt("id"), pdao.getbyidentifier(issuer), pdao.getbyidentifier(source),
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
        return Optional.of(to_validate);
    }

    /* Get All  from database*/
    @Override
    public List<To_validate> getAll() {
    	Connection conn = null;
		Statement stmt = null;
		ProcessesDAO pdao = new ProcessesDAO();
		List<To_validate> listTo_validate = new ArrayList<>();

		try {
			String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			String sql = "select * from to_validate;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String issuer = rs.getString("issuer");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				To_validate to_validate = new To_validate(rs.getInt("id"), pdao.getbyidentifier(issuer), pdao.getbyidentifier(source),
						pdao.getbyidentifier(destination), rs.getFloat("asset"), rs.getInt("seq"));
				listTo_validate.add(to_validate);
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
        return listTo_validate;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(To_validate s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into to_validate(issuer, source, destination, asset, seq) values(?,?,?,?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, s.getIssuer().getIdentifier());
	        myStmt.setString(2, s.getSource().getIdentifier());
	        myStmt.setString(3, s.getDestination().getIdentifier());
	        myStmt.setFloat(4, s.getAsset());
	        myStmt.setInt(5, s.getSeq());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate inserted\n");
	       
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
    public void update(To_validate s, String[] params) {
        // TODO Auto-generated method stub
    	
    }

    /* Delete a Transaction from a database*/
    @Override
    public void delete(To_validate s) {
        // TODO Auto-generated method stub
    	Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from to_validate where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, s.getId());
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate deleted\n");
	       
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
    	Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from to_validate where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, ide);
	        
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " To_validate with ide = " + ide + " is deleted\n");
	       
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
	public To_validate getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
