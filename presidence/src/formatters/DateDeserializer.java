package formatters;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement arg0, java.lang.reflect.Type arg1, JsonDeserializationContext arg2) {
            try {
                String dateStr = arg0.getAsString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = dateFormat.parse(dateStr);
                return new Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }

      
    }