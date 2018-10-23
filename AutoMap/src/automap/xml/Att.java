package automap.xml;

/**
 *
 * @author hmia
 */
public class Att {

    private final String name;
    private String content;

    /**
     * Create a new attribute with fc that contains the name and the content
     * separate by / example attrituteName/content
     * @param fc
     */
    public Att(String fc) {
        String[] l = getToken(fc);
        name = l[0];
        content = l[1];
    }

    /**
     *
     * @param name
     * @param content
     */
    public Att(String name, String content) {
        this.name = name;
        this.content = content;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(" ").append(name).append("=\"").append(content).append("\"").toString();
    }

    private String[] getToken(String fc) {
        return fc.split("\\/");
    }
}
