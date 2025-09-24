package race;

public class Szivacs extends Leny {
    private final String Lenyfajta = "szivacs";
    private final int maxvizmennyiseg = 20;

    /**
     * Szivacs lény osztálya.
     * Konstruktor a szülő osztály konstruktorát felhasználván,
     * létrehozza a szivacs lényt.
     * @param nev
     * @param vizmennyiseg
     */
    public Szivacs(String nev, int vizmennyiseg) {
        super(nev, vizmennyiseg);
    }
    
    /**
     *Az adott lény mit csinál a napos időben a verseny alatt.
     * történik egy vizsgálat, hogy életben van-e még, ha igen akkor vizet fogyaszt és lép.
     */
    @Override
    public void napos() {
        if(this.vizmennyiseg != 0){
            this.vizmennyiseg -= 4;
            this.megtetttavolsag += 0;
        }
        
        
    }

    /**
     *Az adott lény mit csinál a felhos időben a verseny alatt.
     * történik egy vizsgálat, hogy életben van-e még, ha igen akkor vizet fogyaszt és lép.
     */
    @Override
    public void felhos() {
        if(this.vizmennyiseg != 0){
            this.vizmennyiseg += 1;
            this.megtetttavolsag += 1;
        }
        
        
    }

    /**
     *Az adott lény mit csinál a esős időben a verseny alatt.
     * történik egy vizsgálat, hogy életben van-e még, ha igen akkor vizet fogyaszt és lép.
     */
    @Override
    public void esos() {
        if(this.vizmennyiseg != 0){
            this.vizmennyiseg += 6;
            this.megtetttavolsag += 3;
        }
       
        
    }
    
    /**
     * Megadja, hogy ez egy Szivacs lény.
     * @return Lenyfajta
     */
    @Override
    public String milyenvagy() {
        return Lenyfajta;
    }
    
    
}
