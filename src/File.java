public class File implements Component {
    private String name;
    private String content = "";

    File(String name, String content) {
        this.name = name;
        this.content = content;
    }
    @Override
    public String tree(int intent) {
        return "└──" + name + "\n";
    }

    @Override
    public String ls() {
        return name;
    }

    @Override
    public String more(String name) {
        return content;
    }
}