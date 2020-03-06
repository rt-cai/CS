package test;

import org.junit.jupiter.api.Test;

import static helper.WriteReadFile.readRecord;
import static helper.WriteReadFile.writeRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteReadFileTest {

    @Test
    void testWriteRecord(){
        writeRecord((long)2,"test.dat");
        assertEquals(2, readRecord("test.dat"));
    }


}
