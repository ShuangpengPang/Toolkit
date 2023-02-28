import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class KillProcess {

    public static void main(String[] args) {
    	System.err.println("start to kill process");
        int cnt = 0;
        while (true) {
            try {
                Process process = new ProcessBuilder(new String[]{"/bin/sh", "-c", " ps aux | pgrep coreduetd"}).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ( (line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String result = builder.toString().trim();
                if (result != null && !result.isEmpty()) {
                    new ProcessBuilder(new String[]{"/bin/sh", "-c", " echo 187263123 | sudo -S kill -9 " + result}).start();
//                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process1.getOutputStream()));
//                    bw.write("187263123\n");
//                    bw.flush();
                    System.err.println("kill process: " + new Date(System.currentTimeMillis()) + " " + ++cnt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


