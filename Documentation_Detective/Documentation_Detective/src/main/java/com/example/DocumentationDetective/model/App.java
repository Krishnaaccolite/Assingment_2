package com.example.DocumentationDetective.model;

import annotations.ClassDocumentation;
import annotations.MethodDocumentation;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import utils.Scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
public class App {
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream obj = new PrintStream(new File("Result.txt"));
		System.setOut(obj);

		try {
			List<Class> classes = Scanner.getAllClasses("models");
			System.out.println("All classes: ");
			for (Class c: classes) {
				System.out.println(c.getName());
			}
			System.out.println();

			Reflections reflections = new Reflections(new ConfigurationBuilder()
					.setUrls(ClasspathHelper.forPackage("models"))
					.setScanners(new TypeAnnotationsScanner(), new org.reflections.scanners.SubTypesScanner(), new MethodAnnotationsScanner())
			);

			Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(ClassDocumentation.class);
			System.out.println("Classes annotated with '@ClassDocumentation': ");
			for (Class<?> cls: annotatedClasses) {
				System.out.println(cls.getName());
			}
			System.out.println();

			Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(annotations.MethodDocumentation.class);
			System.out.println("Methods annotated with '@MethodDocumentation': ");
			for(Method method: annotatedMethods) {
				System.out.println(method);
			}
			System.out.println();

			Map<String, String> methodsWithoutAnnotationButDocumented = new HashMap<>();
			Set<String> methodsWithAnnotationButNoJavaDoc = new HashSet<>();
			try {
				for (Class<?> cls: classes) {
				    String classPath = cls.getName().replace(".", "/") + ".java";
					String sourceCode = new String(Files.readAllBytes(Paths.get("src/main/java", classPath)));
					CompilationUnit cu = StaticJavaParser.parse(sourceCode);
					for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
						method.getJavadoc().ifPresent(javadoc -> {
							if(!method.isAnnotationPresent(MethodDocumentation.class)) {
								String methodJavadoc = javadoc.toText();
								methodsWithoutAnnotationButDocumented.put(String.valueOf(cls.getName() + "." + method.getName()), methodJavadoc);
							}
						});
						if(method.isAnnotationPresent(MethodDocumentation.class) && !method.getJavadoc().isPresent()) {
							methodsWithAnnotationButNoJavaDoc.add(cls.getName() + "." + method.getName());
						}
					}
				}
				System.out.println("Classes annotated with '@ClassDocumentation' but have no javadoc: ");
				for (Class<?> cls: annotatedClasses) {
					System.out.println(cls.getName());
				}
				System.out.println();

				System.out.println("Methods annotated with '@MethodDocumentation' but have no javadoc: ");
				System.out.println(methodsWithAnnotationButNoJavaDoc);
				System.out.println();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
