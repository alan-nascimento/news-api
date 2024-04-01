# News API

This project implements a simple REST API framework in Java using the Spring Boot. The API interacts with a public news API (in this example, [GNews API](https://gnews.io/docs/v4#introduction)) for fetching articles.

## Features

-   Fetching News Articles: Users can retrieve news articles based on various parameters such as query, title, author, and count.
-   Caching Mechanism: The API includes a caching mechanism to store previously fetched articles, reducing redundant requests and improving performance.
-   Flexible Endpoint: Designed to provide flexibility in searching for news articles based on different criteria.
-   Easy Integration: Developed using the Spring framework, making it easy to integrate with existing Java applications or frameworks.

## API Endpoint

```sh
GET /api/news/search
```

-   Description: Retrieves news articles based on search parameters.
-   Parameters:
    -   `query` (required): Keyword or phrase to search for in news articles.
    -   `title` (optional): Search articles by title.
    -   `author` (optional): Search articles by author.
    -   `count` (optional, default=10): Number of articles to fetch (maximum).
-   Response: Returns a list of news articles matching the search criteria.

## Usage

Pre-requisites:

-   Docker
-   Docker Compose

Instructions:

1. Setup `.env` file, you can use `.env.template` as a reference.

2. Run the following command to start the API server:

```sh
docker compose --env-file .env up
```

3. Access the API endpoints using a REST client or browser. Example:

```sh
curl -X GET 'http://localhost:8080/api/news/search?query=test&title=Example%20Title&count=10'
```

## Response Examples

### Success

```json
{
    "articles": [
        {
            "title": "Example Title",
            "description": "This is an example description.",
            "content": "This is an example content.",
            "url": "https://example.com/article",
            "publishedAt": "2024-04-01T18:09:26.145+00:00",
            "source": {
                "name": "Example",
                "url": "https://example.com"
            }
        }
    ]
}
```

### Bad Request

```json
{
    "timestamp": "2024-04-01T18:09:26.145+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/api/news/search"
}
```

### Internal Server Error

```json
{
    "timestamp": "2024-04-01T18:09:26.145+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "An error occurred while fetching news articles.",
    "path": "/api/news/search"
}
```

## Dependencies

-   Spring Boot
-   Spring Web
-   Spring Cache
-   Lombok
-   Apache Commons Lang

## Author & Version Control

Developed by **Alan Nascimento**. Published in April 2024.
