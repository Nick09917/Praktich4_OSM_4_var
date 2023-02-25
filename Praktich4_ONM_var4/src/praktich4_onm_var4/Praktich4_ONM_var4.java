
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
            String fileNameXML = dir + "array_dat7.xml"; // Имя XML-файла для работы
            String fileNameProp = dir + "array_dat7.prop"; // Имя Prop-файла для работы
            System.out.println(fileNameXML);
            System.out.println(fileNameProp);
            Properties p = new Properties(); // Переменная для хранения xml-данных
            File f1 = new File(fileNameXML); // Переменная для доступа к файлу fileNameXML
            File f2 = new File(fileNameProp); // Переменная для доступа к файлу fileNameProp
            String comment = new Date().toString();
             if ((f1.exists() == false) || f2.exists() == false) {
                // Создаем случайные данные для файлов
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        k =  -10 + (10 + 10) * randomdoub.nextDouble(); // В моем задание указаны вещ цисла, Делаем рандом
                        p.put("m" + i + j, String.valueOf(k));   // Для вещественных чисел, используя random
                       // p.put("m" + 2 + 2, String.valueOf(0.0)); /// Добавления Нуля в ручную, так как выбить 0 в рандоме
                    }                                                   /// Редкая вещица
                }
                // Сохранение обработанных данных массива в файлы
                p.storeToXML(new FileOutputStream(fileNameXML), comment);
                p.store(new FileOutputStream(fileNameProp), comment);
            } else { // Если файл существует, то
                // Загружаем xml-данные из файла в переменную p для сохранения существующих значений
                p.loadFromXML(new FileInputStream(fileNameXML));
                //p.load(new FileInputStream(fileNameProp));
            }

            System.out.println("Сгенерированный Массив:");
             for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    k = Double.parseDouble(p.getProperty("m" + i + j, "0"));
                    m[i][j] = Math.round(k * 100.0) / 100.0;
                    System.out.format("%.2f",k );
                    System.out.print("  ");
                }
                System.out.println("");
            }
             /// Релизация алгоритма задания
             int counter = 0;  // Заводим счетчик, для подсчета 0 
             for(int i = 0; i<R; i++){
                 for (int j = 0; j < C; j++){
                     if(m[i][j] == 0){    /// Если 0 будет найден, мы инкреметируем счетчик
                         counter++;
                     }
                 }
             }
    
            if(counter>0){   /// Если счетчик больше 0, То проводим дейтсвия алгоритма
                System.out.println("0 найден в таблице");
                System.out.println("Проводим действия алгоритма");
                for(int i = 0; i<R; i++){
                    for(int j = 0; j<C; j++){
                        if( m[i][j] % 1 != 0){   // данный хитрый способ делания по модулю поможет , нам понять Int или Double
                            m[i][j] = 1;  // Любое целое число, при деление по модулю на 1, выдаст 0, в то время как вещественные
                        }                 // выдадут не целый 0
                    }
                }
            }else{
                System.out.println("0 не был обнаружен, попробуйте сгенерировать список снова"); // ответ в случае не нахождения 0
            }
              
            
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    // Сохраняем данные в переменную, хранящую данные xml
                    p.put("r" + i + j, String.valueOf(m[i][j]));
                }
            }
             // Сохранение обработанных данных массива в XML-файл
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
            System.err.println("Error working with XML-file!"); // Вывести сообщение об ошибке            
        }

   
    
    }
    
}
    

