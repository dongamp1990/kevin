package org.alipp.qt.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.alipp.qt.domain.CustomerInfo;
import org.alipp.qt.util.StringUtil;

import com.jfinal.aop.Clear;

public class IndexController extends BaseController {
	@Clear
	public void index() {
		render("index.jsp");
	}
	@Clear
	public void search() {
		String qqNumber = getPara("qqNumber");
		if (StringUtil.isNotBlank(qqNumber)) {
			try {
				qqNumber = new String( qqNumber.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select * from customer_info c ")
		   .append("left join level_code lc on lc.code_id = c.level_code ")
		   .append("where c.qq_number = ? ");
		List<CustomerInfo> infos = CustomerInfo.dao.find(sql.toString(), qqNumber);	
		if (infos != null && !infos.isEmpty()) {
			CustomerInfo info = infos.get(0);
			if (info.get("phone_number") != null && StringUtil.isNotBlank(info.get("phone_number").toString())) {
				String phoneNumber = info.get("phone_number").toString();
				if (phoneNumber.length() >= 11) {
					String start = phoneNumber.substring(0, 3);
					String end = phoneNumber.substring(7, phoneNumber.length());
					info.set("phone_number", start + "****" + end);
				}else {
					info.set("phone_number", "****");
				}
			}
			setAttr("info", infos.get(0));
		}
		setAttr("qqNumber", qqNumber);
		render("search.jsp");
	}
}
