package o1.adventure

import scala.collection.mutable.Map
import scala.collection.immutable.Vector

class InteractableThingy(name: String, description: String, val exitArea: Area) extends Area(name, description){

  override val neighbors = Map[String, Area]("back" -> exitArea)

  override def fullDescription: String =
    this.description + this.secretExitList + this.itemList + this.exitStr

  def itemList: String =
    var itemList = ""
    if items.nonEmpty then
      itemList = s"\n\nYou see here:"
      items.keys.foreach(s => itemList += " " + s)
    itemList


  def exitStr: String = "\n\nIf you're done looking, you can exit with 'go back'."

  // define options for actions when examining this
  // how it should work:
  // player enters: examine cupboard
  // then it shows like a description of the cupboard idk the cupboard contains... something
  // options for player: take gun (idk), return
  // sooo thingy can contain items but can also have its own actions

  // player calls action
  // if action is a defined action then
  // run that action with modifiers
  // have a list of available actions at each moment
  // list is affected by current area/object
  // when examining thingy, list of actions is defined completely by that thingy
  // each thingy should have 'return' action to go back to area
}

class ContainerThingy(name: String, description: String, area: Area) extends InteractableThingy(name, description, area) {

  def take(actor: Player, itemName: String): String =
    if this.containsItem(itemName) then
      actor.giveItem(items(itemName))
      items.remove(itemName)
      s"You take the $itemName."
    else
      s"There's no $itemName here."

  override def fullDescription: String =
    var itemList = ""
    if items.nonEmpty then
      itemList = s"\nIt contains:"
      items.keys.foreach(s => itemList += " " + s)
    this.description + itemList + this.exitStr

}

class LockedDoor(name: String, description: String, val key: Item, val originalArea: Area, val leadingToArea: Area, val exitDirection: String) extends InteractableThingy(name, description, originalArea) {
  var locked: Boolean = true

  def unlock(actor: Player): String =
    if !this.locked then
      "The door is already unlocked."
    else if actor.has(key.name) then
      this.locked = false
      this.originalArea.setNeighbor(exitDirection, leadingToArea)
      "You unlock the door."
    else
      "You don't have the key."


  def lock(): Unit = this.locked = true


  override def fullDescription: String =
    var lockedText = " It's unlocked."
    if locked then
      lockedText = " It's locked."
    this.description + lockedText + this.exitStr


}

class Telephone(name: String, description: String, exitArea: Area) extends InteractableThingy(name, description, exitArea) {

  val conversationFlow = Vector[(String, Vector[String])]()
  var convoTurn = 0

  def continueConvo(answerOption: Int) =
    val current = conversationFlow(convoTurn)
    if answerOption < current(1).length then
      println(current(0))
      println()
      for s <- current(1).indices do
        println(s"[$s] ${current(1)(s)}")

      println("Please type the number of your chosen answer.")


}