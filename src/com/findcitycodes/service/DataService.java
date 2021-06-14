package com.findcitycodes.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.findcitycodes.pojo.Data;

public class DataService {

	public List<Data> loadData(String inputFileName) throws IOException {
		List<Data> dataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8));) {

			String line;

			while ((line = br.readLine()) != null) {
				Data data = new Data();
				String[] splitted = line.split("\t");
				data.setPhoneNo(splitted[0]);
				data.setCity(splitted[1]);
				data.setState(splitted[2]);
				data.setCountry(splitted[3]);
				dataList.add(data);
			}
			System.out.println("Data size: " + dataList.size());
		} catch (FileNotFoundException e) {
			System.out.println("Phone number file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public Map<String, List<Data>> groupDataByCity(List<Data> dataList) {
		return dataList.stream().collect(Collectors.groupingBy(Data::getCity,
				Collectors.mapping(data -> data, Collectors.collectingAndThen(Collectors.toList(), ArrayList::new))));
	}

	public Map<String, List<Data>> groupDataByCountry(List<Data> dataList) {
		return dataList.stream().collect(Collectors.groupingBy(Data::getCountry,
				Collectors.mapping(data -> data, Collectors.collectingAndThen(Collectors.toList(), ArrayList::new))));
	}

	public Map<String, Long> groupDataByTwoDigitPhoneNumberAndCount(List<Data> dataList) {
		return dataList.stream()
				.collect(Collectors.groupingBy(data -> data.getPhoneNo().substring(0, 2), Collectors.counting()));
	}

	public Map<String, Long> groupDataByThreeDigitPhoneNumberAndCount(List<Data> dataList) {
		return dataList.stream()
				.collect(Collectors.groupingBy(data -> data.getPhoneNo().substring(0, 3), Collectors.counting()));
	}

	public Map<String, Long> groupDataByFourDigitPhoneNumberAndCount(List<Data> dataList) {
		return dataList.stream()
				.collect(Collectors.groupingBy(data -> data.getPhoneNo().substring(0, 4), Collectors.counting()));
	}

	public Map<String, Long> groupDataByPhoneNumberDigitsAndCount(List<Data> dataList, int digit) {
		return dataList.stream()
				.collect(Collectors.groupingBy(data -> data.getPhoneNo().substring(0, digit), Collectors.counting()));
	}

	public void printRecords(List<Data> result, String outputFilePath) {
		if(result != null && result.size() > 0) {
			System.out.println("City State Country CityCode #Record");
			result.forEach(data -> System.out.println(data));	
			try(PrintWriter printWriter = new PrintWriter(new FileWriter(outputFilePath))){
				printWriter.println("City State Country CityCode #Record");
				result.forEach(data -> printWriter.println(data));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Empty Data List");
		}
		
	}
}
