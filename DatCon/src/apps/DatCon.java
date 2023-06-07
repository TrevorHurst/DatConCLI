
package src.apps;


import java.io.IOException;
import src.Files.FileEnd;
import src.Files.NotDatFile;


import src.Files.ConvertDat;
import src.Files.CsvWriter;
import src.Files.DatFile;

public class DatCon {

    public static void main(String[] args) throws NotDatFile, IOException, FileEnd{
        String inFile = new String("C:/Users/thurst/Desktop/testicool/FLY005.DAT");//args[0];
        String outFile = new String("C:/Users/thurst/Desktop/testicool/FLY005.DAT.csv");//args[1];
        
        DatFile df = DatFile.createDatFile(inFile);
        df.preAnalyze(); // Lots of processing! freezes debugging
        long tickLower = df.lowestTickNo;
        long tickUpper = df.highestTickNo;
        long offset = 0; 
        
        CsvWriter csvWriter = new CsvWriter(outFile);
        
        
        ConvertDat convertDat = df.createConVertDat();
        convertDat.csvEventLogOutput = true;
        convertDat.tickRangeLower = tickLower;
        convertDat.tickRangeUpper = tickUpper;
        convertDat.timeOffset = offset;
        convertDat.csvWriter = csvWriter;
        convertDat.createRecordParsers();
        
        df.reset();
        
        convertDat.analyze(true); // Write the data! Freezes debugging
        
        csvWriter.close();
        
        System.out.println("DONE");
        }

}