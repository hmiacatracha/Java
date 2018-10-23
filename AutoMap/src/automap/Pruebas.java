package automap;

import automap.parsing.AnnotationLine;
import automap.parsing.ClassLine;
import automap.exceptions.InvalidLineException;
import automap.parsing.PackLine;
import automap.parsing.PropertyLine;
import automap.xml.Att;
import automap.xml.Tag;

/**
 *
 * @author hmia
 */
public class Pruebas {

    public static void main(String[] args) {
        Att atributo = new Att("prueba", "2");
        Att atribut2 = new Att("prueba/4");
        Tag root = new Tag("hibernate-mapping#package/org.hibernate.tutorial.domain").setLabel("root");
        Tag tagClass = new Tag("class#name/Event/table/EVENTS").setLabel("class");
        Tag id = new Tag("id#name/id/column/EVENT_ID").setLabel("id");
        Tag generator = new Tag("generator#class/native").setLabel("generator");

        id.addTag(generator);
        tagClass.addTag(id);
        root.addTag(tagClass);
        
        ClassLine cl = null;
        try {
            cl = new ClassLine("public class  Pruebas  {");
            System.out.println(cl.getModifier());
            System.out.println(cl.getName());
            cl = new ClassLine("public abstract class  Pruebas  {");
            System.out.println(cl.getModifier());
            System.out.println(cl.getName());
        } catch (InvalidLineException ex) {
            System.out.println(ex.getMessage());
        }

        AnnotationLine al = null;
        try {
            al = new AnnotationLine("           \\@H          ");
            System.out.println(al.getAnnotation());
            al = new AnnotationLine("\\@HPk");
            System.out.println(al.getAnnotation());
        } catch (InvalidLineException ex) {
            System.out.println(ex.getMessage());
        }

        PropertyLine pl = null;
        try {
            pl = new PropertyLine("private      String      name;");
            System.out.println(pl);
            System.out.println(pl.getName());
            pl = new PropertyLine("private List<Integer> prueba;");
            System.out.println(pl);
            System.out.println(pl.getName());
        } catch (InvalidLineException ex) {
            System.out.println(ex.getMessage());
        }

        PackLine packLine = null;
        try {
            packLine = new PackLine("package hola.com.princesa;");
            System.out.println(packLine);
            System.out.println(packLine.getName());
            packLine = new PackLine("package List<Integer> prueba;");
            System.out.println(packLine);
            System.out.println(packLine.getName());
        } catch (InvalidLineException ex) {
            System.out.println(ex.getMessage());
        }      
    }
}
