
package praktich4_onm_var4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Praktich4_ONM_var4 {

   
    public static void main(String[] args) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException {
      
        final int R = 4;
        final int C = 5;
        double [][] m = new double[R][C];
        double k;
        Random randomdoub = new Random();
      try{
           String dir = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator");
            String fileNameXML = dir + "array_dat7.xml"; // ��� XML-����� ��� ������
            String fileNameProp = dir + "array_dat7.prop"; // ��� Prop-����� ��� ������
            System.out.println(fileNameXML);
            System.out.println(fileNameProp);
            Properties p = new Properties(); // ���������� ��� �������� xml-������
            File f1 = new File(fileNameXML); // ���������� ��� ������� � ����� fileNameXML
            File f2 = new File(fileNameProp); // ���������� ��� ������� � ����� fileNameProp
            String comment = new Date().toString();
             if ((f1.exists() == false) || f2.exists() == false) {
                // ������� ��������� ������ ��� ������
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        k =  -10 + (10 + 10) * randomdoub.nextDouble(); // � ���� ������� ������� ��� �����, ������ ������
                        p.put("m" + i + j, String.valueOf(k));   // ��� ������������ �����, ��������� random
                       // p.put("m" + 2 + 2, String.valueOf(0.0)); /// ���������� ���� � ������, ��� ��� ������ 0 � �������
                    }                                                   /// ������ ������
                }
                // ���������� ������������ ������ ������� � �����
                p.storeToXML(new FileOutputStream(fileNameXML), comment);
                p.store(new FileOutputStream(fileNameProp), comment);
            } else { // ���� ���� ����������, ��
                // ��������� xml-������ �� ����� � ���������� p ��� ���������� ������������ ��������
                p.loadFromXML(new FileInputStream(fileNameXML));
                //p.load(new FileInputStream(fileNameProp));
            }

            System.out.println("��������������� ������:");
             for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    k = Double.parseDouble(p.getProperty("m" + i + j, "0"));
                    m[i][j] = Math.round(k * 100.0) / 100.0;
                    System.out.format("%.2f",k );
                    System.out.print("  ");
                }
                System.out.println("");
            }
             /// ��������� ��������� �������
             int counter = 0;  // ������� �������, ��� �������� 0 
             for(int i = 0; i<R; i++){
                 for (int j = 0; j < C; j++){
                     if(m[i][j] == 0){    /// ���� 0 ����� ������, �� ������������� �������
                         counter++;
                     }
                 }
             }
    
            if(counter>0){   /// ���� ������� ������ 0, �� �������� �������� ���������
                System.out.println("0 ������ � �������");
                System.out.println("�������� �������� ���������");
                for(int i = 0; i<R; i++){
                    for(int j = 0; j<C; j++){
                        if( m[i][j] % 1 != 0){   // ������ ������ ������ ������� �� ������ ������� , ��� ������ Int ��� Double
                            m[i][j] = 1;  // ����� ����� �����, ��� ������� �� ������ �� 1, ������ 0, � �� ����� ��� ������������
                        }                 // ������� �� ����� 0
                    }
                }
            }else{
                System.out.println("0 �� ��� ���������, ���������� ������������� ������ �����"); // ����� � ������ �� ���������� 0
            }
              
            
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    // ��������� ������ � ����������, �������� ������ xml
                    p.put("r" + i + j, String.valueOf(m[i][j]));
                }
            }
             // ���������� ������������ ������ ������� � XML-����
            p.storeToXML(new FileOutputStream(fileNameXML), comment);
            p.store(new FileOutputStream(fileNameProp), "Source and processed array data");
            System.out.println("New matrix:");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                System.out.print(p.getProperty("r" + i + j, "?") + " ");
                }
                System.out.println("");
            }
            } catch (Exception e) {
            System.err.println("Error working with XML-file!"); // ������� ��������� �� ������            
        }

   
    
    }
    
}
    

