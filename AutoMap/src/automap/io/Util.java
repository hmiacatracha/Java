package automap.io;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Util
{
    public static int readInt(int from,int to)
    {
        int out = 0;
        boolean done;
        do
            try
            {
                System.out.print("[" + from + "," + to + "] > ");
                Scanner s = new Scanner(System.in);
                out = s.nextInt();
                if(out < from || out > to)
                    throw new NoSuchElementException();
                done = true;
            }
            catch(NoSuchElementException e)
            {
                done = false;
            }
        while(!done);
        return out;
    }
    public static String readString()
    {
        System.out.print("> ");
        Scanner s = new Scanner(System.in);
        String out = s.nextLine();
        if(out == null)
            out = "";
        return out;
    }
}
