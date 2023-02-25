package org.mossmc.mosscg.MossPortMapping;

import static org.mossmc.mosscg.MossPortMapping.Logger.*;

public class Main {
    public static void main(String[] args) {
        Logger.loadLogger();
        sendInfo("欢迎使用MossPortMapping端口映射软件");
        sendInfo("软件版本：" + BasicInfo.version);
        sendInfo("软件作者：" + BasicInfo.author);
    }
}
