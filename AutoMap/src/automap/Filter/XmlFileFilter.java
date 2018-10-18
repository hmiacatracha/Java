/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.Filter;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author hmia
 */
public class XmlFileFilter implements FileFilter {

    private final String[] okFileExtensions = new String[]{"xml"};
    
    public boolean accept(File file) {
        for (String extension : okFileExtensions) {
            System.out.println("filter =>" + file.getName().toLowerCase());
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

}
