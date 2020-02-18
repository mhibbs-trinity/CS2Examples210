package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scala.collection.mutable

class ParticleSystem(protected var origin:Vec2) {
    protected var particles:mutable.Buffer[Particle] = mutable.Buffer[Particle]()

    def addParticle():Unit = {
        particles += new RainbowParticle(origin.clone,
                             new Vec2(math.random * 10 -5,
                                      math.random * 10 -5))
        /*
        particles += new SquareParticle(origin.clone,
                             new Vec2(math.random * 10 -5,
                                      math.random * 10 -5))
        */
        println(particles.length)
    }

    def display(g:GraphicsContext):Unit = {
        for(p <- particles) {
            p.display(g)
        }
    }

    def timeStep(erasers:mutable.Buffer[Eraser]):Unit = {
        particles.foreach(_.timeStep)
        particles.filter(_.isOffScreen)
        if(erasers.length > 0) {
            var i = 0
            while(i < particles.length) {
                var hit = false
                if(erasers.map(_.overlaps(particles(i))).reduce(_||_)) particles.remove(i)
                else i += 1
            }
        }
    }

    def applyForce(acc:Vec2):Unit = {
        for(p <- particles) {
            p.applyForce(acc)
        }
    }
    
}