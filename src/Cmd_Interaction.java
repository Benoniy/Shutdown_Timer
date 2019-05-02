import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Cmd_Interaction {
    //Longer but more effective and doesn't open a physical window
    public static
    boolean runCMD(String command) {
        try{
            String[] commands = {"cmd", "/c", command};
            Process p = Runtime.getRuntime().exec(commands);
            p.waitFor();
            BufferedReader read = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = read.readLine())!= null){
                System.out.println(line);
            }
            read.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
