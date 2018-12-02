package com.cnpc.test.handler;
import com.cnpc.test.entity.ExchangeEntity;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;

public class MinaHandler extends IoHandlerAdapter {
    private ApplicationContext springApplication;
    public MinaHandler(ApplicationContext springApplication) {
        super();
        this.springApplication=springApplication;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated-------------------------");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened-------------------------");
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed-------------------------");
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle-------------------------");
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.write(cause);
        session.write(new RuntimeException("请求异常："+cause.getMessage()));
        session.closeNow();
        session.getService().dispose();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if(message instanceof ExchangeEntity){
            ExchangeEntity entity= (ExchangeEntity) message;
            Class clazz=entity.getClazz();
            Object[] args=entity.getArgs();
            String methodName=entity.getMethodName();
            Object o=springApplication.getBean(clazz);
            Method[] methods= o.getClass().getDeclaredMethods();
            Method method=null;
            Object reslut=null;
            for(Method m:methods){
                if(m.getName().equals(methodName)){
                    method=m;
                    break;
                }
            }
            if(method!=null) {
                reslut = method.invoke(o, args);
            }
            session.write(reslut);
        }else{
            session.write(new RuntimeException("不是合法的minahessian协议请求"));
            session.closeNow();
            session.getService().dispose();
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent-------------------------");
        super.messageSent(session, message);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        System.out.println("inputClosed-------------------------");
        super.inputClosed(session);
    }
}
