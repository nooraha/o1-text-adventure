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
      lockedText = " It's locked. You can unlock it with 'unlock' if you have the " + key.name + "."

    this.description + lockedText + this.exitStr


}