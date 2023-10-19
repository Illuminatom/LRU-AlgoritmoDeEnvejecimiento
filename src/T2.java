package src;

public class T2 extends Thread{

    public void run(){
        
        while(Main.fin == false){
            if(Main.paginasUsadas.size() == 0){
                continue;
            }
            Main.tablaPaginas.envejecerPaginas(Main.paginasUsadas);
            Main.paginasUsadas.clear();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
