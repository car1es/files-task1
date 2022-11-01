package files.task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> paths = new ArrayList<>();
        StringBuilder log = new StringBuilder();

        paths.add("Games");
        paths.add("Games/src");
        paths.add("Games/src/main");
        paths.add("Games/src/test");
        paths.add("Games/res");
        paths.add("Games/res/drawables");
        paths.add("Games/res/vectors");
        paths.add("Games/res/icons");
        paths.add("Games/savegames");
        paths.add("Games/temp");

        try {
            for (String s : paths) {
                File folder = new File(s);
                if (folder.mkdir()) {
                    logs(log, folder, true);
                    if (s.equals("Games/src/main")) {
                        File file = new File(s, "Main.java");
                        if (file.createNewFile()) {
                            logs(log, file, true);
                        } else {
                            logs(log, file, false);
                        }
                        file = new File(s, "Utils.java");
                        if (file.createNewFile()) {
                            logs(log, file, true);
                        } else {
                            logs(log, file, false);
                        }
                    }
                    if (s.equals("Games/temp")){
                        File file = new File(s, "temp.txt");
                        if (file.createNewFile()) {
                            logs(log, file, true);
                        } else {
                            logs(log, file, false);
                        }
                    }
                }else {
                    logs(log,folder,false);
                }
            }
            FileWriter logtmp = new FileWriter("Games/temp/temp.txt",true);
            logtmp.write(log.toString());
            logtmp.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void logs(StringBuilder log, File file, boolean b) {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m:s.S");
        log
                .append("\n")
                .append(sdf.format(file.lastModified()) + " ")
                .append(b ? "" : "Ошибка! ")
                .append(file.isDirectory() ? "Папка " : "Файл: ")
                .append(file.getAbsolutePath())
                .append(b ? " - создан" : " - не создан");
    }

}
