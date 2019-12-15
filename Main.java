public class Main
{
	public static void main(String[] args) {
		System.out.println(ShieldSwitch.oneEGH(ShieldSwitch.oneEGH("C\\F\\Y\\\\O", '\\'),'\\'));
		Settings.getPath();
		Global.startProgram();
		while(Global.isRunning()) {
			System.out.print("\n "+Settings.path + " >> ");
			String com = Global.getInput();
			ShieldSwitch.switchProcess(com);
		}
	}
}