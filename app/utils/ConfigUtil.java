package global;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;

public class ConfigUtil {
    private static play.Logger.ALogger logger = play.Logger.of(ConfigUtil.class);
    public static Object getConfig(String name) {
        try {
            return ConfigFactory.load().getValue(name).unwrapped();
        } catch(ConfigException.Missing missing) {
            logger.warn(String.format("Config value missing at path %s", name));
            return null;
        }
    }
}
