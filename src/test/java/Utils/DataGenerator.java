package Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class DataGenerator {

    //Generate Random email
    public String getEmail() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        String email = "James@" + randomUUIDString.split("-")[2] + ".com";
        return email;
    }

    //Get date
    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String dateToUse = (formatter.format(calendar.getTime()));
        return dateToUse;
    }

    //Data for Reset Customer Fields
    public ArrayList<String> resetCustomerData() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("Bob Raymen");
        ar.add("987125");
        ar.add("Bob_15@yahoo.com");
        ar.add("Hopkins");
        return ar;
    }

    public static void main(String[] args) {
        DataGenerator r = new DataGenerator();

    }
}
