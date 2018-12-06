package orq.nail.paper_stone_scissors

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.preference.PreferenceManager
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_paper_stone_scissors.*
import kotlinx.android.synthetic.main.activity_player_name.*
import android.support.v4.content.ContextCompat.startActivity



class player_name : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_name)

        save_player_name_button.setOnClickListener() {

            save_player_name()

            val myIntent = Intent(this@player_name, PaperStoneScissors::class.java)
            //myIntent.putExtra("key", value) //Optional parameters
            this@player_name.startActivity(myIntent)
        }



    }//end of onCreate

        fun save_player_name () {

            val user_name_sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = user_name_sharedPreferences.edit()

            val player_name_string: String

            if (insert_player_name_editText.text.isNotEmpty()) {
                player_name_string = insert_player_name_editText.text.toString()
            } else {
                player_name_string = "player"
            }

            editor.putString("new_player_name", player_name_string)
            editor.apply()
        }//end of save function



}
