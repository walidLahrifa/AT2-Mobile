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

import Beans.Seq;
public class SeqDAO implements DAO<Seq> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Seq> getbyid(int id) {
    	Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	Seq seq=null;
        ProcessesDAO pdao = new ProcessesDAO();
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from seq where id =?");
	    	                                   
	    	pstmt.setInt(1,id);       
	
	    	rs = pstmt.executeQuery();
	    	while (rs.next()) {
	    		seq = new Seq(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("seq_number"));
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
        return Optional.of(seq);
    }

    /* Get All Transactions from database*/
    @Override
    public List<Seq> getAll() {
        Connection conn = null;
        Statement stmt = null;
        List<Seq> listSeqs = new ArrayList<>();

        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            ProcessesDAO pdao = new ProcessesDAO();
            String sql = "select * from seq;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Seq seq = new Seq(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("seq_number"));
                listSeqs.add(seq);
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
        return listSeqs;
    }

    /* Save a Transaction into a database*/
    @Override
    public void save(Seq s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into seq(identifier, seq_number) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, s.getProcess().getIdentifier());
	        myStmt.setInt(2, s.getSeq_number());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Seq inserted\n");
	       
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
    
    public void create_seq(String identifier) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into seq(identifier, seq_number) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, identifier);
	        myStmt.setInt(2, 0);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Seq Created Successfully \n");
	       
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
    public void update(Seq s, String[] param) {
        // TODO Auto-generated method stub
    	Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update seq set identifier=?, seq_number=? where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
			
	        myStmt.setString(1, param[0]);
	        myStmt.setInt(2, Integer.parseInt(param[1]));
	        myStmt.setInt(3, s.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Seq updated");
	       
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
    
    public void updatebyseq_number(String identifier, int new_seq) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update seq set seq_number=? where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
			
	        myStmt.setInt(1, new_seq);
	        myStmt.setString(2, identifier);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " " + identifier + "'s seq_number updated");
	       
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
    public void delete(Seq s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from seq where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, s.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " seq deleted");
	       
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
			String query = "delete from seq where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, identifier);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " "+ identifier + "'s Seq is deleted");
	       
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
	public Seq getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	Seq seq=null;List<Seq> seqs=new ArrayList<>();
        ProcessesDAO pdao = new ProcessesDAO();
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from seq where identifier = ?"); 
	    	                                   
	    	pstmt.setString(1,identifier);       
	
	    	rs = pstmt.executeQuery();        
	    	while (rs.next()) {
	    		seqs.add(new Seq(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("seq_number")));
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
    	if(seqs==null || seqs.size()==0) {
    		System.out.println("No value found");
        	
    	}else if(seqs.size()>1){
    		System.out.println("Too many values found for a unique value");
    	}else {
    		seq = seqs.get(0);
    		//System.out.println("tkharbi9a");
        	return seq;
    	}
    	//System.out.println("tkharbi9a2");
    	return null;
    	}

}
