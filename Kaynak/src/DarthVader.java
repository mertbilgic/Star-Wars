public class DarthVader extends Karakter {
    
    public DarthVader(int x, int y, String Karakter_adi, String tur) {
        super(x, y, Karakter_adi, tur);
    }

    @Override
    public int findShortestPath(int[][] mat, int[][] visited, int y2, int x2, int y1, int x1, int min_dist, int dist) {
        
        int KX=x2;
        int KY=y2;
        int syc=0;
        
        AnaEkran.path.add(getX());
        AnaEkran.path.add(getY());
        
        while(KX!=x1||KY!=y1){
            syc++;
            
            if(KY>=0){
                if(KY!=y1&&KY<y1){
                    KY++;
                    AnaEkran.path.add(KY);
                    AnaEkran.path.add(KX);
                }
                 else if(KY!=y1&&KY>y1) {
                    KY--;
                    AnaEkran.path.add(KY);
                    AnaEkran.path.add(KX);
                 }
            }
            if(KX>=0){
                if(KX>=0&&KX!=x1&&KX<x1){
                    KX++;
                    AnaEkran.path.add(KY);
                    AnaEkran.path.add(KX);
                }
           else if(KX>=0&&KX!=x1&&KX>x1){
                    KX--;
                    AnaEkran.path.add(KY);
                    AnaEkran.path.add(KX);
                }
           }         
           if(KX==x1&&KY==y1){
               break;
           }      
       }
        
       AnaEkran.sa=AnaEkran.path.toString();
       AnaEkran.min_distK=syc;
       
       return syc;
    }
    
    
    
}
