package uk.gov.hmcts.befta.launchdarkly;

import com.launchdarkly.sdk.server.LDClient;
import uk.gov.hmcts.befta.util.EnvironmentVariableUtils;

public class LaunchDarklyConfig {

    public static final String LD_SDK_KEY = "LD_SDK_KEY";
    public static final String LAUNCH_DARKLY_ENV = "LAUNCH_DARKLY_ENV";
    public static final String MICROSERVICE_NAME = "MICROSERVICE_NAME";

    private LaunchDarklyConfig() { }

    private static LDClient ldClient;

    public static LDClient getLdInstance() {
        if (ldClient == null) {
            synchronized (LaunchDarklyConfig.class) {
                if (ldClient == null && System.getenv(LD_SDK_KEY) != null) {
                    ldClient = new LDClient(System.getenv(LD_SDK_KEY));
                }
            }
        }
        return ldClient;
    }

    public static String getEnvironmentName() {
       return System.getenv(LAUNCH_DARKLY_ENV);
    }

    public static String getLDMicroserviceName() {
        return System.getenv(MICROSERVICE_NAME);
    }
}