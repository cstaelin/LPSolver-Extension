/**
 * Version 4.1.0- September  2025
 * 
 * The LPSolverExtension class serves as the front end to the lpsolver55 
 * library of linear programming routines.  Please see the license.md file 
 * that comes with this extension for the licensing of both the lpsolver
 * package and this extension.
 * 
 * Adam MacKenzie was the orginal author of this extension. It has, however,
 * been extensively revised and updated by Charles Staelin for NetLogo 7.0, 
 * JDK 17 (as used by NetLogo 7.0), and for the Macintosh platform. 
 */
package org.nlogo.extensions.lpsolver;

import java.io.File;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.PrimitiveManager;
import org.nlogo.extensions.lpsolver.LPLibraryLoader;

public class LPSolverExtension extends org.nlogo.api.DefaultClassManager {
    
    private static org.nlogo.workspace.ExtensionManager em;

    // runs when the extension is loaded to make sure all the needed.
    @Override
    public void runOnce(org.nlogo.api.ExtensionManager em)
            throws org.nlogo.api.ExtensionException {

        // We don't use this, yet.
        LPSolverExtension.em = (org.nlogo.workspace.ExtensionManager) em;

        // find out what operating system and JVM we have.
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        String jvmArchitecture = System.getProperty("os.arch").toLowerCase();
        String arch;

        // As it stands, only 64-bit systems are supported by the underlying
        // shared libraries. Check that this is such a system.  Note: 
        // apparently, the "os.arc" property returns the type of JVM, not
        // the undrerlying operating system. However, that is probably 
        // sufficient for our purposes.
        if (jvmArchitecture.contains("64")) {
            arch = "64";
        } else {
            throw new ExtensionException(
                    "The underlying software on which lpsolver is built "
                    + "requires a 64-bit system and this appears not to "
                    + "be such a system. Please refer to the "
                    + "LPSolver installation instructions  "
                    + "contained in the README.md file.");
        }

        // Check to see that the requied libraries are in the right place. 
        // Java would otherwise throw and error if they were not.
        // Note that the libraries and their proper location are platform
        // specific.
        String userExtensionDirectory
                = org.nlogo.api.FileIO.perUserDir("extensions", false);
        String fileSeparator = System.getProperty("file.separator");
        userExtensionDirectory = userExtensionDirectory + 
                fileSeparator + "lpsolver";

        if (operatingSystem.contains("win")) {
            String subDir = "win" + arch;
            File dllLib1 = new File(userExtensionDirectory + fileSeparator +
                subDir + fileSeparator + "lpsolve55.dll");
            File dllLib2 = new File(userExtensionDirectory + fileSeparator +
                subDir + fileSeparator + "lpsolve55j.dll");
            if (!dllLib1.exists() || !dllLib2.exists()) {
                throw new ExtensionException(
                "The library files lpsolve55.dll and/or lpsolve55j.dll "
                 + "must be present in the lpsolver extension win64 directory: " 
                 + userExtensionDirectory + fileSeparator + "win64"  );
          }
        } else if (operatingSystem.contains("mac")) {
            String subDir = "osx" + arch;
            File DYLib = new File(userExtensionDirectory + fileSeparator +
                subDir + fileSeparator + "liblpsolve55.dylib");
            File JNILib = new File(userExtensionDirectory + fileSeparator +
                subDir + fileSeparator + "liblpsolve55j.jnilib");
            if (!DYLib.exists() || !JNILib.exists()) {
            throw new ExtensionException(
                "The library files liblpsolve55.dylib and/or liblpsolve55j.jnilib "
                 + "must be present in the lpsolver extension directory: " 
                 + userExtensionDirectory + fileSeparator + "osx64" );
          }
        } else if (operatingSystem.contains("linux")) {
            String subDir = "lnx";
            File SOLib1 = new File(userExtensionDirectory + fileSeparator +
                subDir + fileSeparator + "liblpsolve55j.so");
            File SOLib2 = new File(userExtensionDirectory + fileSeparator +
                subDir + fileSeparator + "liblpsolve55j.so");
            if (!SOLib1.exists() || !SOLib2.exists()) {
            throw new ExtensionException(
                "The library files liblpsolve55.so and/or liblpsolve55j.so "
                 + "must be present in the lpsolver extension directory: " 
                 + userExtensionDirectory + fileSeparator + "lnx64" );
          }          
        } else {
            // Don't know this OS.
            throw new ExtensionException("Unknown OS platform.");
        }
    }

    @Override
    public void load(PrimitiveManager primitiveManager) {
        primitiveManager.addPrimitive("max", 
                new LPSetupProblem(LPSetupProblem.MAXIMIZE));
        primitiveManager.addPrimitive("min", 
                new LPSetupProblem(LPSetupProblem.MINIMIZE));
    }

// This is used for debugging only.
    public static void writeToNetLogo(String mssg, Boolean toOutputArea,
            Context context) throws ExtensionException {
        try {
            context.workspace().outputObject(mssg, null, true, true,
                    (toOutputArea)
                            ? org.nlogo.api.OutputDestinationJ.OUTPUT_AREA()
                            : org.nlogo.api.OutputDestinationJ.NORMAL());
        } catch (LogoException e) {
            throw new ExtensionException(e);
        }
    }
}
