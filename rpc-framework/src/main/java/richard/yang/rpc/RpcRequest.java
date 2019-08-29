package richard.yang.rpc;

import lombok.Data;

import java.io.Serializable;


/**
 * 构建请求传过来的参数
 */
@Data
public class RpcRequest implements Serializable {
    //类名
    private String className;
    //方法名
    private String methodName;
    //参数类型
    private Class[] types;
    //参数
    private Object[] params;
}
