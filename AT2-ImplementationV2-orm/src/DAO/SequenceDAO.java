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

import Beans.Sequence;

public class SequenceDAO implements DAO<Sequence> {

    /* Get a Transaction by Id from database*/
    @Override
    public Optional<Sequence> getbyid(int id) {
    	Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	Sequence sequence=null;
        ProcessesDAO pdao = new ProcessesDAO();
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from sequence where id =?");
	    	                                   
	    	pstmt.setInt(1,id);       
	
	    	rs = pstmt.executeQuery();
	    	while (rs.next()) {
	    		sequence = new Sequence(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("sequence"));
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
        return Optional.of(sequence);
    }

    /* Get All Transactions from database*/
    @Override
    public List<Sequence> getAll() {
    	Connection conn = null;
        Statement stmt = null;
        List<Sequence> listSequences = new ArrayList<>();

        try {
            String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            ProcessesDAO pdao = new ProcessesDAO();
            String sql = "select * from sequence;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

            	Sequence seq = new Sequence(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("sequence"));
                listSequences.add(seq);
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
        return listSequences;
    }

    /* Save a Sequence into a database*/
    @Override
    public void save(Sequence s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into sequence(identifier, sequence) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, s.getProcess().getIdentifier());
	        myStmt.setInt(2, s.getSequence());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Sequence inserted\n");
	       
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
    
    
    public void create_sequence(String identifier) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "insert into sequence(identifier, sequence) values(?,?)";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, identifier);
	        myStmt.setInt(2, 0);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Sequence Created Successfully \n");
	       
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
    
    

    /* Update a Sequence into a database*/
    @Override
    public void update(Sequence s, String[] param) {
        // TODO Auto-generated method stub
    	Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update sequence set identifier=?, sequence=? where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
			
	        myStmt.setString(1, param[0]);
	        myStmt.setInt(2, Integer.parseInt(param[1]));
	        myStmt.setInt(3, s.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " Sequence updated");
	       
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
    
    
    public void updatesequence(String identifier, int new_sequence) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "update sequence set sequence=? where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
			
	        myStmt.setInt(1, new_sequence);
	        myStmt.setString(2, identifier);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " " + identifier + "'s sequence updated");
	       
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

    /* Delete a Sequence from a database*/
    @Override
    public void delete(Sequence s) {
        // TODO Auto-generated method stub
    	//Establish cnx
		Connection conn = null;
		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
		
		try {
			conn = DriverManager.getConnection(url);
			PreparedStatement myStmt; 
			String query = "delete from sequence where id = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setInt(1, s.getId());
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " sequence deleted");
	       
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
			String query = "delete from sequence where identifier = ?";
			myStmt = conn.prepareStatement(query);
			
			// Set Parameters
	        myStmt.setString(1, identifier);
			// Execute SQL query
	        int res = myStmt.executeUpdate();
	 
	        // Display the record inserted
	        System.out.println(res + " "+ identifier + "'s Sequence is deleted");
	       
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
	public Sequence getbyidentifier(String identifier) {
		// TODO Auto-generated method stub
		Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs;
    	Sequence seq=null;List<Sequence> seqs=new ArrayList<>();
        ProcessesDAO pdao = new ProcessesDAO();
    	try {
    		String url = "jdbc:sqlite:src\\database\\AT2_Mobile.db";
    		conn = DriverManager.getConnection(url);
	    	pstmt = conn.prepareStatement(
	    	  "select * from sequence where identifier =?"); 
	    	                                   
	    	pstmt.setString(1,identifier);       
	
	    	rs = pstmt.executeQuery();        
	    	while (rs.next()) {
	    		seqs.add(new Sequence(rs.getInt("id"), pdao.getbyidentifier(rs.getString("identifier")), rs.getInt("sequence")));
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
        	return seq;
    	}
    	return null;
    	}
   }
