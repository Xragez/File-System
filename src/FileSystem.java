import java.util.ArrayList;

public class FileSystem implements FileSystemInterface{
    private Directory currentDir;
    FileSystem(){
        currentDir = new Directory("root");
    }

    @Override
    public String help(){
        return  "Commands:\n" +
                "-- cd [directory path] - change directory\n" +
                "-- ls [directory path] - list elements in directory\n" +
                "-- mkfile [file name] [file content] - creates file in current directory\n" +
                "-- mkdir [directory name] - creates directory in current directory\n" +
                "-- rm [file name] - deletes file in current directory\n" +
                "-- rmdir [directory name] - deletes directory if empty\n" +
                "-- tree - list all files in current directory\n" +
                "-- more [file or directory name] shows content of a file\n";
    }

    @Override
    public Directory getCurrentDir(){
        return currentDir;
    }

    @Override
    public boolean createDirectory( String name){
        currentDir.addDir(new Directory(name, currentDir));
        return true;
    }

    @Override
    public void deleteDirectory( String name){
        Directory dir = findDirByName(currentDir, name);
        if(dir.getDirectories().size() == 0 && dir.getFiles().size() == 0)
            currentDir.rmDir(dir);
    }

    @Override
    public boolean createFile( String name, String content){
        currentDir.addFile(new File(name, content));
        return true;
    }

    @Override
    public void deleteFile( String name){
        currentDir.rmFile(findFileByName(name));
    }
    @Override
    public String tree(int intent){
       return currentDir.tree(intent );
    }
    @Override
    public String ls(){
        String list = "";
        for (File file: currentDir.getFiles()){
            list += " " + file.ls();
        }
        for (Directory dir: currentDir.getDirectories()){
            list += " " + dir.ls();
        }
        return list;
    }

    @Override
    public String more(String name){
        File file = findFileByName(name);
        if (file == null)
            return findDirByName(currentDir, name).more(name);

        return file.more(name);
    }

    @Override
    public boolean cd(String path){
        if (path.equals("")){
            while(currentDir.getParent() != null)
                currentDir = currentDir.getParent();
        }
        else {
            Directory dir = findDirectoryBYPath(path);
            if (dir == null)
                return false;
            else
                currentDir = dir;
        }
        return true;
    }

    private Directory findDirectoryBYPath(String path){
        Directory directory = currentDir;
        String[] dirs = path.split("/");
        for(String dir: dirs){
            if(dir.equals("."))
                directory = currentDir;
            else if(dir.equals(".."))
                directory = directory.getParent();
            else
                directory = findDirByName(directory, dir);
        }
        return directory;
    }

    private Directory findDirByName(Directory directory, String name){
        for (Directory dir: directory.getDirectories())
            if(dir.ls().equals(name))
                return dir;
        return null;
    }

    private File findFileByName(String name){
        for (File file: currentDir.getFiles())
            if(file.ls().equals(name))
                return file;
        return null;
    }
}
