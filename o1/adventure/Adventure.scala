package o1.adventure

import scala.collection.mutable.Map

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of “hard-coded” information that pertains to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure:

  /** the name of the game */
  val title = "A Forest Adventure"

//  private val middle      = Area("Forest", "You are somewhere in the forest. There are a lot of trees here.\nBirds are singing.")
//  private val northForest = Area("Forest", "You are somewhere in the forest. A tangle of bushes blocks further passage north.\nBirds are singing.")
//  private val southForest = Area("Forest", "The forest just goes on and on.")
//  private val clearing    = Area("Forest Clearing", "You are at a small clearing in the middle of forest.\nNearly invisible, twisted paths lead in many directions.")
//  private val tangle      = Area("Tangle of Bushes", "You are in a dense tangle of bushes. It's hard to see exactly where you're going.")
//  private val home        = Area("Home", "Home sweet home! Now the only thing you need is a working remote control.")
//  private val destination = home
//
//  middle     .setNeighbors(Vector("north" -> northForest, "east" -> tangle, "south" -> southForest, "west" -> clearing   ))
//  northForest.setNeighbors(Vector(                        "east" -> tangle, "south" -> middle,      "west" -> clearing   ))
//  southForest.setNeighbors(Vector("north" -> middle,      "east" -> tangle, "south" -> southForest, "west" -> clearing   ))
//  clearing   .setNeighbors(Vector("north" -> northForest, "east" -> middle, "south" -> southForest, "west" -> northForest))
//  tangle     .setNeighbors(Vector("north" -> northForest, "east" -> home,   "south" -> southForest, "west" -> northForest))
//  home       .setNeighbors(Vector(                                                                  "west" -> tangle     ))
//
//  // TODO: Uncomment the two lines below. Improve the code so that it places the items in clearing and southForest, respectively.
//  clearing.addItem(Item("battery", "It's a small battery cell. Looks new."))
//  southForest.addItem(Item("remote", "It's the remote control for your TV.\nWhat it was doing in the forest, you have no idea.\nProblem is, there's no battery."))
//
//  /** The character that the player controls in the game. */
//  val player = Player(middle)

  private val bedroom0 = Area("Bedroom", "You're in a small room with a low ceiling and a tiny north-facing window. \n" +
  "The only pieces of furniture are your bed stuffed away into the corner, \n" +
    "a small table, writing desk and a bookshelf overflowing with boooks. " +
    "It's always a bit breezy in here.")
  private val hallway0 = Area("Hallway", "A long and narrow hallway stands before you, the corners invisible in \n" +
    "the darkness. The door to the bathroom looms on the left, \n" +
    "with the stairs leading down on the opposite side. A handmade carpet keeps you warm against" +
    "\n the unheated wooden floor.")
  private val bathroom0 = Area("Bathroom", "")
  private val livingroom0 = Area("Living room", "")
  private val kitchen0 = Area("Kitchen", "Your kitchen.")

  bedroom0.setNeighbors(Vector("south" -> hallway0))
  hallway0.setNeighbors(Vector("north" -> bedroom0, "west" -> bathroom0, "downstairs" -> livingroom0))
  bathroom0.setNeighbor("east", hallway0)
  livingroom0.setNeighbors(Vector("upstairs" -> hallway0, "south" ->  kitchen0))
  kitchen0.setNeighbor("north", livingroom0)

  bedroom0.addThingy(InteractableThingy("bed", "Your bed. It's very uncomfortable.", bedroom0))
  bathroom0.addThingy(InteractableThingy("mirror", "You can just barely make out your own reflection under all the dirt. " +
    "\nYou look very tired.", bathroom0))
  hallway0.addThingy(InteractableThingy("window", "It's dark outside.", hallway0))
  livingroom0.addThingy(InteractableThingy("window", "It's dark outside.", livingroom0))
  livingroom0.addThingy(InteractableThingy("television", "You look down at your old-timey box TV. The dark screen reflects your face back at you.", livingroom0))
  kitchen0.addThingy(InteractableThingy("sink", "A metallic sink covered in water stains. " +
    "\nThe water coming from the tap is ice cold.", kitchen0))


  private val kitchen1 = Area("Kitchen", "While enjoying your glass of freezing water, you suddenly hear strange noises coming from the living room.")
  private val livingroom1 = Area("Living room", "A terrible ruckus is coming from the middle of the room.")

  // connects kitchen0 to kitchen1
  kitchen0.getThingies("sink").addSecretExitCommand("get water", "You pour yourself a glass of water from the tap.", kitchen1)

  // add dummy house1 rooms with sounds coming from the living room
  // add connections
  kitchen1.setNeighbor("north", livingroom1)
  livingroom1.setNeighbor("south", kitchen1)
  // add thingies

  livingroom1.addThingy(InteractableThingy("television", "The TV is playing a shoot-off scene from a classic Western movie from your childhood." +
    "\nYou don't remember turning the TV on. Maybe the controls are acting up again?", livingroom1))


  private val livingroom2 = Area("Living room", "It's quiet again. The silence is almost eerie. You're tired, so you decide to go back to sleep." +
    "\nDid the TV really just turn on on its own, though?")
  private val hallway2 = Area("Hallway", "") // you think you glimpse movement outside the window, examining window transports to 3
  private val bathroom2 = Area("Bathroom", "") // you look distorted in the mirror
  private val bedroom2 = Area("Bedroom", "") // normal bedroom, tired but you're suddenly too anxious to sleep, trying to sleep doesn't work
  private val kitchen2 = Area("Kitchen", "") // normal kitchen

  livingroom1.getThingies("television").addSecretExitCommand("turn off", "You quickly turn off the TV, and this time unplug it from the wall just in case.", livingroom2)


  // add connections between house2 rooms
  livingroom2.setNeighbors(Vector("south" -> kitchen2, "upstairs" -> hallway2))
  hallway2.setNeighbors(Vector("downstairs" -> livingroom2, "north" -> bedroom2, "west" -> bathroom2))
  kitchen2.setNeighbor("north", livingroom2)
  bathroom2.setNeighbor("east", hallway2)
  bedroom2.setNeighbor("south", hallway2)

  // add thingies


  private val hallway3 = Area("Hallway", "You step back from the window, trembling. You aren't sure what you just saw, but in any case it isn't " +
    "\ngood. The only thought that pops into your mind is going downstairs to call for help on the telephone.")
  private val livingroom3 = Area("Living room", "") // use the telephone
  // dummy rooms w player being anxious
  private val bedroom3 = Area("Bedroom", "")
  private val bathroom3 = Area("Bathroom", "")


  hallway2.addThingy(InteractableThingy("window", "As you peer through the window, you glimpse the dark garden outside. It's too dark to make out " +
    "\ndetails, but you see the silhouette of a strange, inhuman shape, almost still but heaving up and down as if it was breathing heavily. " +
    "\n\nAt first you think it's just a tree, but you suddenly notice the whites of its eyes. It's staring up at you, unblinking.", hallway3))

  // add connections between rooms
  hallway3.setNeighbors(Vector("downstairs" -> livingroom3, "west" -> bathroom3, "north" -> bedroom3))
  bedroom3.setNeighbor("south", hallway3)
  bathroom3.setNeighbor("east", hallway3)
  livingroom3.setNeighbor("upstairs", hallway3)


  // add thingies

  // add telephone to livingroom 3
  livingroom3.addThingy(InteractableThingy("telephone", "", livingroom3))

  private val livingroom4 = Area("Living room", "You're really starting to panic now. You keep thinking you see movement somewhere within the shadows of the dark " +
    "\nroom in the corner of your eye, but when you turn to look, there's nothing there. " +
    "\n\nSomeone is after you. You're sure of that now. They, or it, messed with the TV and then cut the phone line, and is coming after you next." +
    "\n\nYou suddenly recall the gun you keep locked in the attic. The key should be in the bedroom drawer.")


  livingroom3.getThingies("telephone").addSecretExitCommand("call xx-xx-xxx", "You pick up the receiver and dial the number of your close friend. After a moment's wait, they pick up." +
    "\n\n'Hello? Who's calling this late at night?' \n\n'CLICK'" +
    "\n\n Before you have time to open your mouth to answer, the call cuts off. In a panic, you try dialing the number again, but the phone is dead. ", livingroom4)

  // add house4 rooms of being anxious and wanting to get gun

  private val hallway4 = Area("Hallway", "")
  private val bedroom4 = Area("Bedroom", "")
  private val bathroom4 = Area("Bathroom", "")

  livingroom4.setNeighbors(Vector("upstairs" -> hallway4))
  hallway4.setNeighbors(Vector("downstairs" -> livingroom4, "west" -> bathroom4, "north" -> bedroom4))
  bedroom4.setNeighbor("south", hallway4)
  bathroom4.setNeighbor("east", hallway4)

  bedroom4.addThingy(InteractableThingy("drawer", "An ornate chest of drawers made of dark wood. It has drawers of many different sizes. You open them one at a time looking for the key.", bedroom4))
  bedroom4.getThingies("drawer").addItem(Item("attic key", "The key to open the attic. It's a bit rusty."))

  private val attic4 = Area("Attic", "The air here has a musty smell to it. The roof slants downwards at such a steep angle you have to watch your head. " +
    "\nThe floor is littered with boxes and old furniture, but in the middle stands a promising-looking cabinet. " +
    "\nYou should hurry")
  hallway4.addThingy(LockedDoor("attic door", "The door leading to the attic.", bedroom4.getThingies("drawer").getItem("attic key"), hallway4, attic4, "upstairs"))
  attic4.addThingy(InteractableThingy("cabinet", "The tall, oaken cabinet looms over you as you swing open the door to inspect its contents. A revolver lies on the top shelf.", attic5))
  attic4.getThingies("cabinet").addItem(Item("revolver", "It's made of metal with a worn leather handle. It's heavy."))

  private val attic5 = Area("Attic", "You hold the gun in your hands, trembling. You hear more noises coming from downstairs. This time you have something to defend yourself with, at least.")
  private val hallway5 = Area("Hallway", "The sounds seem to get louder as you descend the attic stairs. The noises are difficult to describe: they sometimes sound more " +
    "\nlike shuffling, sometimes like knocking, at other times like whispers." +
    "\n\nIn any case, you're now sure that they're coming from the bathroom.")
  private val bathroom5 = Area("Bathroom", "The bathroom is dim, the tiles freezing cold under your feet. The slightest hint of moonlight shines in through the tiny window near the ceiling. " +
    "\n\nIt's suddenly completely silent. The door is closed; you're not sure if you closed it yourself or if it closed on its own." +
    "\n\nYou look around you, searching for the source of the earlier noises.")

  attic5.setNeighbor("downstairs", hallway5)
  hallway5.setNeighbor("west", bathroom5)

  bathroom5.addThingy(InteractableThingy("mirror", "The mirror seems even dirtier than before. Underneath all the dirt, your own silhouette looks back at you." +
    "\nBehind you stands another shape." +
    "\nYou can't breathe.", bathroom5))

  private val bathroom6 = Area("...", "")

  bathroom5.getThingies("mirror").addSecretExitCommand("shoot", "You fire the gun", bathroom6)

  private val destination = bathroom6

  val player = Player(kitchen0)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 100


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.destination && this.player.has("remote") && this.player.has("battery")

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "You are lost in the woods. Find your way back home.\n\nBetter hurry, 'cause Scalatut elämät is on real soon now. And you can't miss Scalkkarit, right?"


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether the player has completed their quest. */
  def goodbyeMessage =
    if this.isComplete then
      "It's quiet."
    else if this.turnCount == this.timeLimit then
      "You suddenly hear steps coming up from behind you. Before you can turn to look, a freezing cold envelops your entire body and your vision turns to black. " +
        "\nYou feel yourself being dragged into the shadows before losing consciousness."
    else  // game over due to player quitting
      "Skill issue"


  /** Plays a turn by executing the given in-game command, such as “go west”. Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String): String =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")



end Adventure

