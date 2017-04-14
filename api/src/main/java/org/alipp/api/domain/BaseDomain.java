package org.alipp.api.domain;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("rawtypes")
public abstract class BaseDomain <T extends BaseDomain> extends Model<T>{

	private static final long serialVersionUID = 8377988724565169983L;
	
	@SuppressWarnings("unchecked")
	public T dao() {
		return (T)this;
	}
	
}
