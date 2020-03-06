package helper;

import java.io.*;

public class WriteReadFile {

    public static void writeRecord(long time,String filename) {
        try (DataOutputStream output =
                     new DataOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(filename)))) {
            output.writeLong(time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static long readRecord(String filename) {
        try (DataInputStream input =
                     new DataInputStream(
                             new BufferedInputStream(
                                     new FileInputStream(filename)))) {
            return input.readLong();
        } catch (EOFException ex) {
            System.out.print("End of file");
        } catch (FileNotFoundException ex){
            return(-1l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

       return -2l;
    }

    public static void initialize(String filename){
        try (DataOutputStream output =
                     new DataOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(filename)))) {
            output.writeLong(12345678);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
