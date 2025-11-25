package o1.adventure

import scala.collection.mutable.Map

/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area):

  private var currentLocation: Area = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var currentThingy = None

  private var carrying = Map[String, Item]()

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the player’s current location. */
  def location = this.currentLocation

  def drop(itemName: String): String =
    if this.has(itemName) then
      val droppedItem = carrying.remove(itemName)
      droppedItem.foreach(d => this.currentLocation.addItem(d))
      s"You drop the $itemName."
    else
      "You don't have that!"

  def take(itemName: String): String =
    if this.currentLocation.containsItem(itemName) then
      val pickedUp = this.currentLocation.removeItem(itemName)
      pickedUp.foreach(item => carrying += item.name -> item)
      s"You pick up the ${itemName}."
    else
      s"There is no ${itemName} here to take."

//  def examine(itemName: String): String =
//    var returnString = ""
//    if this.has(itemName) then
//      carrying.get(itemName).foreach(item => returnString = s"You look closely at the $itemName.\n${item.description}")
//    else
//      returnString = "If you want to examine something, you need to pick it up first."
//    returnString
//

  def examine(thingyName: String): String =
    if this.currentLocation.containsThingy(thingyName) then
      this.currentLocation = this.currentLocation.getThingies(thingyName)
      s"You examine the $thingyName."
    else
      s"There is no $thingyName here."

  def giveItem(item: Item): Unit =
    carrying += item.name -> item

  def get(itemName: String): String =
    if this.currentLocation.containsItem(itemName) then
      val pickedUp = this.currentLocation.removeItem(itemName)
      pickedUp.foreach(item => carrying += item.name -> item)
      s"You pick up the ${itemName}."
    else
      s"There is no ${itemName} here to pick up."

  def has(itemName: String): Boolean =
    carrying.contains(itemName)

  def inventory: String =
    if carrying.isEmpty then
      "You are empty-handed."
    else
      var returnString = "You are carrying:"
      for item <- carrying.keys do
        returnString += s"\n$item"
      returnString

  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player’s current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then s"You go $direction." else s"You can't go $direction."

  def quietGo(area: Area) =
    this.currentLocation = area

  def trySecretExitCommand(command: String): Option[String] =
    if this.currentLocation.containsSecretExitCommand(command) then
      val desc = Some(this.currentLocation.getSecretExitCommands(command)(0))
      this.quietGo(this.currentLocation.getSecretExitCommands(command)(1))
      desc
    else
      None

  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() = "You rest for a while. Better get a move on, though."


  /** Signals that the player wants to quit the game. Returns a description of what happened
    * within the game as a result (which is the empty string, in this case). */
  def quit() =
    this.quitCommandGiven = true
    ""


  /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player

