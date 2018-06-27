package me.mao.minigame.arena.rollback;

import me.mao.minigame.Core;
import org.bukkit.World;

import java.io.*;

public class RollbackHandler {


    private static RollbackHandler rollbackHandler = new RollbackHandler();
    private RollbackHandler(){}
    public static RollbackHandler getRollbackHandler() {
        return rollbackHandler;
    }

    public void rollback(World world) {

        Core.getInstance().getServer().unloadWorld(world, false);

        String originalName = world.getName().split("_")[0];

        rollback(originalName);
    }

    public void rollback(String worldName) {
        String rootDirectory = Core.getInstance().getServer().getWorldContainer().getAbsolutePath();

        File srcFolder = new File(rootDirectory + "/" + worldName);
        File destFolder = new File(rootDirectory + "/" + worldName + "_active");

        delete(destFolder);
        try {
            copyFolder(srcFolder, destFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(File delete){
        if (delete.isDirectory()) {
            String[] files = delete.list();

            if (files != null) {
                for (String file : files) {
                    File toDelete = new File(file);
                    delete(toDelete);
                }
            }
        } else {
            delete.delete();
        }
    }

    private void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
            }

            // list all the directory contents
            String files[] = src.list();

            if (files != null) {
                for (String file : files) {
                    //construct the src and dest file structure
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    //recursive copy
                    copyFolder(srcFile, destFile);
                }
            }
        } else {
            // if file, then copy it
            // Use byte stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

}
