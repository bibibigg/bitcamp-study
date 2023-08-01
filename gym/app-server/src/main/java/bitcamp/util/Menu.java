package bitcamp.util;

import java.util.ArrayList;

public class Menu {

  private String path;
  private String title;
  private ArrayList<ActionListener> listeners = new ArrayList<>();

  public Menu(String path, String title) {
    this.path = path;
    this.title = title;
  }

  public Menu(String path, String title, ActionListener listener) {
    this(path, title);
    this.addActionListener(listener);
  }

  public void addActionListener(ActionListener listener) {
    listeners.add(listener);
  }

  public void removeActionListener(ActionListener listener) {
    listeners.remove(listener);
  }

  public String getTitle() {
    return title;
  }

  public void execute(BreadcrumbPrompt prompt) {
    try {
      prompt.setAttribute("menuPath", this.path);

      for (int i = 0; i < listeners.size(); i++) {
        ActionListener listener = listeners.get(i);
        listener.service(prompt);
      }
    } catch (Exception e) {
      prompt.clear();
      prompt.println(e.getMessage());

    } finally {
      try {
        prompt.end();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

}

