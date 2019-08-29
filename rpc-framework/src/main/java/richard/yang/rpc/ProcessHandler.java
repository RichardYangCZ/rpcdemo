package richard.yang.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessHandler implements Runnable {

    private Socket socket;
    public ProcessHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            //解析消费端传过来的消息
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest =(RpcRequest) inputStream.readObject();

            Class clazz = null;
            // 注册中心   map    ProductService   ---  ProductServiceImpl
            if(Registry.map.containsKey(rpcRequest.getClassName())){
                clazz = Registry.map.get(rpcRequest.getClassName());
            }

            // 反射
            Method method = clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getTypes());

            Object result = method.invoke(clazz.newInstance(),rpcRequest.getParams());

            // 返回结果
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
