package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent
import scalafx.scene.image.Image
import scalafx.animation.AnimationTimer

object WindowTest extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Sample window!"
        scene = new Scene(800,800) {
            val canvas = new Canvas(800,800)
            content = canvas
            val g = canvas.graphicsContext2D

            var posx = 400.0
            var posy = 400.0
            var velx = math.random * 10 - 5
            var vely = math.random * 10 - 5
            val timer = AnimationTimer(t => {
                g.setFill(Color.White)
                g.fillRect(0,0, 800,800)
                g.setFill(Color.Red)
                g.fillOval(posx-50,posy-50, 100,100)
                if(posy < 50 || posy > 750) vely = -vely
                if(posx < 50 || posx > 750) velx *= -1
                posx += velx
                posy += vely
            })
            timer.start
        }
    }
}