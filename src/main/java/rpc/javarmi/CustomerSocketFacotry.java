package rpc.javarmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * RMI服务端的通信接口是随机产生的 可能被防火墙拦截 需要强制指定RMI通信端口 一般自定义一个类来实现
 */
public class CustomerSocketFacotry extends RMISocketFactory {
/*指定通信端口访问被拦截*/
    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host,port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if(port ==0){
            port = 8501;
        }
        System.out.println("rmi nofity port"+port);
        return new ServerSocket(port);
    }
}
