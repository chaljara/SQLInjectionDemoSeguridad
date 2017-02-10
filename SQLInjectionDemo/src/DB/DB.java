package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private final static String url = "jdbc:sqlite:C:\\sqlite\\chinook.db";
	
	public static void create() {
        Connection conn = null;
        // SQL statement for creating a new table
        //http://www.guru99.com/learn-sql-injection-with-practical-example.html
        String sql = "CREATE TABLE IF NOT EXISTS contact (\n"
                + "	id integer PRIMARY KEY NOT NULL,\n"
                + "	name text NOT NULL,\n"
                + "	password text NOT NULL\n"
                + ");";
        Statement stmt = null;
        try {
        	conn = DriverManager.getConnection(url);
	        stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
        	if(stmt != null)
        	{
        		try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if(conn != null)
        	{
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
    }

	public static boolean insert(){
		Connection conn = null;
		Statement  stmt = null;
		
		try {
			conn = DriverManager.getConnection(url);
			String insert_stmt = "insert into contact(id,name,password) values(1,christian,qwerty);";
			conn.createStatement();
		      boolean rs = stmt.execute(insert_stmt);
		      return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
        	if(stmt != null)
        	{
        		try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if(conn != null)
        	{
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
		return false;
	}
	
	public static void query(String query){
		Connection conn = null;
		Statement  stmt = null;
		
		try {
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(query);
		      while(rs.next()) {
		          System.out.println("ALBUM --> " + rs.getInt("AlbumId") + " "+
		        		  rs.getString("Title")+" "+
		        		  rs.getString("ArtistId"));
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
        	if(stmt != null)
        	{
        		try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if(conn != null)
        	{
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	public static void main(String [] args){
		query("select * from albums");
	}
}

