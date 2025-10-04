**Version 4.2.0**

This is the source code for the LPSolver extension written for NetLogo, including the java files and LPSolve55 native libraries on which the extension depends. You are free to download them or fork this repository and modify them to your own purposes.  Building the `lpsolver.jar` file from the files in the `src` directory is pretty straightforward with any good Java IDE.  (I used NetBeans.)  Note that the LPSolve55 native libraries are in the `src/lib` directory as the extension bundles them inside of the `lpsolver.jar` file.  

*lp_solve55_5.5.2.11_source.zip* and *lp_solve55_5.5.2.11_java.zip* contain the source code for the LPSolve55 libraries themselves.  They are slightly modified versions of the same files found on SourceForge.  Please see *Steps to build the dynamic libraries.pdf* to see how they have been modified and used to build the native .dll/.so/.dylib/.jnilib files required by the various platforms on which the LPSolver extension runs.

The README.md file for the LPSolver extension itself is here named *LPS-README.md*.

The file *LPSolverExtension-4.2.0.zip* contains just the finished extension, as would be supplied by NetLogo through the Extension Manager.  *lpsolver.jar* is self-contained and is really all that is needed for NetLogo to run the extension as long as it is placed in the NetLogo extensiondirectory appropriate to the platform.