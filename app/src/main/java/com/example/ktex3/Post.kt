package com.example.ktex3

class Post(
	val textContent: String,
	val date: String,
	val likes: Int,
	val liked: Boolean = false,
	val comments: Int,
	val commented: Boolean = false,
	val shares: Int,
	val shared: Boolean = false
)