package org.kevin.nettylearn.marshalling;

import java.io.Serializable;

public class ShoppingCar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2187881729586282204L;
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
		return "ShoppingCar [productId=" + productId + ", amount=" + amount
				+ "]";
	}
}
