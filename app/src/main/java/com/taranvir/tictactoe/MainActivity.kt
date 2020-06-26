package com.taranvir.tictactoe

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class PLAYINGPLAYERS{

        FIRST_PLAYER,
        SECOND_PLAYER
    }

    enum class WINNER_OF_GAME{

        PLAYER_ONE,
        PLAYER_TWO,
        NO_ONE
    }

    //Inastance Variables

    var playingPlayer: PLAYINGPLAYERS? = null
    var winnerOfGame : WINNER_OF_GAME? = null
    var player1Options: ArrayList<Int> = ArrayList()
    var player2Options: ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{
            startService(Intent(this@MainActivity, SoundService::class.java))

        }catch(e:Exception){
            e.printStackTrace()
        }
        playingPlayer = PLAYINGPLAYERS.FIRST_PLAYER
    }

    fun imageButtonTapped(view: View) {

        val selectedImageButton: ImageButton = view as ImageButton
        var randomNumber = (Math.random() * 9 + 1).toInt()

        when (randomNumber) {

            1 -> tableLayout.setBackgroundColor(Color.YELLOW)
            2 -> tableLayout.setBackgroundColor(Color.CYAN)
            3 -> tableLayout.setBackgroundColor(Color.GREEN)
            4 -> tableLayout.setBackgroundColor(Color.DKGRAY)
            5 -> tableLayout.setBackgroundColor(Color.MAGENTA)
            6 -> tableLayout.setBackgroundColor(Color.BLUE)
            7 -> tableLayout.setBackgroundColor(Color.RED)
            8 -> tableLayout.setBackgroundColor(Color.LTGRAY)
            9 -> tableLayout.setBackgroundColor(Color.WHITE)
        }

        var optionNumber = 0

        when (selectedImageButton.id) {

            R.id.imageButton -> optionNumber = 1
            R.id.imageButton1 -> optionNumber = 2
            R.id.imageButton2 -> optionNumber = 3
            R.id.imageButton3 -> optionNumber = 4
            R.id.imageButton4 -> optionNumber = 5
            R.id.imageButton5 -> optionNumber = 6
            R.id.imageButton6 -> optionNumber = 7
            R.id.imageButton7 -> optionNumber = 8
            R.id.imageButton8 -> optionNumber = 9
        }

        action(optionNumber, selectedImageButton)
    }

    private fun action(optionNumber: Int, _selectedImageButton: ImageButton){

        var selectedImageButton = _selectedImageButton

        if(playingPlayer == PLAYINGPLAYERS.FIRST_PLAYER){
            selectedImageButton.setImageResource(R.drawable.x_letter)
            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            playingPlayer = PLAYINGPLAYERS.SECOND_PLAYER

        }

        if(playingPlayer == PLAYINGPLAYERS.SECOND_PLAYER){
//            selectedImageButton.setImageResource(R.drawable.o_letter)
//            player2Options.add(optionNumber)
//            selectedImageButton.isEnabled = false
//            allDisabledImages.add(selectedImageButton)
//            playingPlayer = PLAYINGPLAYERS.FIRST_PLAYER

            //Algorithm for playing with the Computer
            var notSelectedImageNumbers = ArrayList<Int>()

            for (imageNumber in 1..9){

                if(!(player1Options.contains(imageNumber))){
                    if(!(player2Options.contains(imageNumber))){

                        notSelectedImageNumbers.add(imageNumber)
                    }
                }
            }

            try {

                var randomNumber = ((Math.random() * notSelectedImageNumbers.size)).toInt()
                var imageNumber = notSelectedImageNumbers[randomNumber]

                when(imageNumber){

                    1-> selectedImageButton = imageButton
                    2-> selectedImageButton = imageButton1
                    3-> selectedImageButton = imageButton2
                    4-> selectedImageButton = imageButton3
                    5-> selectedImageButton = imageButton4
                    6-> selectedImageButton = imageButton5
                    7-> selectedImageButton = imageButton6
                    8-> selectedImageButton = imageButton7
                    9-> selectedImageButton = imageButton8

                }
                selectedImageButton.setImageResource(R.drawable.o_letter)
                player2Options.add(imageNumber)
                selectedImageButton.isEnabled=false
                allDisabledImages.add(selectedImageButton)
                playingPlayer = PLAYINGPLAYERS.FIRST_PLAYER

            }catch (e:Exception){
                  e.printStackTrace()
            }

        }

        specifyTheWinnerOfGame()

    }

    private fun specifyTheWinnerOfGame(){

        if(player1Options.contains(1) && player1Options.contains(2) && player1Options.contains(3)){

            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        }
        else if(player2Options.contains(1) && player2Options.contains(2) && player2Options.contains(3)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }
        else if(player1Options.contains(4) && player1Options.contains(5) && player1Options.contains(6)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        }
        else if(player2Options.contains(4) && player2Options.contains(5) && player2Options.contains(6)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO

        }
        else if(player1Options.contains(7) && player1Options.contains(8) && player1Options.contains(9)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE

        }
        else if(player2Options.contains(7) && player2Options.contains(8) && player2Options.contains(9)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }
        else if(player1Options.contains(1) && player1Options.contains(4) && player1Options.contains(7)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }
        else if(player2Options.contains(1) && player2Options.contains(4) && player2Options.contains(7)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }
        else if(player1Options.contains(2) && player1Options.contains(5) && player1Options.contains(8)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }
        else if(player2Options.contains(2) && player2Options.contains(5) && player2Options.contains(8)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

        else if(player1Options.contains(3) && player1Options.contains(6) && player1Options.contains(9)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }

        else if(player2Options.contains(3) && player2Options.contains(6) && player2Options.contains(9)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

        else if(player1Options.contains(1) && player1Options.contains(5) && player1Options.contains(9)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }

        else if(player2Options.contains(1) && player2Options.contains(5) && player2Options.contains(9)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

        else if(player1Options.contains(3) && player1Options.contains(5) && player1Options.contains(7)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }

        else if(player2Options.contains(3) && player2Options.contains(5) && player2Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

        if(winnerOfGame == WINNER_OF_GAME.PLAYER_ONE){
            createAlert("Player One Grabs the Game!",
                "Congratulations to the Player 1",
                AlertDialog.BUTTON_POSITIVE,"OK", false
            )
            return

        }else if(winnerOfGame == WINNER_OF_GAME.PLAYER_TWO){
            createAlert("Player two Grabs the Game!",
                "Congratulations to the Player 2",
                AlertDialog.BUTTON_POSITIVE,"OK", false
            )
            return
        }
        checkDrawState()

    }

    private fun createAlert(title: String, message: String, whichPositive: Int, buttonText: String, cancelable: Boolean) {

        val alertDialog:AlertDialog = AlertDialog.Builder(this@MainActivity).create()

        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setButton(whichPositive, buttonText){

            dialog:DialogInterface?, which: Int ->

                resetGame()
        }

        alertDialog.setCancelable(cancelable)

        alertDialog.show()

    }

    private fun resetGame() {

        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winnerOfGame = WINNER_OF_GAME.NO_ONE
        playingPlayer = PLAYINGPLAYERS.FIRST_PLAYER

        imageButton.setImageResource(R.drawable.place_holder)
        imageButton1.setImageResource(R.drawable.place_holder)
        imageButton2.setImageResource(R.drawable.place_holder)
        imageButton3.setImageResource(R.drawable.place_holder)
        imageButton4.setImageResource(R.drawable.place_holder)
        imageButton5.setImageResource(R.drawable.place_holder)
        imageButton6.setImageResource(R.drawable.place_holder)
        imageButton7.setImageResource(R.drawable.place_holder)
        imageButton8.setImageResource(R.drawable.place_holder)

        imageButton.isEnabled=true
        imageButton1.isEnabled=true
        imageButton2.isEnabled=true
        imageButton3.isEnabled=true
        imageButton4.isEnabled=true
        imageButton5.isEnabled=true
        imageButton6.isEnabled=true
        imageButton7.isEnabled=true
        imageButton8.isEnabled=true

    }

    private fun checkDrawState(){

        if(allDisabledImages.size ==9 && winnerOfGame!=WINNER_OF_GAME.PLAYER_ONE && winnerOfGame!=WINNER_OF_GAME.PLAYER_TWO ){

            createAlert("IT'S A DRAW!","No one wins the game!!",AlertDialog.BUTTON_POSITIVE,"OK",false)
        }
    }
}
