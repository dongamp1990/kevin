package org.kevin.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.kevin.common.Common;

public class RequestLogginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String method = ((HttpServletRequest) request).getMethod();
		StringBuffer data = new StringBuffer();
		if("POST".equalsIgnoreCase(method)) {
			ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
					(HttpServletRequest) request);
			try {
				wrappedRequest.getReader();
				data.append(new String(wrappedRequest.rawData));
			} catch (Exception e) {
				Common.LOG.info("read param error");
			}
			wrappedRequest.resetInputStream();
			Common.LOG.info("RequestURI: {}, Params: {}",
					((HttpServletRequest) request).getRequestURI(), data.toString());
			chain.doFilter(wrappedRequest, response);
		}else if("GET".equalsIgnoreCase(method)){
			for (String paramKey : request.getParameterMap().keySet()) {
				data.append(paramKey).append("=").append(request.getParameter(paramKey)).append("&");
			}
			if (data.length() > 0) {
				String tempData = data.substring(0, data.length() - 1);
				data = new StringBuffer(tempData);
			}
			Common.LOG.info("RequestURI: {}, Params: {}",
					((HttpServletRequest) request).getRequestURI(), data.toString());
			chain.doFilter(request, response);
		}else {
			Common.LOG.info("RequestURI: {}, Params: {}",
					((HttpServletRequest) request).getRequestURI(), data.toString());
		}
		response.getOutputStream().write("test".getBytes());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	private class ResettableStreamHttpServletRequest extends
			HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}

		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = readByte(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}

		private byte[] readByte(BufferedReader reader) {
			if (reader == null) {
				return null;
			}
			StringBuilder sBuilder = new StringBuilder();
			String data = null;
			try {
				if (reader != null) {
					while ((data = reader.readLine()) != null) {
						sBuilder.append(data);
					}
				}
			} catch (Exception e) {
				Common.LOG.info("read param error");
			}
			return sBuilder.toString().getBytes();
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = readByte(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}

		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;

			@Override
			public int read() throws IOException {
				return stream.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {

			}
		}
	}
}
