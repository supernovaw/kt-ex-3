package com.example.ktex3

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class PostAdapter(private val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false)
		return PostViewHolder(view)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		with(holder.itemView) {
			val post = list[position]
			initPost(post, this)
		}
	}

	override fun getItemCount(): Int = list.size

	private fun initPost(p: Post, v: View) {
		v.findViewById<TextView>(R.id.post_author).text = p.authorName
		v.findViewById<ImageView>(R.id.post_profile_picture).setImageResource(p.profilePicture)
		v.findViewById<TextView>(R.id.post_content_text).text = p.textContent
		v.findViewById<TextView>(R.id.post_date).text = p.date
		updatePostButtonsStatus(p, v)

		val likeIcon = v.findViewById<ImageView>(R.id.post_like_icon)
		val likesCount = v.findViewById<TextView>(R.id.post_likes_count)
		val locationIcon = v.findViewById<ImageView>(R.id.location_icon)

		val likeListener: (it: View?) -> Unit = {
			if (p.liked) p.likes-- else p.likes++
			p.liked = !p.liked
			updatePostButtonsStatus(p, v)
		}
		likeIcon.setOnClickListener(likeListener)
		likesCount.setOnClickListener(likeListener)
		locationIcon.visibility = if (p is AddressedPost) View.VISIBLE else View.GONE
		if (p is AddressedPost) {
			locationIcon.setOnClickListener {
				val uri = "geo:${p.coordinatesLatLng.first},${p.coordinatesLatLng.second}"
				v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
			}
			locationIcon.setOnLongClickListener {
				Snackbar.make(v.context, v, p.address, Snackbar.LENGTH_SHORT).show()
				true
			}
		}
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

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)