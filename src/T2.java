package src;

public class T2 extends Thread{
    public void run(){
        while(true){
            synchronized(T1.class) {
                if(T1.fin == true){
                    break;
                }
            }
            if(T1.paginasUsadas.size() != 0){
                T1.tablaPaginas.envejecerPaginas(T1.paginasUsadas);
                T1.paginasUsadas.clear();
            }
        }
    }
}
