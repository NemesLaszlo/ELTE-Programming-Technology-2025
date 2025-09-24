package race;

public abstract class Leny {
    protected final String nev;
    protected int  vizmennyiseg;
    protected int megtetttavolsag;

    /**
     * Absztrakt olyatály konstruktora.
     * @param nev
     * @param vizmennyiseg
     */
    public Leny(String nev, int vizmennyiseg) {
        this.nev = nev;
        this.vizmennyiseg = vizmennyiseg;
        this.megtetttavolsag = 0;
    }
    
    /**
     * Publikus absztrakt metódusok, melyek felülírásra kerülnek,
     * a különbőző származtatott osztályokban
     * Itt a napos időben történő cselekmények vannak összefoglalva.
     */
    public abstract void napos();

    /**
     *Felsős időn történő változások, mozgások, vízmennyiseg fogyások.
     */
    public abstract void felhos();

    /**
     *Esős időben történő változásai a lénynek/lényeknek.
     */
    public abstract void esos();

    /**
     *Megadja a Lény fajtáját.
     * @return Lenyfajta, a származtatott osztályokból.
     */
    public abstract String milyenvagy();
    
    /**
     * Megadja, hogy az adott Lény életben van-e.
     * @return logikai érték
     */
    public boolean elealeny(){
        return this.vizmennyiseg != 0;
    }
    
    /**
     * Megadja a lény nevét.
     * @return nev
     */
    public String getNev(){
        return this.nev;
    }
    
    /**
     * Megadja a Lény által megtett távot.
     * @return távolság
     */
    public int megtetttav(){
        return this.megtetttavolsag;
    }

    @Override
    public String toString() {
        return  nev +" "+ vizmennyiseg;
    }
    
    
    
    
    
    
}
