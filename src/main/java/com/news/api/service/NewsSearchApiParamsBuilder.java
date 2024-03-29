package com.news.api.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewsSearchApiParamsBuilder {

    private final String gnewsApiUrl;

    private final String gnewsApiKey;

    private final String query;

    private final String title;

    private final String author;

    private final int count;

    public String build() {
        StringBuilder apiUrlBuilder = new StringBuilder(gnewsApiUrl)
            .append("?apikey=").append(gnewsApiKey)
            .append("&max=").append(count);

        if (query != null && !query.isEmpty()) {
            apiUrlBuilder.append("&q=").append(query);
        }
        if (title != null && !title.isEmpty()) {
            apiUrlBuilder.append("&title=").append(title);
        }
        if (author != null && !author.isEmpty()) {
            apiUrlBuilder.append("&author=").append(author);
        }

        return apiUrlBuilder.toString();
    }
}
