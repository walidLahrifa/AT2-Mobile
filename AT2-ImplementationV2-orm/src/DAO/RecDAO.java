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

import Beans.Rec;
public class RecDAO implements DAO<Rec> {

    /* Get a Rec by Id from database*/
    @Override
    public Optional<Rec> getbyid(int id) {
    	Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	Rec rec=null;
        ProcessesDAO pdao = new ProcessesDAO();
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from rec where id =?"); 
	    	                                   
	    	pstmt.setInt(1,id);       
	
	    	rs = pstmt.executeQuery();        
	    	while (rs.next()) {
	    		rec = new Rec(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("rec_number"));
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
        return Optional.of(rec);
    
    }

    /* Get All  from database*/
    @Override
    public List<Rec> getAll() {
        Connection conn = null;
        Statement stmt = null;
        List<Rec> listRecs = new ArrayList<>();

        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            ProcessesDAO pdao = new ProcessesDAO();
            String sql = "select * from rec;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
            	
                Rec rec = new Rec(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("rec_number"));
                listRecs.add(rec);
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
        return listRecs;
    }

    /* Save a rec into a database*/
    @Override
    public void save(Rec r) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into rec(identifier, rec_number) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, r.getProcess().getIdentifier());
	        myStmt.setInt(2, r.getRec_number());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Rec inserted\n");
	       
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
    
    
    public void create_rec(String identifier) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into rec(identifier, rec_number) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1,  identifier);
	        myStmt.setInt(2, 0);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Rec Created Successfully \n");
	       
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

    /* Update a Rec into a database*/
    @Override
    public void update(Rec r, String[] param) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update rec set identifier=?, rec_number=? where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
			
	        myStmt.setString(1, param[0]);
	        myStmt.setInt(2, Integer.parseInt(param[1]));
	        myStmt.setInt(3, r.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Rec updated");
	       
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
    public void updatebyrec_number(String identifier, int new_rec) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update rec set rec_number=? where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
			
	        myStmt.setInt(1, new_rec);
	        myStmt.setString(2, identifier);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " " + identifier + "'s rec_number updated");
	       
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
    public void delete(Rec r) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from rec where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, r.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Rec deleted");
	       
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
    
    public void deletebyidentifier(String identifier) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from rec where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, identifier);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " "+ identifier + "'s Rec is deleted");
	       
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
	public Rec getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	List<Rec> recs= new ArrayList<>();
    	Rec rec = null;
        ProcessesDAO pdao = new ProcessesDAO();
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from rec where identifier =?"); 
	    	                                   
	    	pstmt.setString(1,identifier);       
	
	    	rs = pstmt.executeQuery();        
	    	while (rs.next()) {
	    		recs.add(new Rec(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("rec_number")));
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
    	if(recs==null || recs.size()==0) {
    		System.out.println("No value found");
        	
    	}else if(recs.size()>1){
    		System.out.println("Too many values found for a unique value");
    	}else {
    		rec = recs.get(0);
        	return rec;
    	}
    	return null;
    }
	}
