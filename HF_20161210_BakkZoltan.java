/**
 * Prímszámolgatás
 * Kérjünk be a felhasználótól egy pozitív egészet (3 <= N <= 1000).
 * Töltsünk fel egy tömböt a következő 
 * 20 db prímszámmal (mindegyik N-nél nagyobb v. egyenlő) //kiválasztás tétel
 * Soroljuk be az elemeket értéküktől függően 
 * 10 db (tetszőlegesen megválasztott határú) kategóriába! //megszámolás tétel
 * Válogassuk szét a tömb elemeit (helyben)
 * az 1 v. 3 illetve a 7 v. 9 végződésűekre! //(helyben) szétválogatás tétel
 * (A feladatnak a való világhoz való kapcsolódásának bármilyen nullától 
 * különböző mértéke kizárólag a véletlen műve!)
 * TODO: Fogalmazzunk meg feladatot, amit a programunk old meg!
 * @author Nigel-727 
 */
public class HF_20161210_BakkZoltan {
//TODO: szebb lehetne a globális változók minimalizálásával
  static final int PRÍMEK_DB = 20; //TODO: ez is lehessen input
  static int[] prímek = new int[PRÍMEK_DB];
  static final int ALSÓ_H = 3; //TODO: ez is lehessen input
  static final int FELSŐ_H = 1000; //TODO: ez is lehessen input
  static int n; //ez az N; ettől kezdve választjuk ki egymás után az egyre növekvő prímeket
  static int KATEGÓRIÁK_DB = 10; //TODO: ez is lehessen input (lehetne még: kategóriák[][])
  
  public static void main(String[] args) {
    System.out.println("Házi feladat by Bakk Zoltán @20161210Sa - \"Prímszámolgatás\"");
    System.out.println("Kérek egy "
            + ALSÓ_H + " és "
            + FELSŐ_H + " közé eső pozitív egészet! (kiválasztom a következő "
            + PRÍMEK_DB + " db, nála >= prímszámot)");
    while ( !(ALSÓ_H<=(n=extra.Console.readInt("N=? ")) && (n<=FELSŐ_H)) ) 
      ;
    int i = n;
    for (int jószámDb=0; jószámDb<PRÍMEK_DB; jószámDb++) {//akkor jó a szám, ha prím
      while ( !prím(i++) ) //"kiválasztás_ programozási tétel"
        ;
      prímek[jószámDb] = i-1; //korrekció (i ide mindenképpen inkrementálva érkezik)
    }
    kiírTömb();
    //Határozzunk meg kategóriákat 
    //(most, önkényesen: első és utolsó elem különbsége osztva KATEGÓRIÁK_DB)
    //és írjuk ki, hány elem esik az egyes kategóriákba!
    //"megszámolás programozási tétel" (kiírással)
    int lépték = //TODO: esetleg meglehet h valószínűleg talán túl van bonyolítva
       (int)Math.round( 
               (double)(prímek[PRÍMEK_DB-1]-prímek[0]+1)/KATEGÓRIÁK_DB ); 
    int alsóHatár = prímek[0];
    i = 1; //most: kategória számolására (output célra csak)
    for (i = 1; i <= KATEGÓRIÁK_DB; i++) {
      int db = intervallumDb(alsóHatár, alsóHatár+lépték-1);
      System.out.println("kategória#"+i+" ("
        +alsóHatár+"-"+(alsóHatár+lépték-1)+"): "+db+" db");
      alsóHatár+=lépték;
    } //TODO: (HIBA) időnként kimarad a legutolsó elem
    //
    //szétválogatás helyben; "szétválogatás programozási tétel"
    //MOST az 1 v. 3 ill. a 7 v.9 végződés a két tulajdonság, ami alapján.
    //Az eredeti tömbön belül válogatunk, 2 db index-szel:
    //az elejétől a vége felé ill. a vége felől az eleje felé haladva
    //hasonlítsuk össze és helyezzük át az elemeket!
    //http://progalap.elte.hu/downloads/seged/eTananyag/lecke20_lap1.html
    int első=0, 
        utolsó=PRÍMEK_DB-1, 
        segéd=prímek[első];
    while (első<utolsó) {
      while (első<utolsó && !tulajdonság(prímek[utolsó]))
        utolsó--;
      if (első<utolsó) {
        prímek[első]=prímek[utolsó];
        első++;
        while (első<utolsó && tulajdonság(prímek[első]))
          első++;
        if (első<utolsó) {
          prímek[utolsó]=prímek[első];
          utolsó--;
        }
      }
    }
    prímek[első]=segéd;
    int határ = tulajdonság(prímek[első]) ? első : első-1;
    //
    kiírTömb();
    System.out.println("Az {1,3}-ra végződő prímek lettek"
        +" a tömb 1-"+(határ+1)+". elemei,"
        +" a {7,9}-re végződőek a tömb "+(határ+2)+"-"+PRÍMEK_DB+". elemei.");
  } //main()
  
  private static boolean prím(int szám) {
    if(szám==2)
      return true;
    if(szám<2 || szám % 2 == 0)
      return false;
    int osztóDb=1;
    for (int i = 3; i <= Math.sqrt(szám) && osztóDb==1; i+=2) 
      if(szám % i == 0)
        osztóDb++;
    return (osztóDb==1);
  } 
  
  private static int intervallumDb(int a, int b) {
    int db=0;
    for (int i = 0; i < PRÍMEK_DB; i++)
      if(a<=prímek[i] && prímek[i]<=b)
        db++;
    return db;
  }

  private static int lastdigit(int number) {
    return number % 10;
  }

  //TODO: átírni amikor más tulajdonságra vizsgálunk (könnyen átírható)
  private static boolean tulajdonság(int num) { 
    int x = lastdigit(num); 
    return x==1 || x==3;
  }
  
  private static void kiírTömb()
  { //fejléccel; TODO: itt se legyenek literálok (most: 4, "----")
    for (int j = 0; j < PRÍMEK_DB; j++)
      System.out.print(extra.Format.right(j+1,4)+" ");
    System.out.println();
    for (int j = 0; j < PRÍMEK_DB; j++)
      System.out.print("---- ");
    System.out.println();
    for (int j = 0; j < PRÍMEK_DB; j++)
      System.out.print(extra.Format.right(prímek[j],4)+" ");
    System.out.println();
  }
  
} //class