package org.kevin.nettylearn.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class ClientOutboundHandler extends ChannelOutboundHandlerAdapter {
	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		String originMsg = buf.toString(CharsetUtil.UTF_8);
		System.out.println("ClientOutboundHandler处理");
		super.write(ctx, Unpooled.copiedBuffer("ClientOutboundHandler处理后_msg="+originMsg,CharsetUtil.UTF_8), promise);
		//释放资源
		ReferenceCountUtil.release(buf);
		//通知ChannelPromise 若ChannelPromise没有被通知可能会导致其中一个ChannelFutureListener不被通知去处理一个消息
		promise.setSuccess();
	}
	
	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		System.out.println("deregister channel");
		super.deregister(ctx, promise);
	}
}
