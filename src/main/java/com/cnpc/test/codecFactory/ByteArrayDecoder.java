package com.cnpc.test.codecFactory;

import com.caucho.hessian.io.HessianInput;
import com.cnpc.test.entity.ExchangeEntity;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.ByteArrayInputStream;

public class ByteArrayDecoder extends ProtocolDecoderAdapter {
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        // TODO Auto-generated method stub
        int len = in.limit();
        byte []  dst=new byte [len];
        in.get(dst);
        ByteArrayInputStream is = new ByteArrayInputStream(dst);
        HessianInput hi = new HessianInput(is);
        ExchangeEntity entity= (ExchangeEntity) hi.readObject();
        out.write(entity);
    }
}
