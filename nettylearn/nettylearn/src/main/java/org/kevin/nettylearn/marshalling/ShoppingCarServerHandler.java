package org.kevin.nettylearn.marshalling;

import java.util.Random;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.kevin.nettylearn.protobuf.OrderProto.Order;
import org.kevin.nettylearn.protobuf.OrderRespProto.OrderResp;

public class ShoppingCarServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ShoppingCar car = (ShoppingCar)msg;
		System.out.println("服务器已收到源请求: " + car.toString());
		AddShoppingCarResp resp = new AddShoppingCarResp();
		resp.setCarId(new Random().nextLong());
		ctx.write(resp);
		ctx.flush();
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.write(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
