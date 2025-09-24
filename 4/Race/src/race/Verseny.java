package race;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Verseny {
    private Collection<Leny> lenyek = new ArrayList<>();
    private String napok;
    private int max;
    private String nev;
    private int a = -1;
    private boolean olvasott = false;
    
    /**
     *A Verseny konstruktora,ahol a beolvasás is megtörténik 
     * egy fájlból egy Collection-be (Listába).
     * @param fileName
     * @throws IOException
     */
    public Verseny(String fileName) throws IOException{
        File file = new File(fileName);
        BufferedReader reader = null;
 
        try{
            reader = new BufferedReader(new FileReader(file));
            
            String line = null;
            String tmp;
            int szam;
            String fajta;
            while( (line = reader.readLine()) != null ){
                String[] tokens = line.split(" ");
                if(tokens.length > 1){
                tmp = tokens[0];
                fajta = tokens[1];
                szam = Integer.parseInt(tokens[2]);
                switch(fajta){
                    case "h": Leny h = new Homokjaro(tmp,szam);
                              lenyek.add(h);
                              break;
                    case "l": Leny l = new Lepegeto(tmp,szam);
                              lenyek.add(l);
                              break;
                    case "s": Leny s = new Szivacs(tmp,szam);
                              lenyek.add(s);
                              break;
                }
                }else{
                    if(a <= 0){
                      a = Integer.parseInt(tokens[0]);
                    }else{
                        napok = tokens[0];
                    }
                }   
            }
            reader.close();
            olvasott = true;
        }catch(FileNotFoundException e) {
            System.out.println("Rossz file név!");
        }
    }
    

    /**
     * Kiírás metódus, a beolvasott adatok ellenőrzésére/kiírására.
     * A beolvasott adatok egy Collectionbe kerülnek, amit egy ciklussal végigmenvén kiírásra kerülnek.
     */
    public void kiir(){
        System.out.println(Integer.toString(a));
        for(Leny x : lenyek){
            System.out.println(x);
        }
        System.out.println(napok);
    }
    
    /**
     * Maga a feladat, a lebonyolít, itt a beolvasott adatokban
     * naponként nevezvén a kódban találhatók, hogy eggyes napokon milyen volt az időjárás.
     * Ezen a Stringet, miután karakterekre lett bonva, végigmenvén egy ciklussal, majd ezen belül egy másik ciklussal
     * ami a Lények Collection-ján megy végig,
     * és minden karakternél ami egy napot szimbolizás az adott jelentésnek megfelelően reagálnak rá a lények, és haladnak a versenyben.
     * Switch- case segítségével történik meg, hogy eggyes karakterek ilyen tevékenységet is hajtatnak végre.
     * n-napos, f- felhos, e-esos.
     * Ezt követően egy Maximum kiválasztás történik, hogy ki tette meg a versenyben a legnagyobb távolságot, egy életben van-e vizsgálattal egybekötve.
     */
    public void lebenyolit(){
        if(olvasott == true){
        for(int i = 0; i < napok.length(); i++){
            char c = napok.charAt(i);
            for(Leny x : lenyek){
                switch(c){
                    case 'n': x.napos();
                    break;
                    
                    case 'f': x.felhos();
                    break;
                    
                    case 'e': x.esos();
                    break;
                }
            }
       }
        max = 0;
        nev = "";
       for(Leny x: lenyek){
           if( x.elealeny() && x.megtetttav()> max ){
               max = x.megtetttav();
               nev = x.getNev();
           }
       }
    }
    }
    
    /**
     * Maga a megoldás kiírása.
     * A győztes által megtett távolság(maximum).
     * A győztes lény neve.
     */
    public void megoldasKiir(){
        if(olvasott == true ){
        System.out.println("A gyöztes átlal megtett táv: ");
        System.out.println(Integer.toString(max));
        System.out.println("A győztes neve: ");
        System.out.println(nev);
        
    }
    }
    
    
}
