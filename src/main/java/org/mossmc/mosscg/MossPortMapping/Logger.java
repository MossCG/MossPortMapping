package org.mossmc.mosscg.MossPortMapping;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Logger {
    public static boolean displayAPI;
    public static SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getNowTimeString() {
        return timeFormat.format(new Date(System.currentTimeMillis()));
    }
    public static void sendInfo(String info) {
        info = "[Info]["+getNowTimeString()+"] "+info;
        System.out.println(info);
        logMessage(info);
    }
    public static void sendWarn(String info) {
        info = "[Warn]["+getNowTimeString()+"] "+info;
        System.out.println(info);
        logMessage(info);
    }
    public static void sendAPI(String info) {
        info = "[API]["+getNowTimeString()+"] "+info;
        if (displayAPI) {
            System.out.println(info);
        }
        logMessage(info);
    }
    public static void sendException(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter= new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String info = "[Exception]["+getNowTimeString()+"] "+stringWriter;
        System.out.println(info);
        logMessage(info);
        try {
            printWriter.close();
            stringWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static File getLogFile;
    public static BufferedWriter getWriter;

    public static void loadLogger() {
        try {
            getLogFile = new File("./MPM/Logs/"+getNowTimeString().replace(":","-").replace(" ","-")+".yml");
            getWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(getLogFile.toPath()), StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("无法加载日志模块！");
            System.exit(1);
        }
    }

    public static void logMessage(String message) {
        if (getWriter == null) {
            return;
        }
        try {
            getWriter.write(message+"\r\n");
            getWriter.flush();
        } catch (IOException e) {
            sendException(e);
        }
    }
}
