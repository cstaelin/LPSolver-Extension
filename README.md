**LPSolver 4.2.1**

LPSolver uses the lpsolve55 linear programming library to solve linear programs.  It can handle normal, integer and mixed integer problems of any dimension.  The extension is described in the PDF file, *LPSolver Extension-v4.2.pdf*, distributed with the extension.  

The best way to install the extensions is through the NetLogo Extension Manager that is integrated into NetLogo 7.0. Simply place the line<br/>
    `extensions [lpsolver]` <br/>
at the beginning of your NetLogo model, or add `lpsolver` to the extensions already listed. When you press the "Check" button, the Extension Manager will say that the `lpsolver` extension is not installed and will ask if you would like NetLogo to download it for you. Choose "Yes" and NetLogo will download and install the extension in a directory specific to each platform.

This version supports 64- and 32-bit Windows, Macintosh OSX and x86, and 64- and 32-bit Linux platforms.

The LPSolver extension was originally written by Adam MacKenzie and was substantially rewritten by Charles Staelin for NetLogo 7.0 and to include support for additional platforms.  The extension is based on the lpsolve55 linear programming package found at (http://lpsolve.sourceforge.net/5.5/).  The package was released under the GNU Lesser GPL.  Details can be found at 
http://lpsolve.sourceforge.net/5.5/LGPL.htm.  Further licensing details can be found in the **LICENSE.md** file that accompanies this extension.

Questions about this version of the LPSolver extension can be directed to Charles Staelin at the [github issue tracker](https://github.com/cstaelin/LPSolver-Extension/issues) for this extension.  The source code is available on GitHub at https://github.com/cstaelin/LPSolver-Extension, along with instructions for rebuilding it. The source code for the lpsolve55 dynamic libraries, on which the extension is built, are also available on GitHub at https://github.com/cstaelin/lpsolve55, along with instructions for building them.

Note that the lpsolve55 dynamic libraries are intended to be included in the lpsolver.jar file, which is why they are included in the `src` directory.  Most IDE's will do that when building the lpsolver.jar file, given their placement in the `src` directory.