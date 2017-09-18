package org.kevin.nettylearn.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.kevin.nettylearn.protobuf.OrderProto.Order;
import org.kevin.nettylearn.protobuf.OrderRespProto.OrderResp;

public class OrderServerHandler extends ChannelInboundHandlerAdapter {
	private volatile Long orderId = 1L;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Order order = (Order)msg;
		System.out.println("服务器已收到源请求: " +"商品名称："+ order.getProductName() + 
				" 地址：" + order.getAddress() + " timeStmap："+ order.getCreateTimestmap());
		OrderResp resp = OrderRespProto.OrderResp.newBuilder().setOrderId(getOrderId().intValue()).build();
		ctx.write(resp);
		ctx.flush();
	}
	
	private Long getOrderId() {
		orderId += 1;
		return orderId;
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
