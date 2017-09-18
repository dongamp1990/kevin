package org.kevin.nettylearn.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ShoppingCarServer {
	private final int port;
	public ShoppingCarServer(int port) {
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
						ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
						ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
						ch.pipeline().addLast(new ShoppingCarServerHandler());
					};
			});
			ChannelFuture f = serverBootstrap.bind(port).sync();
			System.out.println(ShoppingCarServer.class.getName() + " startd and listen on "+ f.channel().localAddress());
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
			workerGroup.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) {
		try {
			new ShoppingCarServer(1099).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
