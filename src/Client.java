import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Client {
    public void run() throws IOException {
        Proxy proxy = new Proxy(new FileSystem());
        boolean end = false;
        String input;
        String[] args;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(!end) {
            System.out.print("[user " + proxy.getCurrentDir().ls() + "]# ");
            input = reader.readLine();
            args = inputToArray(input);


            switch (args[0]) {
                case "help":
                    System.out.print(proxy.help());
                    break;
                case "more":
                    String more = proxy.more(args[1]);
                    if(more == null)
                        noFileDirErr();
                    else
                        System.out.println(more);
                    break;

                case "cd":
                    boolean found;
                    if (args.length == 1)
                        found = proxy.cd("");
                    else
                        found = proxy.cd(args[1]);
                    if (!found)
                        noFileDirErr();
                    break;

                case "ls":
                    System.out.println(proxy.ls());
                    break;

                case "mkfile":
                    if(args.length <= 3) {
                        if (!proxy.createFile(args[1], args[2]))
                            wrongName();
                    }
                    else
                        wrongNumArgs();
                    break;

                case "mkdir":
                    if(args.length == 1){
                        wrongNumArgs();
                    }
                    else if(!proxy.createDirectory(args[1]))
                        wrongName();
                    break;

                case "rm":
                    if(args.length == 1){
                        wrongNumArgs();
                    }
                    else
                        proxy.deleteFile(args[1]);
                    break;

                case "rmdir":
                    if(args.length == 1){
                        wrongNumArgs();
                    }
                    else
                        proxy.deleteDirectory(args[1]);
                    break;

                case "tree":
                    System.out.println(proxy.tree(0));
                    break;

                default:
                    wrongCom();
                    break;

            }
        }
    }

    private void noFileDirErr(){
        System.out.println("No such directory");
    }

    private void wrongNumArgs(){
        System.out.println("Wrong number of argumetns");
    }

    private void wrongCom(){
        System.out.println("Command not found");
    }

    private void wrongName(){
        System.out.println("Use of forbidden characters");
    }

    private String[] inputToArray(String input){
        ArrayList<String> args = new ArrayList<>();
        String arg = "";
        char regex = ' ';
        char c;
        for(int i = 0; i < input.length(); i++){
            c = input.charAt(i);
            if (c == '"'){
                if (regex == ' ')
                    regex = '"';
                else
                    regex = ' ';
            }
            else {
                if (c == regex){
                    args.add(arg);
                    arg = "";
                }
                else
                    arg += c;
            }
        }
        args.add(arg);
        if (args.size() == 2)
            args.add("");
        String[] res = new String[args.size()];
        return args.toArray(res);
    }

    private void printArray(String[] array){
        for(String str: array)
            System.out.println(str);
    }
}
