package org.kevin.nettylearn.messagepack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessagePackClientHandler extends SimpleChannelInboundHandler<AddGoodsResp> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Goods car = new Goods();
		car.setAmount(100D);
		car.setProductId(1L);
		ctx.write(car);
		ctx.flush();
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, AddGoodsResp msg)
			throws Exception {
		System.out.println("客户端收到：" + msg.toString());
		
	}
}
