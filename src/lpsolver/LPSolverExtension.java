/**
 * Version 4.2.0- September  2025
 * 
 * The LPSolverExtension class serves as the front end to the lpsolver55 
 * library of linear programming routines.  Please see the license.md file 
 * that comes with this extension for the licensing of both the lpsolver55
 * package and this extension.
 * 
 * Adam MacKenzie was the original author of this extension. It has, however,
 * been extensively revised and updated by Charles Staelin for NetLogo 7.0, 
 * JDK 17 (as used by NetLogo 7.0), the Macintosh OSX and x86 platforms, and 
 * 32-bit Windows and Linux. . 
 */
package org.nlogo.extensions.lpsolver;

import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.PrimitiveManager;

public class LPSolverExtension extends org.nlogo.api.DefaultClassManager {

    public static String LPSVersion = "4.2.0";
    
    // Runs at the loading of the extension by NetLogo to make sure that
    // the platform is one of those supported.
    @Override
    public void runOnce(org.nlogo.api.ExtensionManager em)
            throws org.nlogo.api.ExtensionException {

        // find out what operating system we have.
        String operatingSystem = System.getProperty("os.name").toLowerCase();

        // Check that this platform is supported and throw an error if it is 
        // not.  LPLibraryLoader.findLibraries() does the heavy lifting here.
        String libDir = LPLibraryLoader.findLibraries(operatingSystem);
        if (libDir.equals("")) {
            // Don't know this OS.
            throw new ExtensionException(operatingSystem + " is apparently an unsupported "
                + "platform.  See the README.md and INSTALATION.md files.");
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
