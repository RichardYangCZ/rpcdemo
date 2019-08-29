package richard.yang.rpc;

import java.util.concurrent.ConcurrentHashMap;

//注册中心
public class Registry {
    public static ConcurrentHashMap<String,Class> map = new ConcurrentHashMap<String, Class>();
}
