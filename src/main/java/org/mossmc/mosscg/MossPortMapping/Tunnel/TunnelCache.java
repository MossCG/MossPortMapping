package org.mossmc.mosscg.MossPortMapping.Tunnel;

import org.mossmc.mosscg.MossLib.Config.ConfigManager;
import org.mossmc.mosscg.MossLib.Object.ObjectConfig;
import org.mossmc.mosscg.MossPortMapping.BasicInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TunnelCache {
    public static Map<String, ObjectConfig> tunnelMap = new HashMap<>();

    public static void loadTunnel() {
        File dir = new File("./MossPortMapping/tunnel");
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            try {
                ObjectConfig data = ConfigManager.getConfigObject("./MossPortMapping/tunnel", file.getName(), null);
                assert data != null;
                tunnelMap.put(data.getString("tunnelID"),data);
                BasicInfo.logger.sendInfo("读取隧道配置文件："+file.getName()+" : "+data.getString("tunnelID"));
            } catch (Exception e) {
                e.printStackTrace();
                BasicInfo.logger.sendWarn("读取隧道配置文件错误："+file.getName());
            }
        }
    }
}
