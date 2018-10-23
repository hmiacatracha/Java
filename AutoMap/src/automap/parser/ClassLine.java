package automap.parser;

/**
 *
 * @author hmia
 */
public class ClassLine extends Line {

    private String modifier;
    private String modifierOptional;
    private String name;

    public ClassLine(String line) throws NoValidLineException {
        super(line);

        if (!isValid()) {
            throw new NoValidLineException("Not persistente class line Exception");
        }

        if (super.size() >= 3 && super.getToken(0).equalsIgnoreCase("public")
                && super.getToken(1).equalsIgnoreCase("class")) {
            modifier = super.getToken(0);
            modifierOptional = "";
            name = super.getToken(2).replaceAll("\\{", "");
        }

        if (super.size() > 3 && super.getToken(0).equalsIgnoreCase("public")
                && super.getToken(1).equalsIgnoreCase("abstract") && super.getToken(2).equalsIgnoreCase("class")) {
            modifier = super.getToken(0);
            modifierOptional = super.getToken(1);
            name = super.getToken(3).replaceAll("\\{", "");
        }

    }

    private boolean isValid() {
        if (super.size() >= 3 && super.getToken(0).equalsIgnoreCase("public")
                && super.getToken(1).equalsIgnoreCase("class")) {
            return true;
        }
        
        return super.size() > 3 && super.getToken(0).equalsIgnoreCase("public")
                && super.getToken(1).equalsIgnoreCase("abstract") && super.getToken(2).equalsIgnoreCase("class");
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
