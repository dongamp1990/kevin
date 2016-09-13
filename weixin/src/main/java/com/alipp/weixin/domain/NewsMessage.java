package com.alipp.weixin.domain;

import java.util.List;

public class NewsMessage extends BaseMessage {

	private List<Article> Articles;

	private String ArticleCount;

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

}
