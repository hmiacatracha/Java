package automap.parser;

import automap.IOHandlerDir;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 *
 * @author hmia
 */
public class ObjectClass {
    private String name;
    private HashSet<PropertyLine> propertyLines = new HashSet<>();
    private HashSet<ConstructorLine> constructorLines = new HashSet<>();
    private HashSet<Object> lines = new HashSet<>();

    public ObjectClass(File f) throws FileNotFoundException {
        String[] l = IOHandlerDir.readFile(f);
    }

    public boolean isAPersistenteClass() {
        return true;
    }

    private static boolean isClassLine(String line) {
        try {
            ClassLine l = new ClassLine(line);
            return true;
        } catch (NoValidLineException ex) {
            return false;
        }
    }

    private static boolean isConstructorLine(String line) {
        return true;
    }
}
