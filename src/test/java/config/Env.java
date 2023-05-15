package config;

import org.aeonbits.owner.ConfigFactory;

public class Env {
    public static class API {
        public final static ApiConfig CONFIG = ConfigFactory.create(ApiConfig.class);
    }
}
