public class KarakterUret {
    
    public Karakter karakter_uret(String karakter_adı){
        
      if(karakter_adı.equals("MasterYoda")){
          return new MasterYoda(5,6,"MasterYoda","iyi",6);
      }
      else if(karakter_adı.equals("LukeSkywalker")){
          
          return new LukeSkywalker(5,6,"LukeSkywalker","iyi",3);
      }
      else{
          return null;
      }
      
    }
    public Karakter karakter_uret2(String karakter_adı,int indis){
     if(karakter_adı.equals("Stormtrooper")){
          return new Stormtrooper(AnaEkran.kapiY[indis],AnaEkran.kapiX[indis],"Stormtrooper","kötü");
      }
     else if(karakter_adı.equals("DarthVader")){
          return new DarthVader(AnaEkran.kapiY[indis],AnaEkran.kapiX[indis],"DarthVader","kötü");
      }
     else if(karakter_adı.equals("KYLOREN")){
          return new KyloRen(AnaEkran.kapiY[indis],AnaEkran.kapiX[indis],"KYLOREN","kötü");
      }
     else{
          return null;
      }
}
    
    
    
}