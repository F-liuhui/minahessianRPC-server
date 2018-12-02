package com.cnpc.test.minahessianserver;

import com.cnpc.test.codecFactory.ByteArrayCodecFactory;
import com.cnpc.test.handler.MinaHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
import java.net.InetSocketAddress;

public class MinaHessianServer {
    private static final int PORT = 9090;// 定义监听端口

    ApplicationContext springApplication;

    public MinaHessianServer(ApplicationContext springApplication) {
        this.springApplication = springApplication;
    }

    public void start() throws IOException {
        //创建一个非阻塞的Server端socket，用NIO
        IoAcceptor acceptor = new NioSocketAcceptor();
        //创建接受数据的过滤器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        //设定这个过滤器将一行一行的读取数据
        chain.addLast("codec",new ProtocolCodecFilter(new ByteArrayCodecFactory()));// 指定编码过滤器
        // 指定业务逻辑处理器
        acceptor.setHandler(new MinaHandler(springApplication));
        // 设置端口号
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
        acceptor.bind(); //启动监听
        System.out.println("Mina Server is Listen on:"+PORT);
    }
}
