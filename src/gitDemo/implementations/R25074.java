package gitDemo.implementations;
import gitDemo.interfaces.IC;
import gitDemo.types.StringDriverTimeTuple;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import gitDemo.types.DriverTime;

public class R25074 implements IC{
    //@Override
    public List<StringDriverTimeTuple> convert(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes) {

        //toto zatím není funkční implementace

        List<DriverTime> driverTimeRecords = new ArrayList<>();

        for (int i = 0; i < driverTimes.size(); i++) {


        }


        return driverTimes;
    }


    @Override
    public String getStudyNumber() {
        return "r25074";
    }

}
