package i.bobrov.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private final Callback onMessageReceivedCallback;

    public ClientHandler(Callback onMessageReceivedCallback) {
        this.onMessageReceivedCallback = onMessageReceivedCallback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
        if (onMessageReceivedCallback != null) {
            onMessageReceivedCallback.callback(s);
        }
    }
}
