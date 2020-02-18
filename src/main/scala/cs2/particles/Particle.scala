package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

abstract class Particle(protected var pos:Vec2, private var vel:Vec2) {
    protected var r = 10.0
    protected var col = Color.Black

    def radius():Double = r
    def position():Vec2 = pos.clone

    def display(g:GraphicsContext):Unit

    def timeStep():Unit = {
        pos += vel
    }

    def applyForce(acc:Vec2):Unit = {
        vel += acc
    }

    def isOffScreen():Boolean = {
        pos.x < 0 || pos.x > 600 || pos.y < 0 || pos.y > 600
    }

}