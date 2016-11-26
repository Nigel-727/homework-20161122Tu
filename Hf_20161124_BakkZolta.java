/**
 * @author: Bakk Zoltán alias Nigel-727 <nigel727@gmail.com>
 */
public class Hf_20161124_BakkZoltan {  
// Feladatspecifikáció:
// [a,b] intervallumban páros/páratlan véletlenszám generálása

  public static void main(String[] args) {
    int a = -20, b = -10; //mindkét oldalról zárt tartomány határai
    boolean psVAGYplan = false; //false: páros; true: páratlan számot akarunk
    int db = 100;
    //a<=b kell hogy legyen, ezért...
    if (!(a <= b)) { //...akkor felcseréljük; A=a, B=b képzeletbeli értékadások:
      a = a + b; //a=A+B
      b = a - b; //b=(A+B)-B=A
      a = a - b; //a=(A+B)-A=B
    }
    System.out.println(db + " darab [" + a + " és " + b + "] közötti "
            + (psVAGYplan ? "páratlan " : "páros ") //false: páros; true: páratlan
            + "véletlenszám: ");
    //Ezúttal a kilépő feltételt könnyebb megfogalmazni:
    if ((a == b) //HA a tartomány 1 db számot tartalmaz
            && (psVAGYplan != páratlan_e(a)) //ÉS a parítása ellentétes azzal amit kérünk
            ) {
      System.out.println("Ilyen számot nem lehet generálni.");
    } else {
      //Természetesen felesleges ilyen sok változót, stb, stb bevezetni, DE:
      //most gyakorlok, sokéves szünet után akarok visszazökkenni a kódolásba.
      double vél; //[0..1) véletlenszám
      int ámosság; //a jó számok száma
      int min; //a legkisebb jó szám
      int szám; //egy jó szám
      //Generálásra fel!
      for (int i = 0; i < db; i++) {
        vél = Math.random();
        ámosság = n_psplan(a, b, psVAGYplan);
        //      System.out.println("ámosság = "+ámosság);
        min = első_psplan(a, psVAGYplan);
        //      System.out.println("min = "+min);
        //Minden készen áll, (véletlen)számoljunk az általános képlettel:
        szám = min + ((int) (vél * ámosság)) * 2; //tudom h egy () felesleges
        //Mutassuk meg:
        System.out.print(szám + ((i+1) % 20 == 0 ? "\n" : " "));
      }
      System.out.println();
    } //else
  } //main

  private static boolean páratlan_e(int x) {
    return x % 2 == 1; //páratlan esetén true, páros esetén false
  }

  //[a,b]-beli páros/páratlan int-ek száma
  private static int n_psplan(int a, int b, boolean psVAGYplan) {
    return psVAGYplan
            ? //false: páros; true: páratlan
            //PÁRATLANok száma amikor _a_ páros : páratlan
            (0 == a % 2) ? (b - a + 1) / 2 : (b - a) / 2 + 1
            : //PÁROSak száma amikor _a_ páros : páratlan
            (0 == a % 2) ? (b - a) / 2 + 1 : (b - a + 1) / 2;
  }

  //[a,a+1] tartománybéli első páros/páratlan int
  private static int első_psplan(int a, boolean psVAGYplan) {
    return a //Ezt, ha az elvárt parítás különbözik, még... 
            + (páratlan_e(a) == psVAGYplan ? 0 : 1) //..1-gyel növelni kell.
            ;
  }
} //class
