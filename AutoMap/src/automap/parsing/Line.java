package automap.parsing;

/**
 *
 * @author hmia
 */
public class Line {

    private final String[] tokens;

    public Line(String line) {
        String l = line.trim();
        while (l.contains("  ")) {
            l = l.replaceAll("  ", " ");
        }
        l = l.replaceAll("\t", "");
        this.tokens = l.split(" ");
    }

    public int size() {
        return tokens.length;
    }

    public String getToken(int index) {
        return tokens[index];
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (String s : tokens) {
            st.append(s).append(" ");
        }
        return st.toString();
    }
}
