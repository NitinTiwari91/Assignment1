package com.findcitycodes.processor;

import com.findcitycodes.pojo.Data;
import com.findcitycodes.pojo.DigitsInfo;
import com.findcitycodes.service.DataService;
import com.findcitycodes.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FindCityCode {

	public List<Data> findCityCodes(List<Data> dataList, DataService dataService) {
		List<Data> result = new ArrayList<>();
		if(dataList != null && dataList.size() > 0) {
			Map<String, List<Data>> countryToDataMap = dataService.groupDataByCountry(dataList);

			for (String country : countryToDataMap.keySet()) {

				Map<String, List<Data>> cityToDataMap = dataService.groupDataByCity(countryToDataMap.get(country));

				if (country.equalsIgnoreCase("India")) {

					for (String city : cityToDataMap.keySet()) {

						Data cityData = cityToDataMap.get(city).get(0);

						cityData.setRecords(cityToDataMap.get(city).size());

						List<Data> cityDataList = cityToDataMap.get(city);

						if (Constants.INDIA_TIER_1.contains(city)) {
							Map<String, Long> twoDigitPhoneNumberWithCountMap = dataService
									.groupDataByTwoDigitPhoneNumberAndCount(cityDataList);
							cityData.setCityCode(getCityCode(twoDigitPhoneNumberWithCountMap, true));
						} else if (Constants.INDIA_TIER_2.contains(city)) {
							Map<String, Long> threeDigitPhoneNumberWithCountMap = dataService
									.groupDataByThreeDigitPhoneNumberAndCount(cityDataList);
							cityData.setCityCode(getCityCode(threeDigitPhoneNumberWithCountMap, true));
						} else {
							Map<String, Long> fourDigitPhoneNumberWithCountMap = dataService
									.groupDataByFourDigitPhoneNumberAndCount(cityDataList);
							cityData.setCityCode(getCityCode(fourDigitPhoneNumberWithCountMap, true));
						}
						result.add(cityData);
					}

				} else {
					// Get digits info for other countries from constants
					DigitsInfo digitsInfo = Constants.COUNTRY_WITH_DIGITS_MAP.get(country);

					if (digitsInfo != null) {
						for (String city : cityToDataMap.keySet()) {
							// Create new object for city and set the number of records
							Data cityData = cityToDataMap.get(city).get(0);
							cityData.setRecords(cityToDataMap.get(city).size());

							// Get the list of city
							List<Data> cityDataList = cityToDataMap.get(city);

							// Find the starting X digits(based on country) from phoneNumber and their count
							Map<String, Long> phoneNumberDigitsWithCountMap = dataService
									.groupDataByPhoneNumberDigitsAndCount(cityDataList, digitsInfo.getDigits());

							// Set the cityCode in object
							cityData.setCityCode(getCityCode(phoneNumberDigitsWithCountMap, digitsInfo.isLeadingZero()));
							result.add(cityData);
						}
					}

				}
			}	
		} else {
			System.out.println("Empty Data list");
		}
		
		return result;
	}

	/**
	 * This method will take map which contains starting digits of phone
	 * number(based on country) and their count and find the highest count and mark
	 * it as cityCode.
	 *
	 * @param digitWithCountMap map which contains starting digit for phone number
	 *                          and their count.
	 * @return cityCode
	 */
	private String getCityCode(Map<String, Long> digitWithCountMap, boolean addLeadingZero) {
		String code = null;
		Long maxValueInMap = (Collections.max(digitWithCountMap.values()));
		for (String digit : digitWithCountMap.keySet()) {
			if (maxValueInMap.equals(digitWithCountMap.get(digit))) {
				if (addLeadingZero) {
					code = "0" + digit;
				} else {
					code = digit;
				}

				break;
			}
		}
		return code;
	}
}
