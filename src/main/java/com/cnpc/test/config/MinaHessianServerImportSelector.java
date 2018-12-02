package com.cnpc.test.config;
import com.cnpc.test.annotation.EnableMinaHessian;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

public class MinaHessianServerImportSelector extends AdviceModeImportSelector<EnableMinaHessian> {
    private String MINAHESSIAN_SERVER_CONFIG_CLASSNAME="com.cnpc.test.config.MinaHessianServerConfig";
    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode){
            case PROXY:
                return new String[]{MINAHESSIAN_SERVER_CONFIG_CLASSNAME};
            case ASPECTJ:
                return new String[]{MINAHESSIAN_SERVER_CONFIG_CLASSNAME};
                default:
                    return new String[]{MINAHESSIAN_SERVER_CONFIG_CLASSNAME};
        }
    }
}
