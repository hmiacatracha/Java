package automap.parsing;

import automap.exceptions.InvalidLineException;
import automap.IOHandler;
import automap.exceptions.NoPersistenteClassException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 *
 * @author hmia
 */
public class PersistenteClass {

    private ClassLine className;
    private PackLine packName;

    /*properties and relations */
    private PrimaryKeyLine id;
    private HashSet<PropertyLine> simpleProperties = new HashSet<>();
    private HashSet<ColectionValuesLine> collectionOfValues = new HashSet<>();
    private HashSet<ManyToOneRelationLine> manyToOneRelations = new HashSet<>();
    private HashSet<ManyToManyRelationLine> manyToManyRelations = new HashSet<>();
    private HashSet<OneToManyRelationLine> oneToManyRelations = new HashSet<>();
    private HashSet<OneToOneRelationLine> oneToOneRelations = new HashSet<>();

    /* annotation, constructors and methods*/
    private HashSet<ConstructorLine> constructor = new HashSet<>();
    private HashSet<MethodLine> methods = new HashSet<>();

    /*Messages list fo errors and warnigs */
    private HashSet<String> listadoDeErrores = new HashSet<>();
    private HashSet<String> listadoDeWarnings = new HashSet<>();
    int num_annotation = 0;

    public PersistenteClass(File f) throws FileNotFoundException, NoPersistenteClassException {
        String[] lines = IOHandler.readFile(f);
        classifyTheLines(lines);

        /*No es un fichero de clase */
        if (className != null && packName != null) {
            throw new NoPersistenteClassException(f.getName());
        }

        /*NO es una clase persistente porque puede que no tenga anotaciones o las tenga incorrectas */
        if (!isAPersistenteClass()) {
            throw new NoPersistenteClassException(f.getName(), listadoDeErrores);
        }
    }

    public PackLine getPackName() {
        return packName;
    }

    public ClassLine getClassName() {
        return className;
    }

    public PrimaryKeyLine getId() {
        return id;
    }

    public HashSet<PropertyLine> getProperties() {
        return simpleProperties;
    }

    public HashSet<ColectionValuesLine> getCollectionOfValues() {
        return collectionOfValues;
    }

    public HashSet<ManyToOneRelationLine> getManyToOneRelations() {
        return manyToOneRelations;
    }

    public HashSet<ManyToManyRelationLine> getManyToManyRelations() {
        return manyToManyRelations;
    }

    public HashSet<OneToManyRelationLine> getOneToManyRelations() {
        return oneToManyRelations;
    }

    public HashSet<OneToOneRelationLine> getOneToOneRelations() {
        return oneToOneRelations;
    }

    public boolean hasWarnigs() {
        verifyWarnings();
        return listadoDeWarnings.size() > 0;
    }

    public void showWarnigs() {
        listadoDeWarnings.stream().forEach((s) -> {
            System.out.println(s);
        });
    }

    private void verifyWarnings() {
        int numberOfConstructorByDefault = 0;
        while (constructor.iterator().hasNext()) {
            ConstructorLine c = constructor.iterator().next();
            if ((c.getModifier().equalsIgnoreCase("public")) && (c.getNumberOfParams() == 0)) {
                numberOfConstructorByDefault++;
            }
        }
        if (numberOfConstructorByDefault == 0 || numberOfConstructorByDefault > 1) {
            addWarning("Se debe de añadir un constructor por defecto en la clase persistente");
        }
    }

    private boolean isAPersistenteClass() {
        return listadoDeErrores.size() == 0;
    }

    private void classifyTheLines(String[] l) {
        for (int i = 0; i < l.length - 1; i++) {
            if (isPackLine(l[i])) {
                if (packName != null) {
                    addError("La clase persistente cuenta con más de un packete");
                }
                packName = getPackLine(l[i]);
            }
            if (isClassLine(l[i])) {
                if (className != null) {
                    addError("La clase persistente cuenta con más de un nombre de clase");
                }
                className = getClassLine(l[0]);
            }
            if (isConstructorLine(l[i])) {
                constructor.add(getConstructorLine(l[0]));
            }
            if (isAnnotationLine(l[i])) {
                AnnotationLine annotation = getAnnotationLine(l[i]);
                analizarAnotacion(annotation, l[i + 1]);
                i++;
                num_annotation++;
            }
        }
        if (num_annotation == 0) {
            addError("La clase no es persistente porque no cuenta con ninguna anotación");
        }
    }

    private void analizarAnotacion(AnnotationLine annotation, String linea) {
        switch (annotation.getAnnotation()) {
            case "HPK":
                if (isPrimaryKeyLine(linea)) {
                    if (id != null) {
                        addError("La clase cuenta con más de una clave primaria");
                    }
                    id = getPrimaryKeyLine(linea);
                } else {
                    addError("La anotacion \\\\@HPK is clave primaria, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            case "HC":
                if (isColetionOfValuesLine(linea)) {
                    collectionOfValues.add(getColectionValuesLine(linea));
                } else {
                    addError("La anotacion is para coleccion de valores, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            case "H11":
                if (isOneToOneRelationLine(linea)) {
                    oneToOneRelations.add(getOneToOneRelationLine(linea));
                } else {
                    addError("La anotacion is para relaciones de uno a muchos, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            case "H1M":
                if (isOneToManyRelationLine(linea)) {
                    oneToManyRelations.add(getOneToManyRelationLine(linea));
                } else {
                    addError("La anotacion \\\\@HP1M es para relaciones de uno a muchos, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            case "HM1":
                if (isManyToOneRelationLine(linea)) {
                    manyToOneRelations.add(getManyToOneRelationLine(linea));
                } else {
                    addError("La anotacion \\\\@HPM1 es para relaciones de muchos a uno, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            case "HMM":
                if (isManyToManyRelationLine(linea)) {
                    manyToManyRelations.add(getManyToManyRelationLine(linea));
                } else {
                    addError("La anotacion \\\\@HP1M es para relaciones de muchos a muchos, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            case "H":
                if (isPropertyLine(linea)) {
                    simpleProperties.add(getPropertyLine(linea));
                } else {
                    addError("La anotacion \\\\@H es una propiedad persistente, "
                            + "sin embargo la linea => " + linea + " no cumple con el formato");
                }
                break;
            default:
                addError("Se ha añadido la anotacion " + annotation.getAnnotation() + ", "
                        + "sin embargo no es una anotación válida");
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

    private static boolean isColetionOfValuesLine(String line) {
        return getColectionValuesLine(line) != null;
    }

    private static boolean isOneToOneRelationLine(String line) {
        return getOneToOneRelationLine(line) != null;
    }

    private static boolean isOneToManyRelationLine(String line) {
        return getOneToManyRelationLine(line) != null;
    }

    private static boolean isManyToOneRelationLine(String line) {
        return getManyToManyRelationLine(line) != null;
    }

    private static boolean isManyToManyRelationLine(String line) {
        return getManyToManyRelationLine(line) != null;
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

    private static PrimaryKeyLine getPrimaryKeyLine(String line) {
        PrimaryKeyLine pl = null;
        try {
            pl = new PrimaryKeyLine(line);
        } catch (InvalidLineException ex) {
        }
        return pl;
    }

    private static ColectionValuesLine getColectionValuesLine(String line) {
        ColectionValuesLine l = null;
        try {
            l = new ColectionValuesLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static OneToOneRelationLine getOneToOneRelationLine(String line) {
        OneToOneRelationLine l = null;
        try {
            l = new OneToOneRelationLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static OneToManyRelationLine getOneToManyRelationLine(String line) {
        OneToManyRelationLine l = null;
        try {
            l = new OneToManyRelationLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static ManyToOneRelationLine getManyToOneRelationLine(String line) {
        ManyToOneRelationLine l = null;
        try {
            l = new ManyToOneRelationLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static ManyToManyRelationLine getManyToManyRelationLine(String line) {
        ManyToManyRelationLine l = null;
        try {
            l = new ManyToManyRelationLine(line);
        } catch (InvalidLineException ex) {
        }
        return l;
    }

    private static ConstructorLine getConstructorLine(String line) {
        return null;
    }

    private void addError(String message) {
        listadoDeErrores.add(message);
    }

    private void addWarning(String message) {
        listadoDeWarnings.add(message);
    }

}
