package LIC.UC04v1.controllers;

import LIC.UC04v1.model.Doctor;
import LIC.UC04v1.model.Student;
import LIC.UC04v1.repositories.DoctorRepository;
import LIC.UC04v1.repositories.StudentRepository;
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

/*
    SOME NOTES ON THIS CLASS
        This class pulls in data from an excel file. It saves the first column as a doctor/student name
        and the second column as the doctor/student email. There are currently no checks in place to
        make sure the excel file is formatted correctly. Additionally, blank rows may be added to the
        database. We'll have to fix this and do extensive testing, but it works at a low fidelity!
    If you have questions about anything, let me (Katie) know. I'll come back and clean up/comment better soon.
 */

@Controller
public class ImportController {

    private DoctorRepository doctorRepository;
    private StudentRepository studentRepository;

    public ImportController(DoctorRepository doctorRepository, StudentRepository studentRepository){
        this.doctorRepository = doctorRepository;
        this.studentRepository = studentRepository;
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
                if(type.equals("doctors")) {
                    Doctor doc = new Doctor();
                    doc.setName(row.getCell(0).getStringCellValue());
                    doc.setEmail(row.getCell(1).getStringCellValue());
                    doctorRepository.save(doc);
                }
                else if(type.equals("students")) {
                    Student stu = new Student();
                    stu.setName(row.getCell(0).getStringCellValue());
                    stu.setEmail(row.getCell(1).getStringCellValue());
                    studentRepository.save(stu);
                }
            }
        }
        //Read in an older .xls Excel file
        else if (fileLocation.endsWith(".xls")) {
            HSSFWorkbook workbookXLS = new HSSFWorkbook(file.getInputStream());
            HSSFSheet worksheetXLS = workbookXLS.getSheetAt(0);

            for (int i = 1; i < worksheetXLS.getPhysicalNumberOfRows(); i++) {

                HSSFRow rowXLS = worksheetXLS.getRow(i);
                if(type.equals("doctors")) {
                    Doctor docXLS = new Doctor();
                    docXLS.setName(rowXLS.getCell(0).getStringCellValue());
                    docXLS.setEmail(rowXLS.getCell(1).getStringCellValue());
                    doctorRepository.save(docXLS);
                }
                else if(type.equals("students")) {
                    Student stuXLS = new Student();
                    stuXLS.setName(rowXLS.getCell(0).getStringCellValue());
                    stuXLS.setEmail(rowXLS.getCell(1).getStringCellValue());
                    studentRepository.save(stuXLS);
                }
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
