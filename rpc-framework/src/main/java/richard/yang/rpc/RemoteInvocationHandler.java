package richard.yang.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private int port;

    private Class interfaces;


    public RemoteInvocationHandler(String host, int port, Class interfaces) {
        this.host = host;
        this.port = port;
        this.interfaces = interfaces;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //封装消息体
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(interfaces.getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setTypes(method.getParameterTypes());
        rpcRequest.setParams(args);

        Object result = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            Socket socket = new Socket(host, port);

            //发送消息
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            //接收结果
            inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }


        return result;
    }
}
