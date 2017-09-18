package org.kevin.nettylearn.messagepack;

import org.msgpack.annotation.Message;

@Message
public class Goods {
	private Long productId;
	private Double amount;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Goods [productId=" + productId + ", amount=" + amount
				+ "]";
	}
}
