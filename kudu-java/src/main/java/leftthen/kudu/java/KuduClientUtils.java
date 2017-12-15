package leftthen.kudu.java;

import org.apache.kudu.client.KuduClient;

public class KuduClientUtils {
    public static KuduClient getDefaultKuduClient() {
        return getKuduClient("172.16.50.21:7051");
    }

    public static KuduClient getKuduClient(String address) {
        return new KuduClient.KuduClientBuilder(address).build();
    }
}
