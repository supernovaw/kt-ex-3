package com.example.ktex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val post = Post(
			"G'day mate \uD83C\uDDE6\uD83C\uDDFA",
			"Sep 12, 2021",
			likes = 50,
			liked = true,
			comments = 8,
			shares = 2,
			shared = true
		)
		val root = findViewById<View>(android.R.id.content).rootView
		initPost(post, root)
	}

	private fun initPost(p: Post, v: View) {
		v.findViewById<TextView>(R.id.post_content_text).text = p.textContent
		v.findViewById<TextView>(R.id.post_date).text = p.date

		v.findViewById<TextView>(R.id.post_likes_count).text =
			if (p.likes > 0) p.likes.toString() else ""
		v.findViewById<TextView>(R.id.post_comments_count).text =
			if (p.comments > 0) p.comments.toString() else ""
		v.findViewById<TextView>(R.id.post_shares_count).text =
			if (p.shares > 0) p.shares.toString() else ""

		if (p.liked) {
			v.findViewById<TextView>(R.id.post_likes_count).setTextColor(0xffbc002d.toInt())
			v.findViewById<ImageView>(R.id.post_like_icon).setImageResource(R.drawable.like_on_24)
		}
		if (p.commented) v.findViewById<TextView>(R.id.post_comments_count)
			.setTextColor(0xffbc002d.toInt())
		if (p.shared) v.findViewById<TextView>(R.id.post_shares_count)
			.setTextColor(0xffbc002d.toInt())
	}
}