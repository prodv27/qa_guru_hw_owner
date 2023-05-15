package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:api.properties"
})
public interface ApiConfig extends Config {
    @Key("api.url")
    String url();
}
