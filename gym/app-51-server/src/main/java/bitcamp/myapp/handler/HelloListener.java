package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    String name = prompt.inputString("이름은? ");
    prompt.printf("%s 님 환영합니다.\n", name);
  }
}
