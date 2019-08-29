package richard.yang.consumer;

import richard.yang.rpc.RpcProxy;
import richard.yang.service.ProductService;

//消费端代码
public class Consumer {

    public static void main(String[] args) {
        //请求服务提供端(连接提供端，传递参数)
        RpcProxy proxy = new RpcProxy();
        ProductService service = (ProductService)proxy.remoteCall(
                "localhost",9000,ProductService.class);
        System.out.println(service.getProduct(100L));

    }

}
