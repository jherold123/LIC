package LIC.UC04v1.controllers;

import LIC.UC04v1.model.Doctor;
import LIC.UC04v1.repositories.DoctorRepository;
import io.micrometer.core.instrument.MultiGauge;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ImportController {

    public ImportController(DoctorRepository doctorRepository){
   //     this.doctorRepository = doctorRepository;
    }

    @GetMapping(path = "/import-Data")
    public String getImports(Model model){
        return "ImportData";
    }
    /*
    This method maps to webpage requests of the format /(profession)/(id)
    We pass parameters, in string form, in the url
     */

    @RequestMapping(path = "/import-Data/{type}/{file}")
    public String docImport(Model model, @PathVariable String type, MultipartFile file) throws IOException {
        String fileLocation;
        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();

        //Read in a modern .xlsx Excel file
        if (fileLocation.endsWith(".xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);
                System.out.println(row.getCell(0).getStringCellValue());
                System.out.println(row.getCell(1).getStringCellValue());
            }
        }
        //Read in an older .xls Excel file
        else if (fileLocation.endsWith(".xls")) {
            HSSFWorkbook workbookXLS = new HSSFWorkbook(file.getInputStream());
            HSSFSheet worksheetXLS = workbookXLS.getSheetAt(0);

            for (int i = 1; i < worksheetXLS.getPhysicalNumberOfRows(); i++) {

                HSSFRow rowXLS = worksheetXLS.getRow(i);
                System.out.println(rowXLS.getCell(0).getStringCellValue());
                System.out.println(rowXLS.getCell(1).getStringCellValue());
            }
        }

        //Passing attributes to the thymeleaf front end
        if (type.equals("doctors"))
            model.addAttribute("docImportMsg", "File: " + file.getOriginalFilename()
                    + " has been uploaded successfully!");
        else
            model.addAttribute("stuImportMsg", "File: " + file.getOriginalFilename()
                    + " has been uploaded successfully!");
        return "ImportData";
    }
}
