package o1.adventure

class InteractableThingy(val name: String, val description: String) {

  private val actions = List[String]()

  override def toString = this.name

  def addAction(name: String): Int =
    1+1

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
