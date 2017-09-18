package org.kevin.nettylearn.messagepack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.List;
import java.util.Random;

public class MessagePackServerHandler extends ChannelInboundHandlerAdapter {
	@SuppressWarnings("unchecked")
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		List<Object> list = (List<Object>) msg;
		Goods g = (Goods) list.get(0);
		System.out.println("服务器已收到源请求: " + list);
		AddGoodsResp resp = new AddGoodsResp();
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
