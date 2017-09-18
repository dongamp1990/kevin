package org.kevin.nettylearn.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		try {
			String receivedMsg = buf.toString(CharsetUtil.UTF_8);
			System.out.println("服务器已收到源请求: " + receivedMsg);
			ctx.write(Unpooled.copiedBuffer(receivedMsg,CharsetUtil.UTF_8));
			ctx.flush();
//			ctx.fireChannelRead(msg);
			ctx.channel().write(Unpooled.copiedBuffer("channel发送的消息",CharsetUtil.UTF_8));
			ctx.channel().flush();
		} finally {
	        ReferenceCountUtil.release(buf); // (2)
	    }
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.write(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
