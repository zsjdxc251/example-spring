package org.example.spring.model;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhengshijun
 * @date 2021/1/22.
 */
//@Order(3)
@Component
public class ProcessOne implements BaseProcess{
    @Override
    public void support() {

    }

    @Override
    public String toString() {
        return "ProcessOne";
    }
}
