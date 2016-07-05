package com.test.thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Example demonstrating a ClassLoader leak.
 * Here's a good way to create a true memory leak (objects inaccessible by running code but still stored in memory) in pure Java:

1) The application creates a long-running thread (or use a thread pool to leak even faster).
2) The thread loads a class via an (optionally custom) ClassLoader.
3)The class allocates a large chunk of memory (e.g. new byte[1000000]), stores a strong reference to it in a static field, and then stores a reference to itself 
in a ThreadLocal. Allocating the extra memory is optional (leaking the Class instance is enough), but it will make the leak work that much faster.
4) The thread clears all references to the custom class or the ClassLoader it was loaded from.
Repeat.
This works because the ThreadLocal keeps a reference to the object, which keeps a reference to its Class, which in turn keeps a reference to its ClassLoader. 
The ClassLoader, in turn, keeps a reference to all the Classes it has loaded. It gets worse because in many JVM implementations Classes and ClassLoaders are 
allocated straight into permgen and are never GC'd at all.

A variation on this pattern is why application containers (like Tomcat) can leak memory like a sieve if you frequently redeploy applications that happen 
to use ThreadLocals in any way. (Since the application container uses Threads as described, and each time you redeploy the application a new ClassLoader is used.)
 *
 * <p>To see it in action, copy this file to a temp directory somewhere,
 * and then run:
 * <pre>{@code
 *   javac ClassLoaderLeakExample.java
 *   java -cp . ClassLoaderLeakExample
 * }</pre>
 *
 * <p>And watch the memory grow! On my system, using JDK 1.8.0_25, I start
 * getting OutofMemoryErrors within just a few seconds.
 *
 * <p>This class is implemented using some Java 8 features, mainly for
 * convenience in doing I/O. The same basic mechanism works in any version
 * of Java since 1.2.
 */
public final class ClassLoaderLeakExample {

  static volatile boolean running = true;

  public static void main(String[] args) throws Exception {
    Thread thread = new LongRunningThread();
    try {
      thread.start();
      System.out.println("Running, press any key to stop.");
      System.in.read();
    } finally {
      running = false;
      thread.join();
    }
  }

  /**
   * Implementation of the thread. It just calls {@link #loadAndDiscard()}
   * in a loop.
   */
  static final class LongRunningThread extends Thread {
    @Override public void run() {
      while(running) {
        try {
          loadAndDiscard();
        } catch (Throwable ex) {
          ex.printStackTrace();
        }
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          System.out.println("Caught InterruptedException, shutting down.");
          running = false;
        }
      }
    }
  }
  
  /**
   * A simple ClassLoader implementation that is only able to load one
   * class, the LoadedInChildClassLoader class. We have to jump through
   * some hoops here because we explicitly want to ensure we get a new
   * class each time (instead of reusing the class loaded by the system
   * class loader). If this child class were in a JAR file that wasn't
   * part of the system classpath, we wouldn't need this mechanism.
   */
  static final class ChildOnlyClassLoader extends ClassLoader {
    ChildOnlyClassLoader() {
      super(ClassLoaderLeakExample.class.getClassLoader());
    }
    
    @Override protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException {
      if (!LoadedInChildClassLoader.class.getName().equals(name)) {
        return super.loadClass(name, resolve);
      }
      try {
        Path path = Paths.get(LoadedInChildClassLoader.class.getName()
            + ".class");
        byte[] classBytes = Files.readAllBytes(path);
        Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
        if (resolve) {
          resolveClass(c);
        }
        return c;
      } catch (IOException ex) {
        throw new ClassNotFoundException("Could not load " + name, ex);
      }
    }
  }
  
  /**
   * Helper method that constructs a new ClassLoader, loads a single class,
   * and then discards any reference to them. Theoretically, there should
   * be no GC impact, since no references can escape this method! But in
   * practice this will leak memory like a sieve.
   */
  static void loadAndDiscard() throws Exception {
    ClassLoader childClassLoader = new ChildOnlyClassLoader();
    Class<?> childClass = Class.forName(
        LoadedInChildClassLoader.class.getName(), true, childClassLoader);
    childClass.newInstance();
    // When this method returns, there will be no way to reference
    // childClassLoader or childClass at all, but they will still be
    // rooted for GC purposes!
  }

  /**
   * An innocuous-looking class. Doesn't do anything interesting.
   */
  public static final class LoadedInChildClassLoader {
    // Grab a bunch of bytes. This isn't necessary for the leak, it just
    // makes the effect visible more quickly.
    // Note that we're really leaking these bytes, since we're effectively
    // creating a new instance of this static final field on each iteration!
    static final byte[] moreBytesToLeak = new byte[1024 * 1024 * 10];
  
    private static final ThreadLocal<LoadedInChildClassLoader> threadLocal=null;//      = new ThreadLocal<>();
    
    public LoadedInChildClassLoader() {
      // Stash a reference to this class in the ThreadLocal
      threadLocal.set(this);
    }
  }
}

