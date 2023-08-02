package bitcamp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import bitcamp.myapp.config.AppConfig;

//
public class ApplicationContext {
  Map<String, Object> beanContainer = new HashMap<>();

  public ApplicationContext(Class<?> configClass) throws Exception {

    processBeanAnnotation(configClass);

    ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
    if (componentScan != null) {
      processComponentScanAnnotation(componentScan);
    }
  }

  private void processBeanAnnotation(Class<?> configClass) throws Exception {
    System.out.println("@Bean --------------------------------");

    Constructor<?> constructor = configClass.getConstructor();
    Object obj = constructor.newInstance();
    Method[] methods = configClass.getDeclaredMethods();
    for (Method m : methods) {

      if (m.getReturnType() == void.class) {
        continue;
      }

      Bean beanAnnotation = m.getAnnotation(Bean.class);
      if (beanAnnotation == null) {
        continue;
      }

      Object returnValue = m.invoke(obj);

      if (beanAnnotation.value().length() > 0) {
        beanContainer.put(beanAnnotation.value(), returnValue);
      } else {
        beanContainer.put(m.getName(), returnValue);
      }
      System.out.printf("%s() 객체 생성\n", m.getName());
    }
  }

  private void processComponentScanAnnotation(ComponentScan componentScan) throws Exception {
    for (String basePackage : componentScan.basePackages()) {
      System.out.println(basePackage + "---------------------------------");
      createBeans(basePackage);
    }
  }

  private void createBeans(String basePackage) throws Exception {
    String packagePath = basePackage.replaceAll("[.]", "/");

    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    InputStream dirInputStream = systemClassLoader.getResourceAsStream(packagePath);
    if (dirInputStream == null) {
      return;
    }

    BufferedReader dirReader = new BufferedReader(new InputStreamReader(dirInputStream));

    Set<Class<?>> classes =
        dirReader.lines().filter(filename -> filename.endsWith(".class")).map(filename -> {
          try {
            return Class.forName(basePackage + "." + filename.replace(".class", ""));
          } catch (Exception e) {
            return null;
          }
        }).collect(Collectors.toSet());

    for (Class<?> clazz : classes) {
      System.out.println(clazz.getName());
      if (clazz.isEnum() || clazz.isInterface() || clazz.isLocalClass() || clazz.isMemberClass()) {
        continue;
      }

      Component compAnno = clazz.getAnnotation(Component.class);
      if (compAnno == null) {
        continue;
      }

      Constructor<?> constructor = clazz.getConstructors()[0];
      Parameter[] params = constructor.getParameters();
      Object[] args = prepareArguments(params);
      Object obj = constructor.newInstance(args);

      if (compAnno.value().length() > 0) {
        beanContainer.put(compAnno.value(), obj);
      } else {
        beanContainer.put(clazz.getSimpleName(), obj);
      }

      System.out.printf("%s 객체 생성!\n", clazz.getName());
    }
  }

  private Object[] prepareArguments(Parameter[] params) {
    ArrayList<Object> args = new ArrayList<>();

    for (Parameter param : params) {
      args.add(getBean(param.getType()));
    }

    return args.toArray();
  }

  @SuppressWarnings("unchecked")
  public <T> T getBean(Class<T> type) {
    Collection<?> list = beanContainer.values();
    for (Object obj : list) {
      if (type.isInstance(obj)) {
        return (T) obj;
      }
    }
    return null;
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }

  public String[] getBeanNames() {
    return beanContainer.keySet().toArray(new String[0]);
  }

  public static void main(String[] args) throws Exception {
    ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);

    System.out.println("생성된 객체 목록:");
    for (String name : applicationContext.getBeanNames()) {
      System.out.println(name);
    }
  }
}


