import java.io.File;

public class ShieldSwitch
{
	public static void switchProcess(String st) 
	{
		if(st.equals("exit") || st.equals("end")) {
			Global.endProgram();
		}
		else if(st.startsWith("dir")) dirSwitch(st);
		else if(st.startsWith("ls")) lsSwitch(st);
		else if(st.startsWith("cd")) cdSwitch(st);
		else if(st.startsWith("newp")) NewProjectModule.commandLineNewProject(st);
		else if(st.startsWith("newf")) NewProjectModule.newFile(st);
		else if(st.startsWith("loadp")) loadProject(st);
	}
	
	public static void cdSwitch(String st) {
		if(st.contains("..")) {
			int nplaces = zeroED(st, '\\') + 2;
			String tpath = Settings.path;
			for(int i = 0; i < nplaces; i++) {
				int idxo = tpath.lastIndexOf('\\');
				if(idxo == -1) break;
				tpath = tpath.substring(0, idxo);
			}
			if(!tpath.endsWith("\\")) tpath+="\\";
			Settings.path = tpath;
			return;
		}
		int indexd = st.indexOf(' ');
		String args = st.substring(indexd+1);
		File file = new File(Settings.path+args);
		if(!file.isDirectory()) {
			System.out.println("Invalid Directory Specification!!");
			return;
		}
		Settings.path = Settings.path+args+"\\";
	}		
	
	public static void dirSwitch(String st) {
		System.out.println();
		if(st.equals("dir")) {
			File[] f = listFiles(Settings.path);
			for(int i = 0; i < f.length; i++) {
				System.out.println(f[i].getName());
			}
			return;
		}
		int indexd = st.indexOf(' ');
		String args = st.substring(indexd+1);
		if(args.equals("*")) {
			File[] f = listFiles(Settings.path);
			for(int i = 0; i < f.length; i++) {
				System.out.println(f[i].getName());
			}
			return;
		}
		if(args.startsWith("*")) {
			String extension = args.substring(args.indexOf('.')+1);
			File[] f = listFiles(Settings.path);
			for(int i = 0; i < f.length; i++) {
				if(f[i].getName().endsWith(extension))
					System.out.println(f[i].getName());
			}
			return;
		}
		String tpath = Settings.path + "/" + args;
		File ndir= new File(tpath);
		if(!ndir.isDirectory() || !ndir.exists()) {
			System.out.println("Invalid Directory Specification!!");
			return;
		}
		File[] f = listFiles(tpath);
		for(int i = 0; i < f.length; i++) {
			System.out.println(f[i].getName());
		}
	}	
	
	public static void lsSwitch(String st) {
		System.out.println();
		if(st.equals("ls")) {
			File[] f = listFiles(Settings.path);
			for(int i = 0; i < f.length; i++) {
				System.out.println(f[i].getName());
			}
			return;
		}
		int indexd = st.indexOf(' ');
		String args = st.substring(indexd+1);
		if(args.equals("*")) {
			File[] f = listFiles(Settings.path);
			for(int i = 0; i < f.length; i++) {
				System.out.println(f[i].getName());
			}
			return;
		}
		if(args.startsWith("*")) {
			String extension = args.substring(args.indexOf('.')+1);
			File[] f = listFiles(Settings.path);
			for(int i = 0; i < f.length; i++) {
				if(f[i].getName().endsWith(extension))
					System.out.println(f[i].getName());
			}
			return;
		}
		String tpath = Settings.path + "/" + args;
		File ndir= new File(tpath);
		if(!ndir.isDirectory() || !ndir.exists()) {
			System.out.println("Invalid Directory Specification!!");
			return;
		}
		File[] f = listFiles(tpath);
		for(int i = 0; i < f.length; i++) {
			System.out.println(f[i].getName());
		}
	}
	
	public static void loadProject(String st) {
		String pname = st.substring(st.indexOf(" ")+1)+".shieldproject";
		String lp = (new ProjectConfigaration(pname)).getProjectRoot();
		if(!(new File(lp)).isDirectory()) {
			System.out.println("Project "+(new ProjectConfigaration(pname)).getName()+" not found !!");
			return;
		}
		Settings.pconf = new ProjectConfigaration(pname);
	}
	
	// Occurance of 
	public static int zeroED(String string, char c) 
	{
		int cd = 0;
		for(int i = 0; i < string.length(); i++) if(string.charAt(i) == c) cd++;
		return cd;
	}
	
	// Replace Multiple Simultaneous Occurances
	public static String oneEGH(String st, char ch) {
		String r=" ";
		for(int i = 0; i < st.length(); i++) {
			char tobe = st.charAt(i);
			char last = r.charAt(r.length()-1);
			if(tobe == last) continue;
			r+=tobe;
		}
		r=r.trim();
		return r;
	}
	
	public static File[] listFiles(String path) {
		return (new File(path).listFiles());
	}
}