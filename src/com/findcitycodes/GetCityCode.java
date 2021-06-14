package com.findcitycodes;

import java.io.IOException;
import java.util.List;

import com.findcitycodes.pojo.Data;
import com.findcitycodes.processor.FindCityCode;
import com.findcitycodes.service.DataService;

public class GetCityCode {

  public static void main(String[] args) throws IOException {

    DataService dataService = new DataService();
    FindCityCode findCityCodeProcessor = new FindCityCode();
    List<Data> inputDataList = dataService.loadData("resources/india_phoneno_not_null.txt");
    List<Data> result =  findCityCodeProcessor.findCityCodes(inputDataList, dataService);
    dataService.printRecords(result, "resources/output.txt");
  }

}
