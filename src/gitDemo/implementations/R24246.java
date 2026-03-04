package gitDemo.implementations;

import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;

import java.util.ArrayList;
import java.util.List;

public class R24246 implements IA {

    @Override
    public String getStudyNumber() {
        return "R24246";
    }

    @Override
    public List<StringDriverTimeTuple> convert(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Vstupní text nesmí být null.");
        }

        List<StringDriverTimeTuple> result = new ArrayList<>();

        String[] records = text.split(";");

        for (String record : records) {
            record = record.trim();
            if (record.isEmpty()) {
                continue;
            }

            String[] parts = record.split("\\s+");

            if (parts.length < 2) {
                continue;
            }

            String time = parts[parts.length - 1];

            StringBuilder driverNameBuilder = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                if (i > 0) {
                    driverNameBuilder.append(" ");
                }
                driverNameBuilder.append(parts[i]);
            }
            String driverName = driverNameBuilder.toString();

            result.add(new StringDriverTimeTuple(driverName, time));
        }

        return result;
    }
}