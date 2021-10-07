package com.example.ktex3

class Post(
	val authorName: String,
	val profilePicture: Int,
	val textContent: String,
	val date: String,
	var likes: Int,
	var liked: Boolean = false,
	var comments: Int,
	var commented: Boolean = false,
	var shares: Int,
	var shared: Boolean = false
)