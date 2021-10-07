package com.example.ktex3

class AddressedPost(
	authorName: String,
	profilePicture: Int,
	textContent: String,
	date: String,
	val address: String,
	val coordinatesLatLng: Pair<Double, Double>,
	likes: Int,
	liked: Boolean = false,
	comments: Int,
	commented: Boolean = false,
	shares: Int,
	shared: Boolean = false
) : Post(
	authorName,
	profilePicture,
	textContent,
	date,
	likes,
	liked,
	comments,
	commented,
	shares,
	shared
)