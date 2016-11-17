
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Backup {

    public Backup() {
    }
    private int BUFFER = 10485760;

    public String getData(String host, String port, String user,String password, String db) throws Exception {

        String runCommand = "mysqldump --host=" + host + " --port=" + port
                + " --user=" + user + " --password=" + password
                + " --compact --complete-insert --extended-insert "
                + "--skip-comments --skip-triggers " + db;
        System.out.println(runCommand);
        Process run = Runtime.getRuntime().exec(runCommand);
        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer temp = new StringBuffer();

        int count;
        char[] cbuf = new char[BUFFER];

        while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
            temp.append(cbuf, 0, count);
        }

        br.close();
        in.close();

        return temp.toString();
    }

    public String getRoutine(String host, String port, String user, String password, String db) throws Exception {

        Process run = Runtime.getRuntime().exec(
                "mysqldump --host=" + host + " --port=" + port
                + " --user=" + user + " --password=" + password
                + " --compact --skip-comments --no-create-info "
                + "--no-data --routines " + db);
        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer temp = new StringBuffer();

        int count;
        char[] cbuf = new char[BUFFER];

        while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
            temp.append(cbuf, 0, count);
        }

        br.close();
        in.close();

        return temp.toString();
    }
}
