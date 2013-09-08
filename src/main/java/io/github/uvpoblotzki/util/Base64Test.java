package io.github.uvpoblotzki.util;

import java.util.Base64;

import static java.lang.System.out;

public class Base64Test {

  public static final String MAGIC_STRING = "magic string";
  public static final String MACIC_ENCODED_STRING = "bWFnaWMgc3RyaW5n";

  public static void main(String[] args) {
    Base64Test test = new Base64Test();

    test.encode();
    test.decode();
  }

  private void decode() {
    String decoded = new String(Base64.getDecoder().decode(MACIC_ENCODED_STRING));
    out.printf("decode %s to: %s\n", MACIC_ENCODED_STRING, decoded);
  }

  private void encode() {
    String encoded = Base64.getEncoder().encodeToString(MAGIC_STRING.getBytes());
    out.printf("%s to base64: %s\n", MAGIC_STRING, encoded);
  }

}
