package gitDemo.implementations;

import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;
import java.util.ArrayList;
import java.util.List;

public class R24261 implements IA {

    @Override
    public String getStudyNumber() {
        return "R24261";
    }

    @Override
    public List<StringDriverTimeTuple> convert(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }

        List<StringDriverTimeTuple> result = new ArrayList<>();

        String[] records = text.split(";");

        for (String record : records) {
            String trimmedRecord = record.trim();
            if (trimmedRecord.isEmpty()) continue;

            String[] parts = trimmedRecord.split(",", 2);

            if (parts.length == 2) {
                String name = parts[0].trim();
                String time = parts[1].trim();

                if (!name.isEmpty() && !time.isEmpty()) {
                    result.add(new StringDriverTimeTuple(name, time));
                }
            }
        }

        return result;
    }
}