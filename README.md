# Blogging App

## JSON Entities

### User

{
"username": 	"shivam",
"email":			"",
"bio": 				"",
"image":			"",
"password":	"xxxx",
"id": 		1
}
### Article

{
"title": 			"Adidas | nike",
"subtitle": 		"Every day i think about sports",
"body":			"Sports is for everyone",
"createdAt": 	"2023-09-14 03:05:22"
"tags":				["sports"],
"slug": 			"how sports is good",
"id": 		1
}

### Comment

{
"title": "great article",
"body": "amazing article",
"createdAt": 	"2023-09-14 03:05:22"
"id": 		1
}

## API Endpoint

### `POST /user`
create a new user

### `GET /profiles`
### `GET /profiles/{username}`


### `GET /articles?tag=sports`
get all articles
available filters
- `/articles?tag=stocks`
- `/articles?author=shivam`
- `/articles?page=3&size=10`

### `GET /articles/{article-slug}`

### `POST /articles`
create a new article

### `PATCH /articles/{article-slug}`
edit an article

### `GET /article/{article-slug}/comments`
get all comments of an article

### `POST /article/{article-slug}/comments`
create comments for an article

### `DELETE /article/{article-slug}/comments/{comment-id}`
