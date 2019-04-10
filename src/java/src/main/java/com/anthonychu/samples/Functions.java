package com.anthonychu.samples;

import java.util.*;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.signalr.*;
import com.microsoft.azure.functions.signalr.annotation.*;

public class Functions {
    @FunctionName("negotiate")
    public SignalRConnectionInfo negotiate(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.POST },
                authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> req,
            @SignalRConnectionInfoInput(
                name = "connectionInfo",
                hubName = "serverlessdraw") SignalRConnectionInfo connectionInfo) {

        return connectionInfo;
    }

    @FunctionName("draw")
    public void draw(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.POST },
                authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<StrokeCollection> req,
            @SignalROutput(
                name = "signalRMessage",
                hubName = "serverlessdraw") OutputBinding<SignalRMessage> signalRMessage) {

        StrokeCollection strokeCollection = req.getBody();
        SignalRMessage msg = new SignalRMessage("newStrokes", strokeCollection);
        signalRMessage.setValue(msg);
    }

    @FunctionName("clear")
    public void clearCanvas(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.POST },
                authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> req,
            @SignalROutput(
                name = "signalRMessage",
                hubName = "serverlessdraw") OutputBinding<SignalRMessage> signalRMessage) {

        SignalRMessage msg = new SignalRMessage("clearCanvas");
        signalRMessage.setValue(msg);
    }
}
