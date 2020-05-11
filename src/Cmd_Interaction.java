
public class Cmd_Interaction {
    //Longer but more effective and doesn't open a physical window
    public static
    boolean runCMD(String command) {
        try{
            String[] commands = {"cmd", "/c", command};
            Process p = Runtime.getRuntime().exec(commands);
            p.waitFor();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
