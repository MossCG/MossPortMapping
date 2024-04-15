package org.mossmc.mosscg.MossPortMapping;

import org.mossmc.mosscg.MossLib.Command.CommandManager;
import org.mossmc.mosscg.MossLib.Config.ConfigManager;
import org.mossmc.mosscg.MossLib.File.FileCheck;
import org.mossmc.mosscg.MossLib.Object.ObjectLogger;
import org.mossmc.mosscg.MossPortMapping.Tunnel.TunnelCache;
import org.mossmc.mosscg.MossPortMapping.User.UserCache;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //模式检查
        if (Arrays.toString(args).toLowerCase().contains("-mode=server")) BasicInfo.runMode = BasicInfo.mode.SERVER;
        if (Arrays.toString(args).toLowerCase().contains("-mode=client")) BasicInfo.runMode = BasicInfo.mode.CLIENT;
        if (BasicInfo.runMode == null) {
            System.out.println("Set mode with -mode=server or -mode=client!");
            System.exit(1);
        }
        //预加载部分
        FileCheck.checkDirExist("./MossPortMapping");
        ObjectLogger logger = new ObjectLogger("./MossPortMapping/logs");
        BasicInfo.logger = logger;
        if (BasicInfo.runMode.equals(BasicInfo.mode.SERVER)) FileCheck.checkDirExist("./MossPortMapping/user");
        if (BasicInfo.runMode.equals(BasicInfo.mode.CLIENT)) FileCheck.checkDirExist("./MossPortMapping/tunnel");
        //基础信息输出
        logger.sendInfo("欢迎使用MossPortMapping端口映射软件");
        logger.sendInfo("软件版本：" + BasicInfo.version);
        logger.sendInfo("软件作者：" + BasicInfo.author);
        logger.sendInfo("运行模式：" + BasicInfo.runMode);
        //配置读取
        logger.sendInfo("正在读取配置文件......");
        if (BasicInfo.runMode.equals(BasicInfo.mode.SERVER)) {
            ConfigManager.getConfigObject("./MossPortMapping", "config.yml", "configServer.yml");
            logger.sendInfo("正在加载用户数据......");
            UserCache.loadUser();
        }
        if (BasicInfo.runMode.equals(BasicInfo.mode.CLIENT)) {
            ConfigManager.getConfigObject("./MossPortMapping", "config.yml", "configClient.yml");
            logger.sendInfo("正在加载隧道数据......");
            TunnelCache.loadTunnel();
        }
        //命令行
        CommandManager.initCommand(logger,true);

    }
}
