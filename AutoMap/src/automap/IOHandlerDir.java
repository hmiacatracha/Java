package automap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hmia
 */
public class IOHandlerDir {

    /**
     * Display content of a directory
     *
     * @param directory
     */
    public static void displayContent(File directory) {
        displayFiles(getContent(directory));
    }

    /**
     * Display content of a directory with a string filter
     *
     * @param directory
     * @param stringFilter
     */
    public static void displayContent(File directory, String stringFilter) {
        displayFiles(getContent(directory, stringFilter));
    }

    /**
     * Display content of a directory with a list of string filters
     *
     * @param directory
     * @param stringFilters
     */
    public static void displayContent(File directory, String[] stringFilters) {
        displayFiles(getContent(directory, stringFilters));
    }

    /**
     * Display content of a directory with a filter
     *
     * @param directory
     * @param filter
     */
    public static void displayContent(File directory, FileFilter filter) {
        displayFiles(getContent(directory, filter));
    }

    /**
     * Get a list of file of a directory
     *
     * @param directory
     * @return list of files
     */
    public static File[] getContent(File directory) {
        return getContent(directory, (File pathname) -> true);
    }

    /**
     * Get a list of file of a directory
     *
     * @param directory
     * @param stringFilter
     * @return
     */
    public static File[] getContent(File directory, String stringFilter) {
        String[] stringFilters = {stringFilter};
        return getContent(directory, stringFilters);
    }

    /**
     *
     * @param directory
     * @param stringFilters
     * @return
     */
    public static File[] getContent(File directory, String[] stringFilters) {
        FileFilter filter;
        filter = (File pathname) -> {
            if (pathname.isDirectory()) {
                return true;
            }
            String base = pathname.getName().toLowerCase();
            for (String s : stringFilters) {
                if (base.endsWith(s)) {
                    return true;
                }
            }
            return false;
        };
        return getContent(directory, filter);
    }

    /**
     *
     * @param directory
     * @param filter
     * @return
     */
    public static File[] getContent(File directory, FileFilter filter) {
        Set<File> fileList = new HashSet<>();
        getContent(directory, fileList, filter);
        return fileList.toArray(new File[fileList.size()]);
    }

    private static void getContent(File directory, Set<File> found, FileFilter filter) {
        for (File file : directory.listFiles(filter)) {
            if (file.isDirectory()) {
                getContent(file, found, filter);
            } else {
                found.add(file);
            }
        }
    }

    private static void displayFiles(File[] list) {
        for (File f : list) {
            try {
                System.out.println(f.getCanonicalFile());
            } catch (IOException ex) {

            }
        }
    }

    /**
     * File
     * Read a file
     * @param  f
     * @return
     * @throws FileNotFoundException
     */
    public static String[] readFile(File f) throws FileNotFoundException {
        List<String> l = new ArrayList<>();
        StringBuilder contentBuilder = new StringBuilder();
        FileReader fr = new FileReader(f);
        try (BufferedReader br = new BufferedReader(fr)) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                l.add(sCurrentLine);
            }
        } catch (IOException e) {
        }
        return l.toArray(new String[l.size()]);
    }

    /**
     * Write a file with string content
     * @param path
     * @param fileContent
     * @throws IOException 
     */
    public static void writeFile(String path, String fileContent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(fileContent);
        }
    }        
}
