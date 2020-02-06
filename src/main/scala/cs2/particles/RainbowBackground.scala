package cs2.particles

import scalafx.scene.canvas.GraphicsContext

class RainbowBackground(private var w:Double, private var h:Double) extends ColorRotation {
    def display(g:GraphicsContext):Unit = {
        g.setFill(stepColor)
        g.fillRect(0,0, w,h)
    }
}