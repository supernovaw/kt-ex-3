package com.example.ktex3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val post = Post(
			"supernovaw",
			R.drawable.panpfp,
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
		v.findViewById<TextView>(R.id.post_author).text = p.authorName
		v.findViewById<ImageView>(R.id.post_profile_picture).setImageResource(p.profilePicture)
		v.findViewById<TextView>(R.id.post_content_text).text = p.textContent
		v.findViewById<TextView>(R.id.post_date).text = p.date
		updatePostButtonsStatus(p, v)

		val likeIcon = v.findViewById<ImageView>(R.id.post_like_icon)
		val likesCount = v.findViewById<TextView>(R.id.post_likes_count)

		val likeListener: (it: View?) -> Unit = {
			if (p.liked) p.likes-- else p.likes++
			p.liked = !p.liked
			updatePostButtonsStatus(p, v)
		}
		likeIcon.setOnClickListener(likeListener)
		likesCount.setOnClickListener(likeListener)
	}

	private fun updatePostButtonsStatus(p: Post, v: View) {
		val red = 0xffbc002d.toInt()
		val gray = 0x8a000000.toInt()
		val likeIcon = v.findViewById<ImageView>(R.id.post_like_icon)
		val likesCount = v.findViewById<TextView>(R.id.post_likes_count)
		val commentsCount = v.findViewById<TextView>(R.id.post_comments_count)
		val sharesCount = v.findViewById<TextView>(R.id.post_shares_count)

		likesCount.text = if (p.likes > 0) p.likes.toString() else ""
		commentsCount.text = if (p.comments > 0) p.comments.toString() else ""
		sharesCount.text = if (p.shares > 0) p.shares.toString() else ""

		likesCount.setTextColor(if (p.liked) red else gray)
		likeIcon.setImageResource(if (p.liked) R.drawable.like_on_24 else R.drawable.like_regular_24)
		commentsCount.setTextColor(if (p.commented) red else gray)
		sharesCount.setTextColor(if (p.shared) red else gray)
	}
}