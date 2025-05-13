package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
public interface Invoker {

	public void setCommand(Command command);

	public <T> T executeCommand();

}
