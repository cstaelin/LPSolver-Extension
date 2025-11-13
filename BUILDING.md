LPSolver 4.2.1

This repository contains the java source code for the LPSolver extension, updated for NetLogo 7.0 and Java 17.  It also contains the lpsolve55 dynamic linear programming libraries upon which the extension depends.  The java source code is contained in the `src` directory, in the `lpsolver` and `lpsolve` sub-directories.  I used the Netbeans IDE to compile the code and build the lpsolver.jar file, but pretty much any IDE should do the trick.  

The java code assumes that the lpsolve55 dynamic libraries are stored within the lpsolver.jar file.  (See the code in LPLibraryLoader.java) Those libraries, .dll, .so, .dylib, and .jnilib files, as appropriate to the target platform, are all located in the `lib` sub-directory and should be included in the build of lpsolver.jar.  (Netbeans does that automatically, given their placement in the `src` directory.)

The java code in the `lpsolver` sub-directory was written by Adam McKenzie and Charles Staelin, specifically for this extension.  The java code in the `lpsolve` sub-directory is adapted from the lpsolve55 package. Attribution and license information is contained in the `README.md`  and `LICENSE.md` files.

The lpsolve55 dynamic libraries are themselves built from the C and C++ code contained in the lpsolve55 package.  That code and instructions for building the libraries is contained in my lpsolve55 repository, https://github.com/cstaelin/lpsolve55.