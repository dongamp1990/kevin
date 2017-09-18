package org.kevin.nettylearn.protobuf;

import org.kevin.nettylearn.protobuf.OrderProto.Order;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class OrderClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("客户端收到：" + msg.toString());
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		int timeStmap = new Long(System.currentTimeMillis() / 1000).intValue();
		Order order = OrderProto.Order.newBuilder().setAddress("深圳市")
				.setProductName("Netty实战").setCreateTimestmap(timeStmap).build();
		ctx.writeAndFlush(order);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
}
