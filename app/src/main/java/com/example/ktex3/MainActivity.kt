package com.example.ktex3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

		val postsList = emptyList<Post>().toMutableList()
		for (i in 0..10) {
			postsList.add(
				AddressedPost(
					"supernovaw",
					R.drawable.panpfp,
					"A very unique post #${Math.random().times(100).toInt()}",
					"Sep ${Math.random().times(15).toInt() + 8}, 2021",
					address = "Portland Int'l Airport",
					coordinatesLatLng = 45.59 to -122.59,
					likes = Math.random().times(50).toInt() + 1,
					liked = Math.random() < .3,
					comments = Math.random().times(20).toInt(),
					shares = Math.random().times(8).toInt(),
					shared = Math.random() < .225
				)
			)
		}
		with(posts_list) {
			layoutManager = LinearLayoutManager(this@MainActivity)
			adapter = PostAdapter(postsList)
		}
	}
}