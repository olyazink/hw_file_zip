import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

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
    }

}
