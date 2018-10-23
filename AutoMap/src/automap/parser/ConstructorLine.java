package automap.parser;

/**
 *
 * @author hmia
 */
public class ConstructorLine extends Line {

    private String modifier;
    private String name;
    private String[] params;

    public ConstructorLine(String line) {
        super(line);
        /*if ((super.getToken(0).equalsIgnoreCase("public") && super.size() == 2)
         || (super.size() == 1 && super.getToken(1).contains("(") && super.getToken(1).contains(")"))) {
         }*/
        params = getParams(line);
    }

    private static String[] getParams(String line) {
        String[] part1 = line.split("\\(");
        String[] params = line.split(",");
        for (String p : params) {
            System.out.println("hola");
        }
        return null;
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

    public void setName(String name) {
        this.name = name;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
