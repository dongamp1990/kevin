package org.kevin.nettylearn.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class OrderClient {
	private final String host;
	private final int port;
	private Channel channel;
	public OrderClient(String host, int port) {
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
					ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
					ch.pipeline().addLast(new ProtobufDecoder(OrderRespProto.OrderResp.getDefaultInstance()));
					ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
					ch.pipeline().addLast(new ProtobufEncoder());
					ch.pipeline().addLast(new OrderClientHandler());
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
			final OrderClient client = new OrderClient("localhost", 1099);
//			new Thread(new Runnable() {
//				public void run() {
//					try {
//						while (true) {
//							Thread.sleep(3000L);
//							int timeStmap = new Long(System.currentTimeMillis() / 1000).intValue();
//							Order order = OrderProto.Order.newBuilder().setAddress("深圳市")
//									.setProductName("Netty实战").setCreateTimestmap(timeStmap).build();
//							client.write(order);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
			client.start();
//			client.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
