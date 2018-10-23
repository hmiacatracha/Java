/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap;

import automap.io.ConsoleMenu;
import automap.io.Util;
import java.io.File;

/**
 *
 * @author hmia
 */
public class AutoMap {

    private static String projectURL;
    private static File[] filesToMap;

    public static void main(String[] args) throws InterruptedException {
        init();
        boolean exit = false;
        do {
            switch (ConsoleMenu.exec("Define url project (current = " + projectURL + ")?Analize project?Generate Hibernate mappings for project")) {
                case ConsoleMenu.EXIT: {
                    ConsoleMenu.printClose();
                    exit = true;
                    break;
                }
                case 1: {
                    boolean isValid = false;
                    do {
                        System.out.println("Write or paste a valid URL:\n");
                        projectURL = Util.readString();
                        File f = new File(projectURL);
                        if (f.exists() && !f.isDirectory()) {
                            isValid = true;
                        }
                    } while (!isValid);
                    ConsoleMenu.printJump();
                    break;
                }
                case 2: //Analize project
                {
                    System.out.println("Analize project ....");
                    analizeProject();
                    System.out.println("Finish");
                    break;
                }
                case 3: //Generate Hibernate mappings for project
                {
                    System.out.println("Generate Hibernate mappings for project project ....");
                    System.out.println("Finish");
                    break;
                }
            }
        } while (!exit);
    }

    private static void init() {
        ConsoleMenu.setAppName("auto map");
        projectURL = ".";
    }

    private static void analizeProject() {
        File f = new File(projectURL);
        filesToMap = IOHandlerDir.getContent(f, "java");
        IOHandlerDir.displayContent(f, "java");
    }    
}
