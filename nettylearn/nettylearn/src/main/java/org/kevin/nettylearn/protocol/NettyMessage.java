package org.kevin.nettylearn.protocol;

public class NettyMessage {
	private Object body;
	private NettyHeader header;

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public NettyHeader getHeader() {
		return header;
	}

	public void setHeader(NettyHeader header) {
		this.header = header;
	}

}
