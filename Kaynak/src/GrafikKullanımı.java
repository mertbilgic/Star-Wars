import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GrafikKullanımı extends JPanel implements KeyListener,ActionListener{
    
    float can,can2;
    int min_dist,tus_k=0,z=AnaEkran.t;
    int[][] visited = new int[11][14];
    String[] Karakter={"","","","",""};
    static int c=0;
    int candir=0;
    int[] yolA=new int[80];
    private BufferedImage image,image2,image3,image4,image5,image6,image7;
    KarakterUret uret = new KarakterUret();
    Karakter iyiK=uret.karakter_uret(AnaEkran.iyi_karakter);
    Karakter[] kötüK = new Karakter[5];
    static  int[][] harita = new int[12][15];
    
    public void Kesitle(String[] a){
        for(int i=0;i<z;i++){
            Karakter[i]+=a[i];
            //System.out.println(Karakter[i]);
        }
    }
                  
    public void esitle(int harita2[][]){          
        for(int i=0;i<11;i++){
            for(int j=0;j<14;j++){           
                GrafikKullanımı.harita[i][j]=harita2[i][j];             
            }        
        }
    }
    //Can sistemi bu kısımda kontrol altına alınıyor eğer karakter yakalandıysa canını sazaltıyoruz eğer bittiysede oyunun bittiğini bildiren masej atıyoruz
    public void oyunBittiMi(){
        if(can>0){
            can--;
            AnaEkran.sa="";
            AnaEkran.path.clear();            
        }
        if(can==0){   
            JOptionPane.showMessageDialog(this,"İYİ KARAKTER KAYBETTİ");
            System.exit(0);    
            }
    }
    //İyi karakterin bitiş nokasınına ulaşıp ulaşmadığını kontrol ediyoruz.Ulaştıysa ulaştığına dair mesaj atıyoruz.
    public void kazandiMi(){
        if(iyiK.getX()==9&&iyiK.getY()==13){
            JOptionPane.showMessageDialog(this,"İYİ KARAKTER KAZANDI");
            System.exit(0);
        }
    }
    //Dosyada okuduğumuz verileri karakter yakalandığında sıfırlayabilmek için static şekilde ayarlardık bu sayade oyunun tur tur oluşmasını sağladık
     public  void OyunuSıfırla(){
          for(int p=0 ;p<z;p++){
               if(AnaEkran.kapi[p].equals("A")){
                  AnaEkran.kapiX[p]=0;
                  AnaEkran.kapiY[p]=5;
               }
               if(AnaEkran.kapi[p].equals("B")){
                  AnaEkran.kapiX[p]=4;
                  AnaEkran.kapiY[p]=0;
               }
               if(AnaEkran.kapi[p].equals("C")){
                  AnaEkran.kapiX[p]=12;
                 AnaEkran. kapiY[p]=0;
               }
               if(AnaEkran.kapi[p].equals("D")){
                 AnaEkran. kapiX[p]=13;
                  AnaEkran.kapiY[p]=5;
               }
               if(AnaEkran.kapi[p].equals("E")){
                  AnaEkran.kapiX[p]=4;
                  AnaEkran.kapiY[p]=10;
               }
               iyiK.setX(5);
               iyiK.setY(6);
               AnaEkran.sa="";
               AnaEkran.path.clear();
        }    
     }
   
    @Override
    public void paint(Graphics g) {  
        super.paint(g); 
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        candir=0;
        //JPanel üzerindeki can göstergesini oluşturmak için kullanıyoruz.
        for(int t=0;t<can;t++){
            g.fillRect(1100+candir*90,0, 70, 70);
            g.drawImage(image7,1100+candir*90,0,image7.getWidth()/(4),image7.getHeight()/(4),this);
            candir++;
        }
     
        for(int i=0;i<11;i++){
            for(int j=0;j<14;j++){
                
                if(AnaEkran.harita[i][j]==0) g.setColor(Color.white);
                else  g.setColor(Color.blue);
                
                g.fillRect(70*j,70*i,70,70);
            }
        
        }
        //Labirentin çıkısına kupa resmini eklemek için kullanıyoruz.
        g.drawImage(image,1000,620,image.getWidth()/2,image.getHeight()/2,this);
        
        //Çizdiğimiz karelerin belirgin olması için aralarda siyah çizgiler olmasını sağlıyoruz.
        g.setColor(Color.black);
        for(int i=0;i<11;i++){
            for(int j=0;j<14;j++){
                g.drawRect((j*70),(i*70), 70, 70);
            }
        }
        
      
        for(c=0;c<z;c++){
            //Karakterler dizi şekilinde kullanılarak gereksiz obje oluşumunun önüne geçildi.
            kötüK[c]=uret.karakter_uret2(Karakter[c],c);
            //Yollanan iyi ve kötü karakter komutlarına göre minimum ifadeyi bulan fonksyon   
            min_dist=kötüK[c].findShortestPath(harita, visited, kötüK[c].getX(),kötüK[c].getY(),iyiK.getX(),iyiK.getY(),Integer.MAX_VALUE, 0);
           //Alt kısımda bulduğumuz minimum değere ait verileri parçalıyoruz. 
            if(min_dist==AnaEkran.min_distK){}
            else AnaEkran.sa=AnaEkran.path.toString();
            AnaEkran.sa=AnaEkran.sa.replaceAll("\\s","");
            String[] result = AnaEkran.sa.split(",");
            yolA[0]=Character.getNumericValue(result[0].charAt(1));  
            
            int x;
            for (x=1; x<result.length-1; x++){
               yolA[x]= Integer.parseInt(result[x]);
            }
            
           String as =result[x];
           String ta="";
           for(int q=0;q<as.length();q++){
               if(as.charAt(q)!=']'){
                   ta+=as.charAt(q);
               }
           }
           yolA[x]=Integer.valueOf(ta);
          
           //En kısa yolu aldığımız kordinatlara göre çiziyoruz
           g.setColor(Color.red);
           for(int c =2;c<x-2;c+=2){
                g.fillRect(70*yolA[c+1],70*yolA[c],70,70);
           }
           
         
           //Oyunda olan karakterlerin resmini haritada gerekli kordinatlara ekliyoruz
           try{
            if(kötüK[c].getKarakter_adi().equals("Stormtrooper")){
             g.drawImage(image4,70*kötüK[c].getY(),70*kötüK[c].getX(),image4.getWidth()/(4),image4.getHeight()/(4),this);
              
            }
           if(kötüK[c].getKarakter_adi().equals("KYLOREN")){
              g.drawImage(image5,70*kötüK[c].getY(),70*kötüK[c].getX(),image5.getWidth()/(4),image5.getHeight()/(4),this);
              
            }
           if(kötüK[c].getKarakter_adi().equals("DarthVader")){
              g.drawImage(image6,70*kötüK[c].getY(),70*kötüK[c].getX(),image6.getWidth()/4,image6.getHeight()/4,this);
              
            }
           if(iyiK.getKarakter_adi().equals("MasterYoda")){
           g.drawImage(image2,70*iyiK.getY(),70*iyiK.getX(),image2.getWidth()/(4),image2.getHeight()/(4),this);
           }
           if(iyiK.getKarakter_adi().equals("LukeSkywalker")){
           g.drawImage(image3,70*iyiK.getY(),70*iyiK.getX(),image3.getWidth()/(2),image3.getHeight()/(2),this);
           }
           }
           catch(Exception e){
               
           }
          
             
       //Harita üzerinde kötü karakterlerin takibini sağladığımız kısım KYLOREN iki birim ilerdiği için ona özel bir durum yarattık.
       if(kötüK[c].getKarakter_adi().equals("KYLOREN")){
            
        if(min_dist==1){
            AnaEkran.kapiY[c]=iyiK.getX();
            AnaEkran.kapiX[c]=iyiK.getY();
        }
        else{
            AnaEkran.kapiY[c]=yolA[4];
        AnaEkran.kapiX[c]=yolA[5];
        }
      
        }
       else{
        AnaEkran.kapiY[c]=yolA[2];
        AnaEkran.kapiX[c]=yolA[3];
       }
        
       
        //Karakterlerin yakalanması ve oyunun tekrar başlaması burdaki koşul ile yapılıyor
        if(kötüK[c].getX()==iyiK.getX()&&kötüK[c].getY()==iyiK.getY()){
            oyunBittiMi();
            OyunuSıfırla();
            repaint();
            break;
        }
        
        //İyi karakterlerin kazanıp kazanmadığını bu fonksiyonla sağlıyoruz
        kazandiMi();
        
            System.out.println(kötüK[c].getKarakter_adi()+" adlı karakterin "+iyiK.getKarakter_adi()+" adlı karakteri yakalması için "+min_dist+" birim haraket etmesi gerekir");
            System.out.println("");
     
        //sa değişkenini ve path değişkenini statik olarak kulladığımız için tur sonunda sıfırlıyoruz.Diğer değerlerle çakışmasını engellemek için  
        AnaEkran.sa="";
        AnaEkran.path.clear();
        
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
}
    
    public GrafikKullanımı() {
        
         try {
            image =ImageIO.read(new FileImageInputStream( new File("kupa.png")));
            image2 =ImageIO.read(new FileImageInputStream( new File("masteryo.png")));
            image3 =ImageIO.read(new FileImageInputStream( new File("lukesky.png")));
            image4 =ImageIO.read(new FileImageInputStream( new File("stormtrooper.png")));
            image5 =ImageIO.read(new FileImageInputStream( new File("kylo_ren.png")));
            image6 =ImageIO.read(new FileImageInputStream( new File("DarthVader.png")));
            image7 =ImageIO.read(new FileImageInputStream( new File("can.png")));
        } catch (IOException ex) {
            Logger.getLogger(GrafikKullanımı.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Hangi iyi karakterin oluşcağını kontrol ediyoruz
        if(iyiK.getKarakter_adi().equals("MasterYoda")){
            can=MasterYoda.can_sayisi;
        }else{
            can=LukeSkywalker.can_sayisi;
        }
            
        setBackground(Color.BLACK);
        if(iyiK.getKarakter_adi().equals("MasterYoda")){
            can=MasterYoda.can_sayisi;
            can2=MasterYoda.can_sayisi;
        }else{
            can=LukeSkywalker.can_sayisi;
            can2=LukeSkywalker.can_sayisi;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    //Klavyeden gelen tuş komutlarınının iyi karakterin kordinatı ile eşleştirip haraketi sağlıyoruz
    @Override
    public void keyPressed(KeyEvent e) {
      int tus =e.getKeyCode();
         if(tus==KeyEvent.VK_UP&&harita[iyiK.getX()-1][iyiK.getY()]!=0){
           if(iyiK.getX()>0){
               tus_k++;
           iyiK.setX(iyiK.getX()-1);
           repaint();
           }   
       }
       if(tus==KeyEvent.VK_DOWN){       
           if(iyiK.getX()<10&&harita[iyiK.getX()+1][iyiK.getY()]!=0){
               tus_k++;
           iyiK.setX(iyiK.getX()+1);
           repaint();
           }   
       }
       if(tus==KeyEvent.VK_LEFT&&harita[iyiK.getX()][iyiK.getY()-1]!=0){
           if(iyiK.getY()>0){
               tus_k++;
           iyiK.setY(iyiK.getY()-1);  
           repaint();
           }  
       }
       if(tus==KeyEvent.VK_RIGHT&&harita[iyiK.getX()][iyiK.getY()+1]!=0){
           if(iyiK.getY()<13){
               tus_k++;
           iyiK.setY(iyiK.getY()+1);  
           repaint();
           } 
       }
       if(tus==KeyEvent.VK_UP||tus==KeyEvent.VK_DOWN||tus==KeyEvent.VK_LEFT||tus==KeyEvent.VK_RIGHT)
            repaint();
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
   
}
