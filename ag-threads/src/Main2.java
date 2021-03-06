
public class Main2 {
  static Object lock = new Object();
  static volatile int soma = 0;
  
  private static synchronized void execT1(){
    soma = soma + 1;
    System.out.println("(1) soma = soma + 1 = " + soma);
    soma = soma - 1;
    System.out.println("(2) soma = soma - 1 = " + soma);
    soma = soma * 3;
    System.out.println("(3) soma = soma * 3 = " + soma);
    soma = soma - 1;
    System.out.println("(4) soma = soma - 1 = " + soma);
  }
  
  private static synchronized void execT2(){
      soma = soma - 1;
      System.out.println("(5) soma = soma - 1 = " + soma);
      soma = soma + 5;
      System.out.println("(6) soma = soma + 5 = " + soma);
      soma = soma + 10;
      System.out.println("(7) soma = soma + 10 = " + soma);
      soma = soma / 2;
      System.out.println("(8) soma = soma / 2 = " + soma);
  }
  
  public static void main(String[] args) throws InterruptedException {
	  //
    final Thread t1 = new Thread(){
      public void run() {
        execT1();
        synchronized (this) {
          //notify();//ler uma variavel de estado e altera o estado para executar... state = "executando..."
        }
        System.out.println("----");
      };
    };
    //
    final Thread t2 = new Thread(){
      public void run() {
        synchronized (t1) {
          try {
            t1.wait();//ler a variavel de T1 chamada state JMP 51
            execT2();
          } catch (InterruptedException e) {
        	  	System.out.println("-------");
            e.printStackTrace();
          } 
        }
      };
    };
    //
    //for (int i = 0; i < 10; i++){
      t2.start();
      //t2.interrupt();
      t1.start();
    //}
    //
    Thread.sleep(1000);
    System.out.println(soma);
  }

}
