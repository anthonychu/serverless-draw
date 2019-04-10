package com.anthonychu.samples;

import java.util.*;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.signalr.*;
import com.microsoft.azure.functions.signalr.annotation.*;

public class Negotiate {
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
}
