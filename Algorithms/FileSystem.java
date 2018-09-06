package Airbnb;

import java.util.*;

public class FileSystem {
	
	public static void main(String[] args) {
		Runnable runnable1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable 1 called");
			}	
		};
		
		Runnable runnable2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable 2 called");
			}	
		};
		
		FileSystem fs = new FileSystem();
		System.out.println(fs.watch("/a", runnable1));
		System.out.println(fs.create("/a", 1));
		System.out.println(fs.watch("/a", runnable1));
		System.out.println(fs.get("/a"));
		System.out.println(fs.create("/a/b", 2));
		System.out.println(fs.watch("/a/b", runnable2));
		System.out.println(fs.set("/a/b", 3));
		System.out.println(fs.get("/a/b"));
		System.out.println(fs.create("/c/d", 3));
		System.out.println(fs.get("/c"));
	}
	
	Map<String, Integer> pathToValueMap;
	Map<String, Runnable> pathToCallbackMap;
	
	public FileSystem() {
		this.pathToValueMap = new HashMap<String, Integer>();
		this.pathToCallbackMap = new HashMap<String, Runnable>();
		this.pathToValueMap.put("", 0);
	}
	
	public boolean create(String path, int value) {
		if (path == null || this.pathToValueMap.containsKey(path)) return false;
		String parent = path.substring(0, path.lastIndexOf('/'));
		if (!this.pathToValueMap.containsKey(parent)) return false;
		this.pathToValueMap.put(path, value);
		
		runCallbacks(path);
		return true;
	}
	
	private void runCallbacks(String path) {
		if (path == null) return;
		while(path.lastIndexOf('/') >= 0) {
			if (this.pathToCallbackMap.containsKey(path)) {
				this.pathToCallbackMap.get(path).run();
			}
			path = path.substring(0, path.lastIndexOf('/'));
		}
	}

	public boolean set(String path, int value) {
		if (path == null || !this.pathToValueMap.containsKey(path)) return false;
		this.pathToValueMap.put(path, value);
		runCallbacks(path);
		return true;
	}
	
	public Integer get(String path) {
		return this.pathToValueMap.get(path);
	}
	
	public boolean watch(String path, Runnable runnable) {
		if (!this.pathToValueMap.containsKey(path)) return false;
		this.pathToCallbackMap.put(path, runnable);
		return true;
	}
}
