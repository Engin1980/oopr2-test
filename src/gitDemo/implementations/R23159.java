package gitDemo.implementations;

import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;
import java.util.ArrayList;
import java.util.List;

public class R23159 implements IA {

    @Override
    public List<StringDriverTimeTuple> convert(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Vstupní text nesmí být null.");
        }

        List<StringDriverTimeTuple> result = new ArrayList<>();

        String[] rawRecords = text.split(";");

        for (String rawRecord : rawRecords) {
            String trimmedRecord = rawRecord.trim();

            if (trimmedRecord.isEmpty()) {
                continue;
            }

            int lastSpaceIndex = trimmedRecord.lastIndexOf(' ');

            if (lastSpaceIndex != -1) {
                String driverName = trimmedRecord.substring(0, lastSpaceIndex).trim();
                String time = trimmedRecord.substring(lastSpaceIndex + 1).trim();

                if (!driverName.isEmpty() && !time.isEmpty()) {
                    result.add(new StringDriverTimeTuple(driverName, time));
                }
            }
        }

        return result;
    }

    @Override
    public String getStudyNumber() {
        return "R23159";
    }
}