package com.findcitycodes.pojo;

public class DigitsInfo {
  private int digits;
  private boolean leadingZero;

  @Override
  public String toString() {
    return "DigitsInfo{" +
        "digits=" + digits +
        ", leadingZero=" + leadingZero +
        '}';
  }

  public DigitsInfo(int digits, boolean leadingZero) {
    this.digits = digits;
    this.leadingZero = leadingZero;
  }

  public int getDigits() {
    return digits;
  }

  public void setDigits(int digits) {
    this.digits = digits;
  }

  public boolean isLeadingZero() {
    return leadingZero;
  }

  public void setLeadingZero(boolean leadingZero) {
    this.leadingZero = leadingZero;
  }
}
