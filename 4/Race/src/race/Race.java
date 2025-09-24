package race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Race {

    public static void main(String[] args){
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Add meg a file nevét: ");
        String s = br.readLine();
        Verseny v = new Verseny(s);
         //v.kiir();
        System.out.println("----------------------");
        v.lebenyolit();
        v.megoldasKiir();
        }catch(IOException e){
            System.out.println("Rossz file név!");
        }
    }
}
