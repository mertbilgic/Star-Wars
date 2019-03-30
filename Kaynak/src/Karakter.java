import java.util.ArrayList;
import java.util.List;

public class Karakter extends Lokasyon{
    
    private String Karakter_adi;
    private String tur;
        
        public int min_distK2 =100;
        public int degisim_k =0;
        private static String k_tutucu="";
	private static final int M = 11;
	private static final int N = 14;
        static String s="";
       
       static List<Integer>path2= new ArrayList<Integer>();
       

    
    
    public Karakter(int x, int y,String Karakter_adi,String tur) {
        super(x, y);
        this.Karakter_adi=Karakter_adi;
        this.tur=tur;
    }

    public String getKarakter_adi() {
        return Karakter_adi;
    }

    public void setKarakter_adi(String Karakter_adi) {
        this.Karakter_adi = Karakter_adi;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }
   
    	
	// Mevcut konumdan (x, y) konumuna gidip gelmeyeceğini kontrol edin.
	// hücre, 0 değerine sahipse veya önceden ziyaret edilmişse false döndürür
	private  boolean güvenliMi(int mat[][], int visited[][], int x, int y)
	{      
		return !(mat[x][y] == 0 || visited[x][y] != 0);
	}

	// geçerli bir konum değilse, false döndür
	private boolean alanKontrol(int x, int y)
	{   
		return (x < 11 && y < 14 && x >= 0 && y >= 0);
	}

	// Kaynak hücreden bir Matrix matındaki Olası En Kısa Rotayı Bul (0, 0)
        // hedef hücreye (x, y)
        // 'min_dist' kaynaktan hedefe en uzun yolun uzunluğunu saklar
        // şu ana kadar bulundu ve 'dist' kaynak hücreden yol uzunluğunu koruyor
        // mevcut hücre (i, j)
        
	public  int findShortestPath(int mat[][], int[][] ziyaret,int i, int j, int x, int y, int min_adım, int adım)		 
	{       
                  if(min_adım!=AnaEkran.min_distK){
                    AnaEkran.sa=AnaEkran.path.toString();
                    min_distK2=min_adım;
                    AnaEkran.min_distK=min_adım;
                    
                }
                
                if(degisim_k>adım){
                    int fark=degisim_k-adım;
                   for(int t=0;t<fark;t++){
                       AnaEkran.path.remove(adım*2);
                       AnaEkran.path.remove(adım*2);
                   }
                    degisim_k=adım;
                }
                
               AnaEkran.path.add(i);
               AnaEkran.path.add(j);
               
		if (i == x && j == y)
		{       
                    if(adım>min_adım){
                        //path.clear();
                    }
			return Integer.min(adım, min_adım);
		}

		// ziyaret edilen (i, j) hücreyi ayarla
		if(x==i&&y==j)
                        ziyaret[i][j] = 0;
                else{
                    ziyaret[i][j] = 1;
                }
		
                // sol hücreye git
		if (alanKontrol(i, j - 1) && güvenliMi(mat, ziyaret, i, j - 1)) {
                        degisim_k++;
                        // path.add(i);
                        // path.add(j);
			min_adım = findShortestPath(mat, ziyaret, i, j - 1, x, y,min_adım, adım + 1);
		
                        
		}
                // üst hücreye git
		if (alanKontrol(i - 1, j) && güvenliMi(mat, ziyaret, i - 1, j)) {
                        degisim_k++;
                        // path.add(i);
                        // path.add(j);
			min_adım = findShortestPath(mat, ziyaret, i - 1, j, x, y,min_adım, adım + 1);
										
		}

		
                // alt hücreye git
		if (alanKontrol(i + 1, j) && güvenliMi(mat, ziyaret, i + 1, j)) {
                        degisim_k++;
                        // path.add(i);
               // path.add(j);
			min_adım = findShortestPath(mat, ziyaret, i + 1, j, x, y,min_adım, adım + 1);
										
		}

		// sağ hücreye git
		if (alanKontrol(i, j + 1) && güvenliMi(mat, ziyaret, i, j + 1)) {
                        degisim_k++;
                       //  path.add(i);
               // path.add(j);
			min_adım = findShortestPath(mat, ziyaret, i, j + 1, x, y,min_adım, adım + 1);
										
		}

		
                

		// Backtrack - (i, j) ziyaret edilen matristen kaldır
		ziyaret[i][j] = 0;
          
                
		return min_adım;
	}
        
    
}




