package orq.nail.paper_stone_scissors

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.graphics.Color
import kotlinx.android.synthetic.main.activity_paper_stone_scissors.*


class PaperStoneScissors : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_stone_scissors)

        val intent = intent


        new_player_name_label_textView.text = load_data_one_item_string("new_player_name")
        //*******************************************************************
        //*******************************************************************
        best_result_player_point_textView.setText(load_data_one_item_int("best_new_player_result_points").toString())
        //*******************************************************************
        best_result_point_player_vs_robot_textView.setText(load_data_one_item_int("best_new_player_vs_robot_result_points").toString())
        //*******************************************************************
        best_result_player_name_textView.text = load_data_one_item_string("best_winner_name")
        //*******************************************************************




        user_selections_radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(user_stone_RadioButton.isChecked || user_paper_RadioButton.isChecked || user_scissors_RadioButton.isChecked) {
                calculate_round()
            }

        }

        another_round_button.setOnClickListener(){
            robot_stone_RadioButton.visibility= View.INVISIBLE
            robot_paper_RadioButton.visibility= View.INVISIBLE
            robot_scissors_RadioButton.visibility= View.INVISIBLE

            robot_stone_label_textView.visibility= View.INVISIBLE
            robot_paper_label_textView.visibility= View.INVISIBLE
            robot_scissors_label_textView.visibility= View.INVISIBLE

            user_stone_RadioButton.visibility= View.VISIBLE
            user_paper_RadioButton.visibility= View.VISIBLE
            user_scissors_RadioButton.visibility= View.VISIBLE

            user_stone_label_textView.visibility= View.VISIBLE
            user_paper_label_textView.visibility= View.VISIBLE
            user_scissors_labeltextView.visibility= View.VISIBLE

            round_result_label_textView.text=""

            user_stone_RadioButton.isChecked=false
            user_paper_RadioButton.isChecked=false
            user_paper_RadioButton.isChecked=false

            user_selections_radioGroup.clearCheck()
            robot_selections_radioGroup.clearCheck()

        }//end of another_round_button setOnClick

        clear_history_button.setOnClickListener(){

            save_data_one_item_string("best_winner_name","player")
            best_result_player_name_textView.text ="player"

            save_data_one_item_int("best_new_player_result_points",0)
            best_result_player_point_textView.text="0"

            save_data_one_item_int("best_new_player_vs_robot_result_points",0)
            best_result_point_player_vs_robot_textView.text="0"
        }

        new_game_button.setOnClickListener(){
            robot_points_textView.text ="0"
            player_points_textView.text="0"
        }

    }//end of onCreate

    fun calculate_round()
    {

        var round_result_user_int = 0

        val random_number = (1..3).shuffled().first()
        when(random_number)
        {
            1 ->{
                robot_stone_RadioButton.isChecked=true
                robot_stone_RadioButton.visibility= View.VISIBLE
                robot_paper_RadioButton.visibility= View.INVISIBLE
                robot_scissors_RadioButton.visibility= View.INVISIBLE
                robot_stone_label_textView.visibility= View.VISIBLE
                robot_paper_label_textView.visibility= View.INVISIBLE
                robot_scissors_label_textView.visibility= View.INVISIBLE
            }
            2 ->{
                robot_scissors_RadioButton.isChecked=true
                robot_stone_RadioButton.visibility= View.INVISIBLE
                robot_scissors_RadioButton.visibility= View.VISIBLE
                robot_paper_RadioButton.visibility= View.INVISIBLE

                robot_stone_label_textView.visibility= View.INVISIBLE
                robot_paper_label_textView.visibility= View.INVISIBLE
                robot_scissors_label_textView.visibility= View.VISIBLE
            }
            3 ->{
                robot_paper_RadioButton.isChecked=true
                robot_stone_RadioButton.visibility= View.INVISIBLE
                robot_scissors_RadioButton.visibility= View.INVISIBLE
                robot_paper_RadioButton.visibility= View.VISIBLE

                robot_stone_label_textView.visibility= View.INVISIBLE
                robot_paper_label_textView.visibility= View.VISIBLE
                robot_scissors_label_textView.visibility= View.INVISIBLE
            }
        }


        when(user_selections_radioGroup.checkedRadioButtonId) {
            user_stone_RadioButton.id ->{

                //best_result_textView.setText("hello") // test notWorking

                if(robot_scissors_RadioButton.isChecked) {round_result_user_int =1 }
                else if(robot_paper_RadioButton.isChecked){round_result_user_int=-1 }

                user_stone_RadioButton.visibility= View.VISIBLE
                user_paper_RadioButton.visibility= View.INVISIBLE
                user_scissors_RadioButton.visibility= View.INVISIBLE
                user_stone_label_textView.visibility= View.VISIBLE
                user_paper_label_textView.visibility= View.INVISIBLE
                user_scissors_labeltextView.visibility= View.INVISIBLE
            }
            user_paper_RadioButton.id ->{

                if(robot_scissors_RadioButton.isChecked) {round_result_user_int =-1}
                else if(robot_stone_RadioButton.isChecked){round_result_user_int=1}

                user_stone_RadioButton.visibility= View.INVISIBLE
                user_paper_RadioButton.visibility= View.VISIBLE
                user_scissors_RadioButton.visibility= View.INVISIBLE
                user_stone_label_textView.visibility= View.INVISIBLE
                user_paper_label_textView.visibility= View.VISIBLE
                user_scissors_labeltextView.visibility= View.INVISIBLE
            }
            user_scissors_RadioButton.id ->{

                if(robot_paper_RadioButton.isChecked) {round_result_user_int =1}
                else if(robot_stone_RadioButton.isChecked) {round_result_user_int =-1}

                user_stone_RadioButton.visibility= View.INVISIBLE
                user_paper_RadioButton.visibility= View.INVISIBLE
                user_scissors_RadioButton.visibility= View.VISIBLE
                user_stone_label_textView.visibility= View.INVISIBLE
                user_paper_label_textView.visibility= View.INVISIBLE
                user_scissors_labeltextView.visibility= View.VISIBLE
            }


        }

        round_result_label_textView.setText(round_result_user_int.toString())

        when(round_result_user_int)
        {
            1 -> {player_points_textView.text = (player_points_textView.text.toString().toInt()+1).toString()
                round_result_label_textView.text="WIN"
                round_result_label_textView.setTextColor(Color.GREEN)
            } //win
            0 -> {
                round_result_label_textView.text="EQUAL"
                round_result_label_textView.setTextColor(Color.BLACK)
            } // similler
            -1 -> {robot_points_textView.text = (robot_points_textView.text.toString().toInt()+1).toString()
                round_result_label_textView.text="LOSE"
                round_result_label_textView.setTextColor(Color.RED)
            } //lose
        }

        another_round_button.setBackgroundColor(Color.YELLOW)
    }//end of round function


    //******************************************
    override fun onStop() {
        super.onStop()
        val best_points_result_sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor_best_points_result = best_points_result_sharedPreferences.edit()

        val best_name_result_sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor_best_name_result = best_name_result_sharedPreferences.edit()




                //new winner   ---name--player_points---robot_points
            if(player_points_textView.text.toString().toInt() > best_result_player_point_textView.text.toString().toInt()) {
                //new result new winner  save the name and the points

                var new_winner_name = load_data_one_item_string("new_player_name")
                save_data_one_item_string("best_winner_name", new_winner_name)

                var best_new_player_result_points = player_points_textView.text.toString().toInt()
                save_data_one_item_int("best_new_player_result_points", best_new_player_result_points)

                val best_new_player_vs_robot_result_points = robot_points_textView.text.toString().toInt()
                editor_best_points_result.putInt(
                    "best_new_player_vs_robot_result_points",
                    best_new_player_vs_robot_result_points
                )

            }//end of if
        //else
          //there is no winner nor best player points





    }//end of onStop


    fun save_data_one_item_string ( key_name : String, value :String)
    {
        val sharedPreferences_save = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences_save.edit()
        editor.putString(key_name,value)
        editor.apply()
    }

    fun save_data_one_item_int ( key_name : String, value :Int)
    {
        val sharedPreferences_save = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences_save.edit()
        editor.putInt(key_name,value)
        editor.apply()
    }

    fun load_data_one_item_string ( key_name : String) : String
    {
        val sharedPreferences_load = PreferenceManager.getDefaultSharedPreferences(this)
        var value = sharedPreferences_load.getString(key_name, "player")
        return value
    }

    fun load_data_one_item_int ( key_name : String) : Int
    {
        val sharedPreferences_load = PreferenceManager.getDefaultSharedPreferences(this)
        var value = sharedPreferences_load.getInt(key_name, 0)
        return value
    }
    //*****************************************


}
