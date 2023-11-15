package i.bobrov.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Network {
    private static final String HOST = "localhost";
    private static final int PORT = 8189;
    private SocketChannel channel;

    public Network(Callback callback) {
        start(callback);
    }

    private void start(Callback onMessageReceivedCallback) {
        var thread = new Thread(() -> {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            var bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            channel = socketChannel;
                            socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder(),
                                    new ClientHandler(onMessageReceivedCallback));
                        }
                    });
            try {
                ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                workerGroup.shutdownGracefully();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMessage(String str) {
        channel.writeAndFlush(str);
    }
}
