import java.util.ArrayList;

public class Directory implements Component{
    private Directory parent = null;
    private ArrayList<Directory> directories = new ArrayList<Directory>();
    private ArrayList<File> files = new ArrayList<File>();
    private String name;

    Directory(String name, Directory parent){
        this.name = name;
        this.parent = parent;
    }
    
    Directory(String name){
        this.name = name;
    }

    public void addDir(Directory child){
        directories.add(child);
    }

    public void addFile(File child){
        files.add(child);
    }

    public void rmDir(Directory child){
        directories.remove(child);
    }

    public void rmFile(File child){
        files.remove(child);
    }

    public ArrayList<File> getFiles(){
        return files;
    }

    public ArrayList<Directory> getDirectories(){
        return directories;
    }

    public Directory getParent(){
        return parent;
    }

    @Override
    public String tree(int intent) {
        String tree = ls() + "\n";
        Directory dir = this;

            for(File file: dir.getFiles()){
                tree += getIntentString(intent);
                tree += file.tree(intent);
            }
            for(Directory directory: dir.getDirectories()){
                tree += getIntentString(intent);
                tree += "└──" + directory.tree(intent + 1);
            }


        return tree;
    }

    @Override
    public String ls() {
        return name;
    }

    @Override
    public String more(String name) {
        return "*** Directory: " + name + " ***";
    }

    private String getIntentString(int intent){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < intent; i++){
            str.append("   ");
        }
        return str.toString();
    }

}
