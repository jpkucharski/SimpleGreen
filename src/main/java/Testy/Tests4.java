package Testy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Tests4 {

	ArrayList<Class> classes;

	
	
	public static void main(String args[]) throws ClassNotFoundException, IOException{
		Tests4 o1 = new Tests4();
		System.out.println("Program uruchomiony.");
		o1.getClasses("logs");
		o1.method1();
		
		
		System.out.println("Program zakonczyl dzialanie.");
	}
	

	private Class method1() {
			 
			return classes.get(1);
		
	}
	
	private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		
		/** 
		 Scans all classes accessible from the context class loader which belong to the given package and subpackages.
		 @param packageName The base package
		 @return The classes
		 @throws ClassNotFoundException
		 @throws IOException*/
		
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}
	 
	/**
	28.* Recursive method used to find all classes in a given directory and subdirs.
	29.*
	30.* @param directory   The base directory
	31.* @param packageName The package name for classes found inside the base directory
	32.* @return The classes
	33.* @throws ClassNotFoundException
	34.*/
	
	private List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}
}