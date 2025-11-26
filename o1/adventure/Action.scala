package o1.adventure

/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as “go east” or “rest” */
class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as “You go west.”). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player): Option[String] =
    this.verb match
      case "go"        => Some(actor.go(this.modifiers))
      // case "rest"      => Some(actor.rest())
      case "quit"      => Some(actor.quit())
      // case "drop"      => Some(actor.drop(this.modifiers))
      case "examine"   => Some(actor.examine(this.modifiers))
      case "inventory" => Some(actor.inventory)
      case "take" =>      Some(actor.take(this.modifiers))
      case "unlock" =>    Some(actor.tryUnlockingDoor())
      case "help" =>      Some(this.help)
      case other       => actor.trySecretExitCommand(commandText)

  def help: String =
    "You must move through rooms with the 'go' command, interacting with the correct objects in order to progress the story. " +
      "\nAs you explore, objects may change from when you previously saw them. " +
      "\n\nHere is a list of all available commands:" +
      "\ngo -direction-: move in the specified direction." +
      "\nquit: quit the game." +
      "\nexamine -object name-: (only available when the current area has something to examine) get a more detailed description of the object." +
      "\ninventory: view your inventory." +
      "\ntake -item name-: (only available when the current object being examined contains items) take the specified item. " +
      "\nunlock: (only available when examining a locked door) unlock the door (if you have the key). " +
      "\nhelp: view this list." +
      "\n\nSome objects, upon being examined, may also contain custom interactions. " +
      "\nThese will be listed and can be used as commands as they are given with no extra parameters needed."


  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = s"$verb (modifiers: $modifiers)"

end Action

