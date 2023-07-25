package bitcamp.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Stack;

public class BreadcrumbPrompt extends GymPrompt {

  private Stack<String> breadcrumbs = new Stack<>();

  public BreadcrumbPrompt(DataInputStream in, DataOutputStream out) {
    super(in, out);
  }

  public void appendBreadcrumb(String title) {
    this.breadcrumbs.push(title);
  }

  public void removeBreadcrumb() {
    this.breadcrumbs.pop();
  }

  public String inputMenu() throws Exception {
    StringBuilder titleBuilder = new StringBuilder();
    for (int i = 0; i < this.breadcrumbs.size(); i++) {
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(this.breadcrumbs.get(i));
    }
    titleBuilder.append("> ");
    return this.inputString(titleBuilder.toString());
  }
}