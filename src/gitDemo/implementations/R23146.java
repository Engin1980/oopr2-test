package gitDemo.implementations;

import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;
import java.util.ArrayList;
import java.util.List;

public class R23146 implements IA {

    @Override
    public String getStudyNumber() {
        return "R23146";
    }

    @Override
    public List<StringDriverTimeTuple> convert(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        List<StringDriverTimeTuple> result = new ArrayList<>();

        String[] records = text.split(";");
        for (String record : records) {
            record = record.trim();
            if (record.isEmpty()) continue;

            String[] tokens = record.split("\\s+");
            if (tokens.length < 2) continue;

            String time = tokens[tokens.length - 1];

            StringBuilder nameBuilder = new StringBuilder();
            for (int i = 0; i < tokens.length - 1; i++) {
                if (i > 0) nameBuilder.append(' ');
                nameBuilder.append(tokens[i]);
            }

            String driverName = nameBuilder.toString().trim();
            if (driverName.isEmpty()) continue;

            result.add(new StringDriverTimeTuple(driverName, time));
        }

        return result;
    }
}
