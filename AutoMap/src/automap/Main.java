/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hmia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length == 0) {
            DirectoryAutoMap directory = new DirectoryAutoMap("/home/hmia/NetBeansProjects/Java/maven-app");
            File[] list = directory.getRecursiveDirectoryContent();
            for (File f : list) {
                try {
                    System.out.println("archivos encontrados => " + f.getCanonicalPath());
                } catch (IOException ex) {

                }
            }
            /*try {
             Files.find(Paths.get("/home/hmia/NetBeansProjects/Java/maven-app"),
             Integer.MAX_VALUE,
             (filePath, fileAttr) -> fileAttr.isRegularFile())
             .forEach(System.out::println);
             } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }*/

        } else if (args.length == 1) {
            DirectoryAutoMap directory = new DirectoryAutoMap(args[0]);
        }
    }

}
