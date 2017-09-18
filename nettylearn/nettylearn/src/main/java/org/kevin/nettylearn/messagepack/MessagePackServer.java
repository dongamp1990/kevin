package org.kevin.nettylearn.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MessagePackServer {
	private final int port;
	public MessagePackServer(int port) {
		this.port = port;
	}
	
	public void start() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap
				.group(group, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
						ch.pipeline().addLast(new MessagePackDecoder());
						ch.pipeline().addLast(new LengthFieldPrepender(2));
						ch.pipeline().addLast(new MessagePackEncoder());
						ch.pipeline().addLast(new MessagePackServerHandler());
					};
			});
			ChannelFuture f = serverBootstrap.bind(port).sync();
			System.out.println(MessagePackServer.class.getName() + " startd and listen on "+ f.channel().localAddress());
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
			workerGroup.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		try {
			new MessagePackServer(1099).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
