package org.kevin.nettylearn.marshalling;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodeCFactory {
	private final static MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
	private final static MarshallingConfiguration configuration = new MarshallingConfiguration();
	
	static {
		configuration.setVersion(5);
	}
	
	public static MarshallingDecoder buildMarshallingDecoder() {
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024);
		return decoder;
	}
	
	public static MarshallingEncoder buildMarshallingEncoder() {
		MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(marshallerFactory, configuration);
		MarshallingEncoder encoder = new MarshallingEncoder(marshallerProvider);
		return encoder;
	}
}
