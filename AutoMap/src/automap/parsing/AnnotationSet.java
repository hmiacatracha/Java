package automap.parsing;

/**
 *
 * @author hmia
 */
public class AnnotationSet {
    
    private AnnotationLine annotation;
    private Class<? extends Line> linea;

    public AnnotationSet(AnnotationLine annotation, Class<? extends Line> linea) {
        this.annotation = annotation;
        this.linea = linea;
    }

    public AnnotationLine getAnnotation() {
        return annotation;
    }

    public void setAnnotation(AnnotationLine annotation) {
        this.annotation = annotation;
    }

    public Class<? extends Line> getLinea() {
        return linea;
    }

    public void setLinea(Class<? extends Line> linea) {
        this.linea = linea;
    }
}
