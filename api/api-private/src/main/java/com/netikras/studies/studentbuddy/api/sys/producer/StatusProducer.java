package com.netikras.studies.studentbuddy.api.sys.producer;

import com.netikras.studies.studentbuddy.api.sys.generated.StatusApiProducer;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusProducer extends StatusApiProducer {

    @Override
    protected String onGetStringMessageEcho(String message) {
        return message;
    }
}
