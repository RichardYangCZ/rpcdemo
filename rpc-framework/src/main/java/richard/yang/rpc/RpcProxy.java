package richard.yang.rpc;

import java.lang.reflect.Proxy;

public class RpcProxy<T> {

    public T remoteCall(String host,int port,Class interfaces){
        //通过动态代理+反射获得实例
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},
                new RemoteInvocationHandler(host,port,interfaces));
    }
}
