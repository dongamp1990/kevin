package org.kevin.nettylearn.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoClient {
	private final String host;
	private final int port;
	private Channel channel;
	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void write(Object msg) {
		channel.write(msg);
		channel.flush();
	}
	
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			 .channel(NioSocketChannel.class)
			 .handler(new ChannelInitializer<SocketChannel>() {
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new MyInHandler());
					ch.pipeline().addLast(new EchoClientHandler());
//					ch.pipeline().addLast(new ClientOutboundHandler());
//					ch.pipeline().addLast(new EncoderHandleer());
				};
			 });
			ChannelFuture f = b.connect(host, port).sync();
			channel = f.channel();
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		try {
			final EchoClient client = new EchoClient("localhost", 1099);
			new Thread(new Runnable() {
				public void run() {
					try {
						int i = 1;
						while (true) {
							Thread.sleep(3000L);
							client.write(Unpooled.copiedBuffer("第" + i + "条消息",CharsetUtil.UTF_8));			
							i++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
