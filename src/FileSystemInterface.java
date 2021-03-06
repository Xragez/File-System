public interface FileSystemInterface extends Component {
    String help();
    Directory getCurrentDir();
    boolean createDirectory(String name);
    void deleteDirectory(String name);
    boolean createFile(String name, String content);
    void deleteFile(String name);
    boolean cd(String path);
    @Override
    String more(String name);
    @Override
    String ls();
    @Override
    String tree(int intent);
}
