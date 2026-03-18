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

            if (trimmedRecord.isEmpty()) {
                continue;
            }

            int lastSpaceIndex = trimmedRecord.lastIndexOf(' ');

            if (lastSpaceIndex != -1) {
                String name = trimmedRecord.substring(0, lastSpaceIndex).trim();
                String time = trimmedRecord.substring(lastSpaceIndex + 1).trim();

                if (!name.isEmpty() && !time.isEmpty()) {
                    result.add(new StringDriverTimeTuple(name, time));
                }
            }
        }

        return result;
    }
}