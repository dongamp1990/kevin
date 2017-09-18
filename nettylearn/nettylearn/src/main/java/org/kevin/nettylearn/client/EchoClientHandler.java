package org.kevin.nettylearn.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		String msgStr = msg.toString(CharsetUtil.UTF_8);
		System.out.println("客户端已收到：" + msgStr);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//通道就绪，可以处理一些业务？
//		ctx.write(Unpooled.copiedBuffer("Netty rocks!",CharsetUtil.UTF_8));
//		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
