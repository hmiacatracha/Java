package automap.parsing;

import automap.exceptions.InvalidLineException;

/**
 *
 * @author hmia
 */
public class AnnotationLine extends Line {

    private final String annotation;

    public AnnotationLine(String lineAnnotation) throws InvalidLineException {
        super(lineAnnotation);
        if (super.size() != 1 || super.getToken(0).startsWith("//@H")) {
            throw new InvalidLineException("Annotation no valid exception");
        }
        annotation = super.getToken(0).replace("//@", "").toUpperCase();
    }

    public String getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
