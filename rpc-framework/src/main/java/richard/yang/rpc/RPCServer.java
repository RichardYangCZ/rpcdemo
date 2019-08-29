package richard.yang.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class RPCServer {

    ExecutorService service = Executors.newCachedThreadPool();

    public void provide(int port){

        try {
            //通过socket进行通信，线程池进行处理
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();//  阻塞   nio 非阻塞

                service.execute(new ProcessHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
