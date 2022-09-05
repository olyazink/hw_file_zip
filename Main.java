import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        StringBuilder temp = new StringBuilder();
// В папке Games создайте несколько директорий: src, res, savegames, temp.
        File dir1 = new File("C:\\1\\Games\\src");
        File dir2 = new File("C:\\1\\Games\\res");
        File dir3 = new File("C:\\1\\Games\\savegames");
        File dir4 = new File("C:\\1\\Games\\temp");

        if (dir1.mkdir() && dir2.mkdir() && dir3.mkdir() && dir4.mkdir())
            temp.append("Каталоги src, res, savegames, temp созданы" + '\n');
        else
            temp.append("Не удалось создать каталоги" + '\n');

//В каталоге src создайте две директории: main, test.
        File dir5 = new File("C:\\1\\Games\\src\\main");
        File dir6 = new File("C:\\1\\Games\\src\\test");
        if (dir5.mkdir() && dir6.mkdir())
            temp.append("Каталоги main, test созданы" + '\n');
        else
            temp.append("Не удалось создать каталог" + '\n');

//В подкаталоге main создайте два файла: Main.java, Utils.java.
        File dir7 = new File("C:\\1\\Games\\src\\main", "Main.java");
        File dir8 = new File("C:\\1\\Games\\src\\main", "Utils.java");
        if (dir7.mkdir() && dir8.mkdir())
            temp.append("Файлы Main.java, Utils.java созданы" + '\n');
        else
            temp.append("Не удалось создать файлы" + '\n');

//В каталог res создайте три директории: drawables, vectors, icons.
        File dir9 = new File("C:\\1\\Games\\res\\drawables");
        File dir10 = new File("C:\\1\\Games\\res\\vectors");
        File dir11 = new File("C:\\1\\Games\\res\\icons");
        if (dir9.mkdir() && dir10.mkdir() && dir11.mkdir())
            temp.append("Каталоги drawables, vectors, icons созданы" + '\n');
        else
            temp.append("Не удалось создать каталог" + '\n');

// В директории temp создайте файл temp.txt.
        File myFile = new File("C:\\1\\Games\\temp\\temp.txt");
        try {
            if (myFile.createNewFile())
                temp.append("Файл temp.txt был создан" + '\n');
        } catch (IOException ex) {
            temp.append(ex.getMessage());
        }

        String text = temp.toString();
        try (FileWriter writer = new FileWriter("C:\\1\\Games\\temp\\temp.txt", false)) {
            // запись всей строки
            writer.write(text);
            // дозаписываем и очищаем буфер
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress gp1 = new GameProgress(100, 10, 1, 3.5);
        GameProgress gp2 = new GameProgress(90, 5, 3, 10.1);
        GameProgress gp3 = new GameProgress(10, 1, 8, 15.6);

        saveGames("C:\\1\\Games\\savegames\\save1.dat", gp1);
        saveGames("C:\\1\\Games\\savegames\\save2.dat", gp2);
        saveGames("C:\\1\\Games\\savegames\\save3.dat", gp3);

        String[] fileSave = {"C:\\1\\Games\\savegames\\save1.dat", "C:\\1\\Games\\savegames\\save2.dat", "C:\\1\\Games\\savegames\\save3.dat"};
        zipFiles("C:\\1\\Games\\savegames\\savegames.zip", fileSave);

        // delete
        for (int i = 0; i < fileSave.length; i++) {
            File newFile = new File(fileSave[i]);
            newFile.delete();
        }
    }

    public static void saveGames(String str, GameProgress gp) {
        // откроем выходной поток для записи в файл
        try (FileOutputStream fos = new FileOutputStream(str);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(gp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZip, String[] fileSave) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(pathZip));)
             {
             for(int i = 0; i < fileSave.length; i++) {
                 try (FileInputStream fis = new FileInputStream(fileSave[i]);) {
                     ZipEntry entry = new ZipEntry(("save" + (i + 1) + ".dat"));
                     zout.putNextEntry(entry);
                     // считываем содержимое файла в массив byte
                     byte[] buffer = new byte[fis.available()];
                     fis.read(buffer);
                     // добавляем содержимое к архиву
                     zout.write(buffer);
                     // закрываем текущую запись для новой записи
                     zout.closeEntry();
                 }
             }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
