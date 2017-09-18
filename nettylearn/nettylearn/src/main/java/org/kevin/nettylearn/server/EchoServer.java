package org.kevin.nettylearn.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.kevin.nettylearn.client.EncoderHandleer;
import org.kevin.nettylearn.server.EchoServerHandler;

public class EchoServer {
	private final int port;
	public EchoServer(int port) {
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
						ch.pipeline().addLast(new EchoServerHandler());
						ch.pipeline().addLast(new OtherServerHandler());
						ch.pipeline().addLast(new EncoderHandleer());
						ch.pipeline().addLast(new OtherServerHandler2());
						ch.pipeline().addLast(new ServerEncoderHandleer());
					};
			});
			ChannelFuture f = serverBootstrap.bind(port).sync();
			System.out.println(EchoServer.class.getName() + " startd and listen on "+ f.channel().localAddress());
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
			workerGroup.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		try {
			new EchoServer(1099).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
