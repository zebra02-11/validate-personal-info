package personalinfovalidation;

import org.junit.platform.commons.util.StringUtils;

public interface Validator {

    static boolean validateByContext(String line, String regexp){
        return !StringUtils.isBlank(line) && line.matches(regexp);

    }
}
