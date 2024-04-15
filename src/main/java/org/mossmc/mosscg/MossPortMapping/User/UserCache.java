package org.mossmc.mosscg.MossPortMapping.User;

import org.mossmc.mosscg.MossLib.Config.ConfigManager;
import org.mossmc.mosscg.MossLib.Object.ObjectConfig;
import org.mossmc.mosscg.MossPortMapping.BasicInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserCache {
    public static Map<String, ObjectConfig> userMap = new HashMap<>();
    
    public static void loadUser() {
        File dir = new File("./MossPortMapping/user");
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            try {
                ObjectConfig data = ConfigManager.getConfigObject("./MossPortMapping/user", file.getName(), null);
                assert data != null;
                userMap.put(data.getString("userID"),data);
                BasicInfo.logger.sendInfo("读取用户配置文件："+file.getName()+" : "+data.getString("userID"));
            } catch (Exception e) {
                e.printStackTrace();
                BasicInfo.logger.sendWarn("读取用户配置文件错误："+file.getName());
            }
        }
    }
}
