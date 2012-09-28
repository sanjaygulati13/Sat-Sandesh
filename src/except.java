/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Sanjay
 */
public class except {

    

    public except(Exception e, String file1)
    {
    	
    	
        try
        {

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dat=(String)dateFormat.format(date);

            String newline=System.getProperty("line.separator");
            String abc="";
            abc=dat+""+newline+e+" % "+file1+newline;
            //System.out.println(abc);

            //abc=newline+abc;
            int len=abc.length();
			byte buf[]=abc.getBytes();

            String path="c:/sams_log.sams";

			File f1=new File(path);
			FileOutputStream fobj=new FileOutputStream(f1,true);
			for(int k=0;k<len;k++)
			{
                fobj.write(buf[k]);
			}
            fobj.close();
        }
        catch(Exception e1)
        {

        }
    }


}
