package com.cnpc.test.config;
import com.cnpc.test.minahessianserver.MinaHessianServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinaHessianServerConfig {
    @Autowired
    ApplicationContext springApplication;

    @Bean
    public MinaHessianServer initMinaServer() {
        System.out.println("准备启动minahessian服务");
        MinaHessianServer minaServer=new MinaHessianServer(springApplication);
        try {
            minaServer.start();
        }catch (Exception e){
            minaServer=null;
            e.printStackTrace();
        }
        return minaServer;
    }
}
