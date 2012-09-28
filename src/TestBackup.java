import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.text.*;

public class TestBackup
{

	public TestBackup()
	{
		
		Backup b = new Backup();
		try
		{
		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
	   	 	java.util.Date date = new java.util.Date();
   		 
			byte[] data = b.getData("localhost", "3306", "root", "", "sams").getBytes();
			byte[] routine = b.getRoutine("localhost", "3306", "root", "", "sams").getBytes();
			
			
			
			File filedst = new File("F:\\sams_"+dateFormat.format(date)+".zip");

			FileOutputStream dest = new FileOutputStream(filedst);
			ZipOutputStream zip = new ZipOutputStream(
			new BufferedOutputStream(dest));
			zip.setMethod(ZipOutputStream.DEFLATED);
			zip.setLevel(Deflater.BEST_COMPRESSION);	

			zip.putNextEntry(new ZipEntry("sams.sql"));
			zip.write(data);

			zip.putNextEntry(new ZipEntry("routine.sql"));
			zip.write(routine);

			zip.close();
			dest.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

}

