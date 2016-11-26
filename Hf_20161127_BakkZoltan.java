public class Hf_20161127_BakkZoltan { //házi feladat ekkorra: 20161127Su

  public static void main(String[] args) {
    final int MAX_P = 10; //maximum ennyi pontot lehet elérni
    final int VIZSGÁZÓK_DB;
    int rendesemberDb = VIZSGÁZÓK_DB = 17, //tfh. nem akarunk célozgatni
            sajátpontszám, szomszédpontszám = 0; //Az előző szomszédra gondolunk... 
    //..."Ütköző" szerepe miatt fontos h kezdőértéket kapjon.
    int i = VIZSGÁZÓK_DB, //már itt bevezetjük az _i_ segédváltozót
            szjegyDb = 1; //a fejléc miatt kell számjegyszám
    while (9 < i) { //amíg többjegyű
      i /= 10; szjegyDb++; //több utasítás egy sorban (nem releváns algoritmus)
    }
    for (i = 1; i <= VIZSGÁZÓK_DB; i++) //FEJLÉC
      System.out.print("#" + extra.Format.left(i, szjegyDb + 1));
    System.out.println();
    //ITT kezdődnek a lényeges algoritmusok:
    for (i = 1; i <= VIZSGÁZÓK_DB; i++) {
      sajátpontszám = (int) (Math.random() * (MAX_P + 1)); //[0..MAX_P]-beli érték
      System.out.print(extra.Format.left(sajátpontszám, szjegyDb + 1) + " ");
      if (0 < szomszédpontszám && sajátpontszám == szomszédpontszám) 
        rendesemberDb--;
      szomszédpontszám = sajátpontszám; //mielőtt továbblépünk, eltároljuk az előző pontszámot
    }
    System.out.println();
    System.out.println("A csoportba (legalább) " + rendesemberDb + " rendes ember járt.");
    //a százaléktól függően osztályozzuk a csoportot, az osztályzatot pedig írjuk ki szöveggel
    int jóSzázalék = (int) (100 * (double) rendesemberDb / VIZSGÁZÓK_DB);
    System.out.print("Ez a vizsgázók "+jóSzázalék+" %-a (");
    int osztályzat //[1, 100] és [1, 5] egészt állítunk elő:
            = jóSzázalék <=60 ? 1 : jóSzázalék <=70 ? 2 : jóSzázalék <=80 ? 3 : jóSzázalék <=90 ? 4 : 5;
    System.out.print("osztályzat = "+osztályzat+" \"");
    //...TODO: hogyan lehetne vmi hasonlót elérni szimpla matematikai képlettel?
    switch (osztályzat) {
      default: //mutatom h a cimkesorrend tetszőleges lehet
        System.out.print("link alakok gyülekezete"); break; //PPT helykorlát miatt
      case 5: //mutatom h nem muszáj a {} se
        System.out.print("hihetetlen"); break; //itt is csak a helykorlát miatt
      case 4:
        System.out.print("ennyi még belefér"); break;
      case 3: //mutatom h amikor a címke egyezik de nincs utasítás, belép a következőbe
      case 2: //3 és 2 esetében ide
        System.out.print("majdnem reménytelen"); break;
    }
    System.out.println("\")"); //idézőjel bezár, zárójel bezár
  }
}
