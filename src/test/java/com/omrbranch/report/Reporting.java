package com.omrbranch.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting {

  public static void generateJvmReport(String jsonFile) {

    // 1. Create Object For Configuration Class -->Path of jvm report where to
    // store, Project name
    Configuration configuration = new Configuration(
        new File("C:\\Users\\varsh\\eclipse-workspace\\OmrBranchAPIProject_Karthik\\src\\test\\resources\\target"),"API Testing Project");

    // 2. Add key and value -->Design purpose
    configuration.addClassifications("Browser", "Chrome");
    configuration.addClassifications("Browser Version", "123");
    configuration.addClassifications("OS", "WIN 11");
    configuration.addClassifications("Sprint", "34");
    configuration.addClassifications("tester name", "karthik");

    // 3. Read the results ,Create Object for Reportbuilder Class -->Pass JSON Files
    List<String> jsonFiles = new ArrayList<>();
    jsonFiles.add(jsonFile);
    ReportBuilder builder = new ReportBuilder(jsonFiles, configuration);
    // 4. Build JVM Report
    builder.generateReports();
  }
}