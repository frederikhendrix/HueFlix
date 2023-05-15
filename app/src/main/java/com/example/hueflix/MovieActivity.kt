package com.example.hueflix

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MovieActivity : AppCompatActivity() {

    private lateinit var imageViewInglorious: ImageView
    private lateinit var imageViewHotFuzz: ImageView
    private lateinit var imageViewTheGrandBudapestHotel: ImageView
    private lateinit var imageViewInBruges: ImageView
    private lateinit var popupWindow: PopupWindow
    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>

    val InBrugesEmotions = arrayOf(
        EmotionData("Humor", 40, "Green", "#00bf63"),
        EmotionData("Tension", 20, "Red", "#ff3131"),
        EmotionData("Regret", 15, "Blue", "#38b6ff"),
        EmotionData("Guilt", 10, "Purple", "#cb6ce6"),
        EmotionData("Melancholy", 8, "Gray", "#a6a6a6"),
        EmotionData("Anger", 5, "Orange", "#ff914d"),
        EmotionData("Redemption", 2, "Yellow", "#ffde59")
    )

    val HotFuzz2Emotions = arrayOf(
        EmotionData("Humor", 50, "Green", "#00bf63"),
        EmotionData("Action", 20, "Red", "#ff3131"),
        EmotionData("Parody", 10, "Purple", "#cb6ce6"),
        EmotionData("Suspense", 5, "Blue", "#38b6ff"),
        EmotionData("Friendship", 5, "Yellow", "#ffde59"),
        EmotionData("Mystery", 4, "Gray", "#a6a6a6"),
        EmotionData("Surprise", 3, "Orange", "#ff914d"),
        EmotionData("Satisfaction", 3, "Pink", "#ff66c4")
    )

    val TheGrandBudapestHotelEmotions = arrayOf(
        EmotionData("Humor", 35, "Green", "#00bf63"),
        EmotionData("Adventure", 20, "Red", "#ff3131"),
        EmotionData("Whimsy", 15, "Blue", "#38b6ff"),
        EmotionData("Nostalgia", 10, "Yellow", "#ffde59"),
        EmotionData("Romance", 5, "Pink", "#ff66c4"),
        EmotionData("Melancholy", 5, "Gray", "#a6a6a6"),
        EmotionData("Intrigue", 5, "Purple", "#cb6ce6"),
        EmotionData("Tension", 3, "Orange", "#ff914d"),
        EmotionData("Satisfaction", 2, "Brown", "#ac663e")
    )

    val IngloriousBastardsEmotions = arrayOf(
        EmotionData("Tension", 40, "Red", "#ff3131"),
        EmotionData("Fear", 20, "Red", "#ff3131"),
        EmotionData("Anger", 15, "Red", "#ff3131"),
        EmotionData("Revenge", 15, "Red", "#ff3131"),
        EmotionData("Empowerment", 5, "Blue", "#38b6ff"),
        EmotionData("Satisfaction", 5, "Green", "#00bf63"),
        EmotionData("Humor", 3, "Yellow", "#ffde59"),
        EmotionData("Desperation", 3, "Gray", "#a6a6a6"),
        EmotionData("Grief", 2, "Black", "#000000"),
        EmotionData("Hope", 2, "Blue", "#38b6ff")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        imageViewInglorious = findViewById(R.id.pieInglorious)
        imageViewInglorious.setOnClickListener{showPopup(IngloriousBastardsEmotions)}


        imageViewHotFuzz = findViewById(R.id.pieHotFuzz)
        imageViewHotFuzz.setOnClickListener{showPopup(HotFuzz2Emotions)}

        imageViewTheGrandBudapestHotel = findViewById(R.id.pieTheGrandBudapestHotel)
        imageViewTheGrandBudapestHotel.setOnClickListener{showPopup(TheGrandBudapestHotelEmotions)}

        imageViewInBruges = findViewById(R.id.pieInBurges)
        imageViewInBruges.setOnClickListener{showPopup(InBrugesEmotions)}

        spinner = findViewById(R.id.spinner)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, getList(IngloriousBastardsEmotions.toList()))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter


    }


    private fun showPopup(data: Array<EmotionData>) {
        val popupView = layoutInflater.inflate(R.layout.popup, null)
        val popupRecyclerView: RecyclerView = popupView.findViewById(R.id.recyclerview)
        popupRecyclerView.layoutManager = LinearLayoutManager(this)

        // Create an instance of your custom adapter and pass the data to it
        val popupAdapter = CustomAdapter(data.toList())
        popupRecyclerView.adapter = popupAdapter

        val popupWindow = PopupWindow(popupView, 350.dpToPx(), 450.dpToPx())

        val overlayLayout = View(this)
        overlayLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        overlayLayout.setBackgroundColor(Color.TRANSPARENT)
        overlayLayout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                popupWindow.dismiss() // Dismiss the popup window when touch event is detected
            }
            true
        }

        val rootView = findViewById<ViewGroup>(android.R.id.content)
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0)
        rootView.addView(overlayLayout)

        popupWindow.setOnDismissListener {
            rootView.removeView(overlayLayout) // Remove the overlay layout when the popup window is dismissed
        }
    }

    fun Int.dpToPx(): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

    private fun showPopup() {
        popupWindow.showAsDropDown(imageViewInglorious)
    }

    private fun getList(list: List<EmotionData>): List<String>{

        val processedList = mutableListOf<String>()

        for(item in list)
        {
            val processedVarPlus = item.emotion + " +"
            val processedVarMin = item.emotion + " -"
            processedList.add(processedVarPlus)
            processedList.add(processedVarMin)
        }

        return processedList
    }
}