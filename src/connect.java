import java.sql.*;
public class connect
{
	Connection con;
	ResultSet rs;
	String k=null;
	Statement st, st1, st2;
	int a;
	PreparedStatement pst;
	static String user=null;
	connect()
	{
		try
		{
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:sams");
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sams","root","");
			st=con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Error in Connection");
			e.printStackTrace();
		}
	}
        public void closeAll()
        {
            try
            {
                con.close();
            }
            catch(Exception exp)
            {
                //Except.except(exp, "connect.java--Error in close()");
                //exp.printStackTrace();
                
            }

        }
	
}