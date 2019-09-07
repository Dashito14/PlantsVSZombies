package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;

public class Redo extends NoParamsCommand{
	public static final String CommandText = "redo";
	public static final String CommandTextMsg = "[R]edo";
	public static final String HelpTextMsg = "Redo pos lo que has deshecho antes loco";
	
		public Redo() {
			super(CommandText, CommandTextMsg, HelpTextMsg);
		}

		@Override
		public boolean execute(Game game) throws CommandExecuteException {
			// TODO Auto-generated method stub
			game.redo();
			return true;
		}
}