package org.chicagoscala.awse.boot
import org.chicagoscala.awse.server._
import org.chicagoscala.awse.server.persistence._
import org.chicagoscala.awse.server.finance._
import org.chicagoscala.awse.server.rest._
import se.scalablesolutions.akka.actor._
import se.scalablesolutions.akka.actor.Actor._
import se.scalablesolutions.akka.config.ScalaConfig._
import se.scalablesolutions.akka.config.OneForOneStrategy
import se.scalablesolutions.akka.util.Logging

class BootAWSESupervisor {
  val factory = SupervisorFactory(
    SupervisorConfig(
      RestartStrategy(OneForOne, 5, 5000, List(classOf[Throwable])),
    Supervise(
      actorOf(new InstrumentAnalysisServerSupervisor),
      LifeCycle(Permanent)) :: 
    Nil))
      
  factory.newInstance.start
}
