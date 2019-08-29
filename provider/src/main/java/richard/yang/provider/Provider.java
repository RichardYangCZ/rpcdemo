package richard.yang.provider;

import richard.yang.provider.serviceImpl.ProductServiceImpl;
import richard.yang.rpc.RPCServer;
import richard.yang.rpc.Registry;
import richard.yang.service.ProductService;



//服务提供者
public class Provider {

    public static void main(String[] args) {
        //存放类名到实现类的映射
        Registry.map.put(ProductService.class.getName(), ProductServiceImpl.class);
        new RPCServer().provide(9000);
    }

}
