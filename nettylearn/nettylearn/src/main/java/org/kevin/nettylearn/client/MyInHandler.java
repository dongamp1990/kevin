package org.kevin.nettylearn.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyInHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx,
			Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		String msgStr = buf.toString(CharsetUtil.UTF_8);
		System.out.println("MyInHandler已收到：" + msgStr);
		ctx.fireChannelRead(msg);
	}
}
