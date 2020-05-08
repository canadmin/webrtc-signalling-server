package com.dualchat.dualchat.signalling.wsconfig;

import lombok.Data;

@Data
public class SignalMessage {

    private String event;
    private String dest;
    private Object data;

}
