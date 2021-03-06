import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Proxy implements Component, FileSystemInterface{
    private FileSystem filesystem;
    private Pattern pattern = Pattern.compile("[^a-z0-9.]", Pattern.CASE_INSENSITIVE);
    private Matcher matcher;
    Proxy(FileSystem filesystem){
        this.filesystem = filesystem;
    }

    @Override
    public String tree(int intent) {
        return filesystem.tree(intent);
    }

    @Override
    public String ls() {
        return filesystem.ls();
    }

    @Override
    public String more(String name) {
        return filesystem.more(name);
    }

    @Override
    public boolean createFile( String name, String content){
        matcher = pattern.matcher(name);
        boolean b = matcher.find();
        if(!b)
            filesystem.createFile(name, content);
        return !b;
    }

    @Override
    public void deleteFile(String name) {
        filesystem.deleteFile(name);
    }

    @Override
    public boolean cd(String path) {
        return filesystem.cd(path);
    }

    @Override
    public String help() {
        return filesystem.help();
    }

    @Override
    public Directory getCurrentDir() {
        return filesystem.getCurrentDir();
    }

    @Override
    public boolean createDirectory(String name){
        matcher = pattern.matcher(name);
        boolean b = matcher.find();
        if(!b)
            filesystem.createDirectory(name);
        return !b;
    }

    @Override
    public void deleteDirectory(String name) {
        filesystem.deleteDirectory(name);
    }

}
