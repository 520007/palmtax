package com.hzxckj.common.mybatis.component;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class QuerySupportInjectRunner extends DefaultSqlInjector implements CommandLineRunner {

    @Resource
    private QuerySupportMethod querySupportMethod;

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(querySupportMethod);
        return methodList;
    }

    @Override
    public void run(String... args) throws Exception {
        querySupportMethod.injectQuerySupportMappedStatement();
    }
}
