package org.kevin.nettylearn.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ShoppingCarClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("客户端收到：" + msg.toString());
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ShoppingCar car = new ShoppingCar();
		car.setAmount(100D);
		car.setProductId(1L);
		ctx.writeAndFlush(car);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
}
