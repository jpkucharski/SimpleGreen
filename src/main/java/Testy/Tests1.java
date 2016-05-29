package Testy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;


public class Tests1 {
	ArrayList<File> list;
	ArrayList<String> list2;

	public static void main(String args[]) throws IOException {
		Tests1 o1 = new Tests1();
		o1.getPackageContent("/simpleGreen/src/main/java/sender");
		o1.method1();
	}
	
	public void method1(){
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	

	
	
		public File[] getPackageContent(String packageName) throws IOException{
		    list = new ArrayList<File>();
		    Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName);
		    while (urls.hasMoreElements()) {
		        URL url = urls.nextElement();
		        File dir = new File(url.getFile());
		        for (File i : dir.listFiles()) {
		            list.add(i);
		        }
		    }
		    return list.toArray(new File[]{});
		}
	}