package automap.io;

public class ConsoleMenu {

    public static final int EXIT = -1;

    /**
     * Define el nombre de la aplicación, para que aparezca en la cabecera del
     * menú.
     *
     * @param name nombre de la aplicación
     */
    public static void setAppName(String name) {
        header = buildHeader(name);
    }

    /**
     * Construye y ejecuta un menú numerado, dado por sus opciones.<br/>
     * La opción de salida es autogenerada y se identifica por la constante
     * <b>EXIT</b>.
     *
     * @param opts opciones separadas por "/"
     *
     * @return selección del usuario
     */
    public static int exec(String opts) {
        if (firstPrint) {
            firstPrint = false;
            System.out.println(jump);
        }
        int sel, nOpts = opts.split("\\?").length + 1;
        System.out.println(header + buildFace(opts));
        sel = Util.readInt(1, nOpts);
        if (sel == nOpts) {
            sel = EXIT;
        }
        System.out.println(jump);
        return sel;
    }

    /**
     * Imprime el mensaje de salida (finalización de la interacción con el menú)
     */
    public static void printClose() {
        System.out.print(jump + header + "Closing... ");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ex) {
            //ignore
        }
    }

    /**
     * Imprime un salto grande de línea
     */
    public static void printJump() {
        System.out.println(jump);
    }

    private static boolean firstPrint = true;
    private static final String jump = buildJump();
    private static String header = buildHeader("app");

    private static String buildJump() {
        StringBuilder j = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            j.append("\n");
        }
        return j.toString();
    }

    private static String buildFace(String opts) {
        StringBuilder face = new StringBuilder();
        String[] o = opts.split("\\?");
        for (int i = 0; i < o.length; i++) {
            face.append("[").append(i + 1).append("] ").append(o[i]).append("\n");
        }
        face.append("[").append(o.length + 1).append("] Exit\n");
        return face.toString();
    }

    private static String buildHeader(String appName) {
        StringBuilder h = new StringBuilder("\n\n");
        for (int c = 0; c < appName.length() + 4; c++) {
            h.append("=");
        }
        h.append("\n| ").append(appName.toUpperCase()).append(" |\n");
        for (int c = 0; c < appName.length() + 4; c++) {
            h.append("=");
        }
        return h.append("\n\n").toString();
    }

}
