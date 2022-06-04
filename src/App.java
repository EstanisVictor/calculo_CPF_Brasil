public class App {
    public static void main(String[] args) throws Exception {
        
        //Pegando o total de threads da máquina
        int numbersThreads = Runtime.getRuntime().availableProcessors();
        
        //Quantidade total de CPF's 
        long quantCpfGerar = 999999999L;
        
        //Quantidade de CPF's gerados que cada thread irá trabalhar
        long quantCpfPorThread = quantCpfGerar/numbersThreads;
        
        //Criando minhas threads referente quantidade de threads no PC
        for (int indexThreads = 0; indexThreads < numbersThreads; indexThreads++) {
            //Cria thread
            Thread myThreads = new Thread(new Runnable(){
                //Método que será executado pela thread
                public void run(){
                        
                    //Convertendo o nome da thread (indexThreads) para indice inteiro
                    int threadNumber = Integer.parseInt(Thread.currentThread().getName());
                    //Definindo o valor inicial e o final que uma thread irá executar
                    long initialValue = quantCpfPorThread*threadNumber;
                    long finalValue = quantCpfPorThread*threadNumber+quantCpfPorThread;
                    
                    //Como a divisão é inteira, possa ser que sobra CPF´s que não serão gerados
                    long restValue = quantCpfGerar%numbersThreads;
                    
                    //Se for igual ao valor final passa somando o resto que sobrou de CPF´s a gerar
                    if(threadNumber == (numbersThreads-1)){
                        /*
                        Método que lê os arquivos .txt 
                        e salva o maior numero primo
                        */
                        calculoBrasilCPF.geradorCPF(initialValue, finalValue+restValue);
                    }else{
                        calculoBrasilCPF.geradorCPF(initialValue, finalValue);
                    }
                    
                }
                //Colocando o indice (indexThreads) como nome da thread
            }, String.valueOf(indexThreads));
            
            myThreads.start();
            
        }
        //quantidade de CPF's gerados
        while(Thread.activeCount() > 0){
            if(Thread.activeCount() == 1){
                System.out.println("\033[0;36m"+calculoBrasilCPF.getContadorCPFvalido());
                break;
            }
        }
    }
}
