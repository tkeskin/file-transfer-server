package tr.com.tkeskin.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {

    public String asString(Date date) {

        return date != null ? new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(date) : null;
    }

    public Date asDate(String date) {

        try {
            return date != null ? new SimpleDateFormat("dd-MM-yyyy HH:mm")
                    .parse(date) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}