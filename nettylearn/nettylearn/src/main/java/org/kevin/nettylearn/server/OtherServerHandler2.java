package org.kevin.nettylearn.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class OtherServerHandler2 extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		String receivedMsg = buf.toString(CharsetUtil.UTF_8);
		System.out.println("OtherServerHandler2收到: " + receivedMsg);
	}
}
