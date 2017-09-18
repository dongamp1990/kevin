package org.kevin.nettylearn.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

public class EncoderHandleer extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		String originMsg = buf.toString(CharsetUtil.UTF_8);
		System.out.println("EncoderHandleer处理, 原msg:" + originMsg);
		super.write(ctx, msg, promise);
	}
}
