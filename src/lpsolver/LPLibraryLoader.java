/* Version 4.2.0
 * Written by Charles Staelin, September 2025.
 */

package org.nlogo.extensions.lpsolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.nlogo.api.ExtensionException;

/*
 * Loads the lpsolve55 libraries appropriate to the platform.  We assume the
 * the libraries have been packed into the lpsolver.jar file in the /lib area
 * of that file.
 */
public class LPLibraryLoader {

    public static void loadit() throws IOException, ExtensionException {

        String osName = System.getProperty("os.name").toLowerCase();
        // LPSolverExtension.writeToNetLogo(jTempDir, false, LPSetupProblem.ctx);
        
        // Construct the locations of the appropriate libraries in the 
        // jar file. The "/" is used with the jar file regardless of 
        // platform.
        String libDir = findLibraries(osName) + "/";
        String lib1Name = "";
        String lib2Name = "";

        if (osName.contains("win")) {
            lib1Name = "lpsolve55.dll";
            lib2Name = "lpsolve55j.dll";
        } else if (osName.contains("linux")) {
            lib1Name = "liblpsolve55.so";
            lib2Name = "liblpsolve55j.so";
        } else if (osName.contains("mac")){
            lib1Name = "liblpsolve55.dylib";
            lib2Name = "liblpsolve55j.jnilib";
        } else {
            // Don't know this OS, but LPSolverExtension should have caught 
            // this upon initialization.
        }
        String lib1 = libDir + lib1Name;    
        String lib2 = libDir + lib2Name;
        // LPSolverExtension.writeToNetLogo(lib1 + "   " + lib2 ,false, LPSetupProblem.ctx); 

        // Now find (or create) the directory to hold the libraries in the 
        // temp directory specified by java in the java.io.tempdir" 
        // property.  
        // Files.createDirectories does not choke if the directory already 
        // exists, so we don't do that check.
        // Note that we mark these temp directories and files for deletion
        // when this session ends.
        Path jTempPath = Paths.get(System.getProperty("java.io.tmpdir"));
        jTempPath = jTempPath.resolve("lpsolver" + LPSolverExtension.LPSVersion);
        jTempPath = Files.createDirectories(jTempPath);
        jTempPath.toFile().deleteOnExit();
        // LPSolverExtension.writeToNetLogo(jTempPath.toString(), false, LPSetupProblem.ctx);

        // Now copy the libraries if they are not already there.
        Path lib1Path = jTempPath.resolve(lib1Name);
        if (!Files.exists(lib1Path)) {
            Files.copy(LPSolverExtension.class.getResourceAsStream("/lib/" + lib1), 
                    lib1Path);
        }
        lib1Path.toFile().deleteOnExit();
        Path lib2Path = jTempPath.resolve(lib2Name);
        if (!Files.exists(lib2Path)) {
            Files.copy(LPSolverExtension.class.getResourceAsStream("/lib/" + lib2), 
                    lib2Path);
        }
        lib2Path.toFile().deleteOnExit();
        // LPSolverExtension.writeToNetLogo(lib1Path.toString() + "  " 
        //      + lib2Path.toString(), false, LPSetupProblem.ctx);
        // And load them.
        System.load(lib1Path.toString());
        System.load(lib2Path.toString());        
    }

    public static String findLibraries (String osName) {
        
    // This simply determines the platform on which it is being run, 
    // and returns it.
        
        String jvmArchitecture = System.getProperty("os.arch").toLowerCase();

        if (osName.contains("mac")) {
            if (jvmArchitecture.contains("arch64")) {
                return "macosx";
            } else if (jvmArchitecture.contains("x86_64")) {
                return "macx86";
            }
            
        } else if (osName.contains("win")) {
            if (jvmArchitecture.contains("64")) {
                return "win64";
            } else if (jvmArchitecture.contains("32")) {
                return "win32";
            }
            
        } else if (osName.contains("linux")) {
            if (jvmArchitecture.contains("64")) {
                return "lnx64";
            } else if (jvmArchitecture.contains("32")) {
                return "lnx32";
            } 
        }
    
    // we apparently don't have a supported OS.
    return "";
    }   
}
