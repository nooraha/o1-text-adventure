package o1.adventure

class InteractableThingy(val name: String, val description: String) {
  override def toString = this.name

  // define options for actions when examining this
  // how it should work:
  // player enters: examine cupboard
  // then it shows like a description of the cupboard idk the cupboard contains... something
  // options for player: take gun (idk), return
  // sooo thingy can contain items but can also have its own actions
}
