package Parser;

import Payment.*;
import Enums.TypeOfFile;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.stream.Stream;


public class FileReader {

    private final FileParser parser;

    /**
     * FileReader reads the file and start parsing it
     */
    public FileReader() {
        parser = new FileParser();
    }


    /**
     * This method receives a file and checks for the file type in order to parse it right
     *
     * @param file is the given file to parse
     * @throws NoSuchFileException if an unsupported file is detected
     * @return a payment object
     */
    public PaymentType parseReader(File file) {
        PaymentType payment = null;
        try {
            ArrayList<String> text = new ArrayList<>();
            try (Stream<String> lines = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1)) {
                lines.forEachOrdered(text::add);
            }

            for (String line : text)
                if (file.getName().endsWith(TypeOfFile.DISBURSEPAYMENT.NAME))
                    payment = parser.parseFile(line, TypeOfFile.DISBURSEPAYMENT);
                else if (file.getName().endsWith(TypeOfFile.INCOMINGPAYMENT.NAME))
                    payment = parser.parseFile(line, TypeOfFile.INCOMINGPAYMENT);
                else
                    throw new NoSuchFileException(file + " Is not supported");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }
}
