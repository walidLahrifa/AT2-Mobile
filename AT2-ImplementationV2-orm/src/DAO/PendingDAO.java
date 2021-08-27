
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Beans.Pending;
import Beans.Processes;

public class PendingDAO implements DAO<Pending> {
	
	/* Get a Pending by Id from database*/
    @Override
    public Optional<Pending> getbyid(int id) {
    	Connection conn = null;
        Statement stmt = null;
        Pending pending=null;
        ProcessesDAO pdao = new ProcessesDAO();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from pending where id= " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				pending = new Pending(rs.getInt("id"), pdao.getbyidentifier(source),
						pdao.getbyidentifier(destination), rs.getInt("sequence"), rs.getFloat("asset"), rs.getInt("seq"));
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
        return Optional.of(pending);
    }
    
    public List<Pending> getallbysource (String identifier) {
		Connection conn = null;
        Statement stmt = null;
        ProcessesDAO pdao = new ProcessesDAO();
        List<Pending> listPendings = new ArrayList<>();
        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            String sql = "select * from pending where source= '" + identifier + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
				
				String destination = rs.getString("destination");
				Pending pending = new Pending(rs.getInt("id"), pdao.getbyidentifier(identifier),
						pdao.getbyidentifier(destination), rs.getInt("sequence"), rs.getFloat("asset"), rs.getInt("seq"));
				listPendings.add(pending);
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
        return listPendings;
    }

    public List<Pending> getAll() {
    	Connection conn = null;
		Statement stmt = null;
		ProcessesDAO pdao = new ProcessesDAO();
		List<Pending> listPendings = new ArrayList<>();

		try {
			String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			String sql = "select * from pending;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				Processes s = pdao.getbyidentifier(source);
				Processes d = pdao.getbyidentifier(destination);
				Pending transaction = new Pending(rs.getInt("id"), s,
						d, rs.getInt("sequence"), rs.getFloat("asset"), rs.getInt("seq"));
				listPendings.add(transaction);
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
		return listPendings;
	}

    public void save(Pending p) {
    	
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into pending(source, destination, sequence, asset, seq) values(?,?,?,?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, p.getSource().getIdentifier());
	        myStmt.setString(2, p.getDestination().getIdentifier());
	        myStmt.setInt(3, p.getSequence());
	        myStmt.setFloat(4,	p.getAsset());
	        myStmt.setInt(5, p.getSeq());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Pending inserted\n");
	       
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

    public void update(Pending p, String[] param) {
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update pending set source=?, destination=?, sequence=?, asset=?, seq=? where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, param[0]);
	        myStmt.setString(2, param[1]);
	        myStmt.setInt(3, Integer.parseInt(param[2]));
	        myStmt.setFloat(4, Float.parseFloat(param[3]));
	        myStmt.setInt(5, Integer.parseInt(param[4]));
	        myStmt.setInt(6, p.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Pending updated");
	       
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

    public void delete(Pending p) {
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from pending where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, p.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " pending deleted");
	       
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
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from pending where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, ide);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " pending deleted");
	       
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
	public Pending getbyidentifier(String identifier) {
		return null;
    }
	
}
