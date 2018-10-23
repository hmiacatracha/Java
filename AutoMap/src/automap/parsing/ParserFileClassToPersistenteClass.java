package automap.parsing;

import automap.exceptions.InvalidLineException;
import automap.IOHandlerDir;
import automap.exceptions.NoPersistenteClassException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author hmia
 */
public class ParserFileClassToPersistenteClass {

    private ClassLine classLine;
    private PackLine packLine;
    private PropertyLine id;
    private HashSet<PropertyLine> propertyLines = new HashSet<>();
    private final List<AnnotationLine> annotationLines = new ArrayList<>();
    private HashSet<ConstructorLine> constructorLines = new HashSet<>();

    public ParserFileClassToPersistenteClass(File f) throws FileNotFoundException, NoPersistenteClassException {
        String[] lines = IOHandlerDir.readFile(f);
        classifyTheLines(lines);
        if (!isAPersistenteClass()) {
            throw new NoPersistenteClassException(f.getName());
        }
    }

    public ClassLine getClassLine() {
        return classLine;
    }

    public PackLine getPackLine() {
        return packLine;
    }

    public HashSet<PropertyLine> getPropertyLines() {
        return propertyLines;
    }

    /**
     * para añadir el id en caso que no exista
     *
     * @param propertyLine
     */
    public void addPropertyLine(PropertyLine propertyLine) {
        this.propertyLines.add(propertyLine);
    }

    public List<AnnotationLine> getAnnotationLines() {
        return annotationLines;
    }

    public HashSet<ConstructorLine> getConstructorLines() {
        return constructorLines;
    }

    /**
     * Anadir el constructor por defecto en caso que no exista
     *
     * @param constructorLines
     */
    public void addConstructorLine(HashSet<ConstructorLine> constructorLines) {
        this.constructorLines = constructorLines;
    }

    private boolean isAPersistenteClass() {
        return classLine != null && !propertyLines.isEmpty() && !annotationLines.isEmpty();
    }

    private void classifyTheLines(String[] l) {
        for (int i = 0; i < l.length - 1; i++) {
            if (isPackLine(l[i])) {
                packLine = getPackLine(l[i]);
            }
            if (isClassLine(l[i])) {
                classLine = getClassLine(l[0]);
            }
            if (isConstructorLine(l[i])) {
                constructorLines.add(getConstructorLine(l[0]));
            }
            if (isAnnotationLine(l[i])) {

                if (isPropertyLine(l[i + 1])) {

                }
                i++;
            }
        }
    }

    private static boolean isPackLine(String line) {
        return getPackLine(line) != null;
    }

    private static boolean isClassLine(String line) {
        return getClassLine(line) != null;
    }

    private static boolean isAnnotationLine(String line) {
        return getAnnotationLine(line) != null;
    }

    private static boolean isPropertyLine(String line) {
        return getPropertyLine(line) != null;
    }

    private static boolean isPrimaryKeyLine(String line) {
        return getPrimaryKeyLine(line) != null;
    }

    private static boolean isConstructorLine(String line) {
        //without implements
        return true;
    }

    private static boolean isMethodLine(String line) {
        //without implements
        return true;
    }

    private static ClassLine getClassLine(String line) {
        ClassLine l = null;
        try {
            l = new ClassLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static PackLine getPackLine(String line) {
        PackLine l = null;
        try {
            l = new PackLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static AnnotationLine getAnnotationLine(String line) {
        AnnotationLine pl = null;
        try {
            pl = new AnnotationLine(line);
        } catch (InvalidLineException ex) {
        }
        return pl;
    }

    private static PropertyLine getPropertyLine(String line) {
        PropertyLine pl = null;
        try {
            pl = new PropertyLine(line);
        } catch (InvalidLineException ex) {
        }
        return pl;
    }

    private static PropertyLine getPrimaryKeyLine(String line) {
        PrimaryKeyLine pl = null;
        try {
            pl = new PrimaryKeyLine(line);
        } catch (InvalidLineException ex) {
        }
        return pl;
    }

    private static ConstructorLine getConstructorLine(String line) {
        return null;
    }
}
