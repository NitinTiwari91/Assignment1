package com.findcitycodes.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.findcitycodes.pojo.DigitsInfo;


public class Constants {

  //In India tier-1 cities have a 2-digit STD code
  public static final List<String> INDIA_TIER_1 = Collections.unmodifiableList(
      Arrays.asList(new String[] {"New Delhi", "Mumbai","Kolkata","Chennai","Hyderabad","Bangalore",
          "Pune","Ahmedabad"}));

  //In India tier-2 cities have a 3-digit STD code
  public static final List<String> INDIA_TIER_2 = Collections.unmodifiableList(
      Arrays.asList(new String[] {"Dehradun", "Surat","Bhagalpur","Tirupati","Ludhina","Vadodara",
          "Jamshedpur","Rajahmundry","Patiala","Jharkhand","Bhubaneshwar","Kakinada","Jaipur", "Guwahati",
          "Nagpur","Warangal", "Jodhpur","Shillong","Indore","Visakhapatanam", "Udaipur","Puducherry",
          "Ujjain","Kalyan","Tiruppur","Kota", "Vapi","Coimbatore","Bhopal","Erode","Salem","Tumkur",
          "Tiruchirapalli","Madurai", "Mysore","Thiruvananthapuram", "Kanpur","Bangalore","Kollam",
          "Lucknow","Belgaum", "Kochi","Allahabad","Hubli","Thrissur","Varanasi",
          "Nellore","Kozhikode","Gorakhpur","Guntur","Kannur","Patna","Vijayawada"}));

  public static final Map<String, DigitsInfo> COUNTRY_WITH_DIGITS_MAP = new HashMap<String, DigitsInfo>() {{
    put("United States", new DigitsInfo(3, false));
    put("Brazil", new DigitsInfo(2, false));
    put("Canada", new DigitsInfo(3, false));
    put("France", new DigitsInfo(3, true));
    put("Hong Kong", new DigitsInfo(1, false));
    put("Italy", new DigitsInfo(1, false));
    put("Malaysia", new DigitsInfo(1, false));
    put("Mexico", new DigitsInfo(2, false));
    put("Netherlands", new DigitsInfo(2, true));
    put("Singapore", new DigitsInfo(2, false));
    put("United Kingdom", new DigitsInfo(2, true));
  }};
}
