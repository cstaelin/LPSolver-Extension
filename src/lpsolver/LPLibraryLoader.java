
package org.nlogo.extensions.lpsolver;

/**
 * Loads the lpsolve55 libraries appropriate to the platform.  At the
 * moment, only 64 bit libraries are available.
 * @author cstaelin - September 2025
 */
public class LPLibraryLoader {
    
    public static void loadit() {
        String extensionDirectory
                = org.nlogo.api.FileIO.perUserDir("extensions", false);
        String fileSeparator = System.getProperty("file.separator");
        extensionDirectory = extensionDirectory + fileSeparator + "lpsolver";
        
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        String jvmArchitecture = System.getProperty("os.arch").toLowerCase();
        String arch = "";
        if (jvmArchitecture.contains("64")) {
            arch = "64";
        }
        
        if (operatingSystem.contains("win")) {
            String subDir = "win" + arch;
            System.load(extensionDirectory + fileSeparator + subDir
                    + fileSeparator + "lpsolve55.dll");
            System.load(extensionDirectory + fileSeparator + subDir
                    + fileSeparator + "lpsolve55j.dll");
        } else if (operatingSystem.contains("linux")) {
            String subDir = "lnx" + arch;
            System.load(extensionDirectory + fileSeparator + subDir
                    + fileSeparator + "liblpsolve55.so");
            System.load(extensionDirectory + fileSeparator + subDir
                    + fileSeparator + "liblpsolve55j.so");
        } else if (operatingSystem.contains("mac")){
            String subDir = "osx" + arch;
            System.load(extensionDirectory + fileSeparator + subDir
                    + fileSeparator + "liblpsolve55.dylib");
            System.load(extensionDirectory + fileSeparator + subDir
                    + fileSeparator + "liblpsolve55j.jnilib");
        } else {
            // Don't know this OS, but LPSolverExtension should have caught 
            // this before getting here.
        }
    }
}
