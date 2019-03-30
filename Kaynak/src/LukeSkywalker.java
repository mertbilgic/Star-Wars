public class LukeSkywalker extends Karakter {
    static int can_sayisi;
    public LukeSkywalker(int x, int y, String Karakter_adi, String tur,int can_sayisi) {
        super(x, y, Karakter_adi, tur);
        this.can_sayisi=can_sayisi;
    }

    public int getCan_sayisi() {
        return can_sayisi;
    }

    public void setCan_sayisi(int can_sayisi) {
        this.can_sayisi = can_sayisi;
    }
    
    
}

