package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class SquareParticle(p:Vec2, v:Vec2) extends Particle(p,v) {
    override def display(g:GraphicsContext):Unit = {
        g.setFill(col)
        g.fillRect(pos.x, pos.y, 2*r,2*r)
    }
}