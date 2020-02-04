package cs2.particles

import cs2.util.Vec2
import scalafx.scene.paint.Color

class RainbowParticle(p:Vec2, v:Vec2) extends RoundParticle(p, v) {
    protected var hue:Double = 0.0

    override def timeStep():Unit = {
        super.timeStep()
        col = Color.hsb(hue, 1.0, 1.0)
        hue += 10.0
    }

}