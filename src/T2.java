package src;

public class T2 extends Thread{
    public void run(){
        while(true){
            synchronized(Main.class) {
                if(Main.fin == true){
                    break;
                }
            }
            if(Main.paginasUsadas.size() == 0){
                continue;
            }
            Main.tablaPaginas.envejecerPaginas(Main.paginasUsadas);
            Main.paginasUsadas.clear();
        }
    }
}
