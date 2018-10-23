package automap.xml;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author hmia
 */
public class Tag {

    private String label;
    private final String name;
    private final HashSet<Att> atts;
    private final ArrayList<Tag> content;

    public Tag setLabel(String label) {
        this.label = label;
        return this;
    }

    public Tag addTag(Tag tag) {
        content.add(tag);
        return this;
    }

    public void addTagTo(String label, Tag tag) {
        Tag ltag = getTagByLabel(label);
        if (ltag != null) {
            ltag.addTag(tag);
        }
    }

    public Tag getTagByLabel(String label) {
        if (this.label.equalsIgnoreCase(label)) {
            return this;
        } else {
            Tag found;
            for (Tag t : content) {
                if ((found = t.getTagByLabel(label)) != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public void addAtt(Att att) {
        atts.add(att);
    }

    public boolean removeAtt(Att att) {
        return atts.remove(att);
    }

    public void setAttValue(String attName, String value) {

    }

    public Tag(String n, Att[] att) {
        this.name = n;
        this.atts = new HashSet(att.length);
        this.content = new ArrayList<>();
        this.label = "";
    }

    public Tag(String label, String name, Att[] atts) {
        this.label = label;
        this.name = name;
        this.atts = new HashSet(atts.length);
        this.content = new ArrayList<>();
    }

    /**
     *
     * @param fc
     */
    public Tag(String fc) {
        String[] tokens = fc.split("\\#");
        HashSet<Att> atts = new HashSet<>();
        String partAttributes = (tokens != null && tokens.length > 1) ? tokens[1] : "";
        String[] l = partAttributes.split("\\/");

        for (int i = 0; i < l.length - 1; i = i + 2) {
            Att attribute = new Att(l[i], l[i + 1]);
            atts.add(attribute);
        }
        this.name = tokens[0];
        this.atts = atts;
        this.content = new ArrayList<>();
    }

    public String toXmlHibernateMapping() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>").append("\n");
        sb.append("<!DOCTYPE hibernate-mapping PUBLIC").append("\n");
        sb.append(getTabs(0)).append("\"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"").append("\n");
        sb.append(getTabs(0)).append("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\"").append("\n");
        sb.append(getTabs(0)).append("<!-- Automatic mapping classes generate by AutoMaping -->").append("\n");
        sb.append(this.toString());
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(0);
    }

    public String toString(int tab) {
        StringBuilder s = new StringBuilder();
        s.append(getTabs(tab)).append("<").append(this.name);
        /* Attributes*/
        for (Att a : this.atts) {
            s.append(a);
        }
        /*elements => contents */
        if (content.isEmpty()) {
            s.append("/>");
        } else {
            s.append(">").append("\n");
            System.out.println(s.toString());
            for (Tag t : content) {
                s.append(t.toString(tab + 1));
                System.out.println(s.toString());
            }
            s.append("\n");
            s.append(getTabs(tab)).append("</").append(this.name).append(">");
            System.out.println(s.toString());
        }
        System.out.println(s.toString());
        return s.toString();
    }

    String getTabs(int number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }
}
