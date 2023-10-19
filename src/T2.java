package src;

public class T2 extends Thread{
    public void run(){
        while(isFin()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(T1.paginasUsadas.size() != 0){
                T1.tablaPaginas.envejecerPaginas(T1.paginasUsadas);
                T1.paginasUsadas.clear();
            }
    }
}

public synchronized boolean isFin() {
    return T1.fin;
}
}
