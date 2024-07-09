package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.service.impl.CsvFileReaderServiceImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CsvFileReaderServiceImplTest {
    private static final String FRUIT_NAME_STRING = "fruit_name";
    private static final String QUANTITY_STRING = "quantity";
    private static final String QUALITY_STRING = "quality";
    private final CsvFileReaderServiceImpl readerService = new CsvFileReaderServiceImpl();

    @Test
    void readFromFile_valideFile_throwsException() throws IOException {
        Path tempPath = File.createTempFile("test", ".csv").toPath();
        Files.write(tempPath, List.of(FRUIT_NAME_STRING, QUANTITY_STRING, QUALITY_STRING));

        List<String> readedLines = readerService.readFromFile(tempPath.toString());
        assertEquals(2, readedLines.size());
        assertTrue(readedLines.contains("quantity"));
        assertTrue(readedLines.contains("quality"));

        Files.deleteIfExists(tempPath);
    }

    @Test
    void readFromFile_invalidFile_throwsException() {
        assertThrows(RuntimeException.class, () ->
                readerService.readFromFile("non-existed.file"));
    }
}
