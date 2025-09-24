package race;

public class Homokjaro extends Leny {
    private final String Lenyfajta = "homokjáró";
    private final int maxvizmennyiseg = 8;

    /**
     * Homokjaro lény osztálya.
     * Konstruktor a szülő osztály konstruktorát felhasználván,
     * létrehozza a homokjáró lényt.
     * @param nev
     * @param vizmennyiseg
     */
    public Homokjaro(String nev, int vizmennyiseg) {
        super(nev, vizmennyiseg);
    }
    
    /**
     * Az adott lény mit csinál a napos időben a verseny alatt.
     * történik egy vizsgálat, hogy életben van-e még, ha igen akkor vizet fogyaszt és lép.
     */
    @Override
    public void napos() {
        if(this.vizmennyiseg != 0){
            this.vizmennyiseg -= 1;
            this.megtetttavolsag += 3;
        }
        
    }

    /**
     * Az adott lény mit csinál a felhos időben a verseny alatt.
     * történik egy vizsgálat, hogy életben van-e még, ha igen akkor vizet fogyaszt és lép.
     */
    @Override
    public void felhos() {
        if(this.vizmennyiseg != 0){
            this.vizmennyiseg -= 0;
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
            this.vizmennyiseg += 3;
            this.megtetttavolsag += 0;
        }
        
    }

    /**
     * Megadja, hogy ez egy Homokjáró lény.
     * @return Lenyfajta
     */
    @Override
    public String milyenvagy() {
        return Lenyfajta;
    }
    
}
