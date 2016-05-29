package Testy;

import java.util.*;
import org.reflections.scanners.*;
import org.reflections.util.*;
import org.reflections.*;

public class Tests3 {
	
	Reflections reflections;
	
	
	
	
	
	
	public void metchod2(){
		
		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
	}
	
//	public void metchod1(){
//		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
//		classLoadersList.add(ClasspathHelper.contextClassLoader());
//		classLoadersList.add(ClasspathHelper.staticClassLoader());
//
//		Reflections reflections = new Reflections(new ConfigurationBuilder()
//		    .setScanners(new SubTypesScanner(/*false /* don't exclude Object.class */), new ResourcesScanner())
//		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
//		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("simpleGreen.sender"))));
//	}
}