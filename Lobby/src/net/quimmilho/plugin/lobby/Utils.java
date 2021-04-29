package net.quimmilho.plugin.lobby;

import org.bukkit.ChatColor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Utils {

    public static void start() {
        String pluginPath = new File(Core.class.getProtectionDomain().getCodeSource().getLocation()
                .getFile()).getPath();
        System.out.println(pluginPath);
        String regex = Pattern.quote(File.separator);
        String[] pathBroken = pluginPath.split(regex);
        String path = "";
        for (int i = 0; i < pathBroken.length - 2; i++) {
            path += pathBroken[i] + "/";
        }
        System.out.println("\n\n\n" + path + "\n\n");
        Core.getInstance().setMainPath(path);
        path = "";
        for (int i = 0; i < pathBroken.length - 3; i++) {
            path += pathBroken[i] + "/";
        }
        path += "/info/";
        System.out.println("\n\n\n" + path + "\n\n");
        Core.getInstance().setPluginMainPath(path);
    }

    public static HashMap<String, String> readFile(String pathStr) {
        HashMap<String, String> map = new HashMap<>();
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            return null;
        } else {
            try {
                String str = readFileStr(path);
                String[] strBroken = str.split("<");
                for (String s : strBroken) {
                    String[] strBroken2 = s.split(">");
                    if (strBroken2.length == 2) {
                        map.put(strBroken2[0], strBroken2[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                Core.getInstance().getLogger().info(ChatColor.RED + "Error reading " + pathStr + "!");
            }
        }
        return map;
    }

    public static String readFileStr(String pathStr) {
        HashMap<String, String> map = new HashMap<>();
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            return null;
        } else {
            try {
                String str = readFileStr(path);
                return str;
            } catch (IOException e) {
                e.printStackTrace();
                Core.getInstance().getLogger().info(ChatColor.RED + "Error reading " + pathStr + "!");
            }
        }
        return null;
    }


    private static String readFileStr(Path path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        String str = "";
        String line;
        while ((line = br.readLine()) != null) {
            str += (line + "<");
        }
        br.close();
        return str;
    }

    public static boolean saveFile(HashMap<String, String> map, String pathStr) throws IOException {
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            return true;
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()));
        for (String str : map.keySet()) {
            bw.write(str + ">" + map.get(str));
            bw.newLine();
        }
        bw.close();
        return false;
    }

    public static boolean saveFile(String str, String pathStr, boolean keep) throws IOException {
        Path path = Paths.get(pathStr);
        if (!Files.exists(path)) {
            return true;
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()));
        if (keep) {
            String s = readFileStr(path);
            String[] sBroken = s.split("<");
            for (String string : sBroken) {
                bw.write(string);
                bw.newLine();
            }
        }
        bw.write(str);
        bw.close();
        return false;
    }

}
