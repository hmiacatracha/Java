package automap.parser;

/**
 *
 * @author hmia
 */
public class AnnotationLine extends Line {

    private final String annotation;

    public AnnotationLine(String line) throws NoValidLineException {
        super(line);
        if (super.size() != 1 || super.getToken(0).startsWith("//@H")) {
            throw new NoValidLineException("Annotation no valid exception");
        }
        annotation = super.getToken(0);
    }

    public String getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
