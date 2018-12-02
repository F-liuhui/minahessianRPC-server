package com.cnpc.test.annotation;

import com.cnpc.test.config.MinaHessianServerImportSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

@Import(MinaHessianServerImportSelector.class)
public @interface EnableMinaHessian {
    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    int order() default 2147483647;
}
