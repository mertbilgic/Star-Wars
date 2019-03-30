public class MasterYoda extends Karakter{
    static float can_sayisi;
    public MasterYoda(int x, int y, String Karakter_adi, String tur,float can_sayisi) {
        super(x, y, Karakter_adi, tur);
        this.can_sayisi=can_sayisi;
    }


    public float getCan_sayisi() {
        return can_sayisi;
    }

    public void setCan_sayisi(float can_sayisi) {
        this.can_sayisi = can_sayisi;
    }
    
    
}