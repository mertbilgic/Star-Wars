import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;


public class AnaEkran extends JFrame{
static List<Integer>path= new ArrayList<Integer>();
public static int  min_distK =Integer.MAX_VALUE;
public static String s="";
public static String sa ="";
static String[] yol={"","","","",""};
static int haritaX=0,haritaY=0;
static  int[][] harita = new int[12][15];
static String[] karakter={"","","","","",""};
static String[] kapi ={"","","","","",""};
static int kapiX[]= new int[5];
static int kapiY[]= new int[5];
static int t=0;
static String iyi_karakter;
    public AnaEkran(String title) throws HeadlessException {
        super(title);
        
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        //Scanner scanner = new Scanner(System.in);
       // System.out.println("İyi karakteri giriniz:");
       // iyi_karakter=scanner.nextLine();
        
        
        AnaEkran ekran = new AnaEkran("Grafik Kullanımı");        
         FileInputStream fis=null;
        
        try {
            fis = new FileInputStream("harita.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya okunamadi");
        }
       
       
        int deger,syc=0,i=0,j=0,kntrl=0;
        String s="";
        
        //KARAKTERLERİN DİZİYE ATANDIĞI KISIM
           while((deger =fis.read())!=-1&&((char)deger)!='0'){
                if(((char)deger)=='K'){
                    fis.skip(8);
                    deger =fis.read();
                    while(((char)deger)!=','){
                        karakter[i]+=(char)deger;
                        if(karakter[i].equals("MasterYoda")||karakter[i].equals("LukeSkywalker")){
                        iyi_karakter=karakter[i];
                        karakter[i]="";
                        fis.skip(10);
                    }
                        deger =fis.read();
                    } 
                    i++;
                    
                }
                if(((char)deger)==','){
                    fis.skip(5);
                    deger =fis.read();
                    kapi[t]+=(char)deger;
                    t++;
                }
            }

           //Harita Yükleme
           for(haritaX=0;haritaX<11;haritaX++){
            for(haritaY=0;haritaY<14;haritaY++){
                   s +=(char)deger;
                   harita[haritaX][haritaY] =Integer.parseInt(s);  
                   deger =fis.read();
                   deger =fis.read();
                   s="";
                   //System.out.print(harita[haritaX][haritaY]+" ");
               }
              // System.out.println("");
           }
            for(haritaX=0;haritaX<11;haritaX++){
            for(haritaY=0;haritaY<14;haritaY++){
                // System.out.print(harita[haritaX][haritaY]+" ");
               }
               //System.out.println("");
           }
           
           fis.close();
           int p;
           for(p=0 ;p<t;p++){
               //System.out.println(kapi[p]);
               if(kapi[p].equals("A")){
                  kapiX[p]=0;
                  kapiY[p]=5;
               }
               if(kapi[p].equals("B")){
                  kapiX[p]=4;
                  kapiY[p]=0;
               }
               if(kapi[p].equals("C")){
                  kapiX[p]=12;
                  kapiY[p]=0;
               }
               if(kapi[p].equals("D")){
                  kapiX[p]=13;
                  kapiY[p]=5;
               }
               if(kapi[p].equals("E")){
                  kapiX[p]=4;
                  kapiY[p]=10;
               }
               
           }
        ekran.setResizable(true);//Ekranın genişleyebilir olmasını engelliyoruz
        ekran.setFocusable(false);//JFrame odaklanmasını engelliyoruz.
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ekran.setSize(1920,1000);
        
        GrafikKullanımı grafik = new GrafikKullanımı();
        grafik.esitle(AnaEkran.harita);
        grafik.Kesitle(karakter);
        grafik.requestFocus();//JPanel'in klavyeden gelicek değerlere odaklanmasını sağlar.
        grafik.addKeyListener(grafik);//Klavye üzerindeki işlemlerimizi sağlıyor.İçine oyunu verdiğimiz için sadece oyunun üzerinde çalışıcak
        grafik.setFocusable(true);//JPanel'in odağı almasını sağlarız.
        //grafik.setFocusTraversalKeysEnabled(false);
        
          ekran.add(grafik);
          ekran.setVisible(true);
          
          
    }
    
}

