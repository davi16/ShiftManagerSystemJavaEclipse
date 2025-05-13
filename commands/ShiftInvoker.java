package commands;
//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921

public class ShiftInvoker implements Invoker {

	Command command;

	@Override
	public void setCommand(Command command) {
		this.command = command;
	}

	@Override
	public <T> T executeCommand() {
		return command.execute();
	}
}
