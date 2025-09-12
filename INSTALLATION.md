### INSTALLATION INSTRUCTIONS for LPSolver v4.1.0:

This version of the `lpsolver` extension is compatible with NetLogo 7.0 and requires two library files from the LPSolve55 linear programming package in order to function. Both library files are included with the extension in a format appropriate to your platform. 

**For all platforms:**

The best way to install the extensions is through the NetLogo Extension Manager that is integrated into NetLogo 7.0. Simply place the line<br/>
    `extensions [lpsolver]` <br/>
at the beginning of your NetLogo model, or add `lpsolver` to the extensions already listed. When you press the "Check" button, the Extension Manager will say that the `lpsolver` extension is not installed and will ask if you would like NetLogo to download it for you. Choose "Yes" and NetLogo will download and install both the extension and the necessary libraries in a directory specific to each platform's OS.

For Windows: The extension and its libraries will be installed in a hidden directory <br/>
`AppData\Roaming\NetLogo\7.0\extensions\lpsolver` <br/>
under your home directory.  (In order to look at this directory, you may need to ask File Explorer to show hidden files.)

For the Macintosh: The extension and its libraries will typically be installed in the directory
`Library/Application Support/NetLogo/7.0/extensions/lpsolver` <br/>
under your home directory.

For Linux: The extension and its libraries will be installed in the hidden directory <br/>
`.netlogo/6.1/extensions/lpsolver` <br/>
under your home directory. (In order to look at this directory, you may need to ask the file manager, e.g., Nautilus in Ubuntu, to show system files.) 


**Additional Information**

Among the files in the directory where the lpsolver extension is installed, there is **README.md** file that contains instruction for setting up your linear programming problem and a NetLogo model that shows how the extension is used, `example-lpsolver.nlogox`. 


**Note on licensing:**

The guts of the lpsolver extension is the lpsolve55 linear programming package found at (http://lpsolve.sourceforge.net/5.5/).  The package was released under the GNU Lesser GPL.  Details can be found at 
http://lpsolve.sourceforge.net/5.5/LGPL.htm.  Further licensing details can be found in the **LICENSE.md** file that accompanies this extension.

The original lpsolver extension was written by Adam MacKenzie.  Substantial updates for NetLogo 7.0 and the Macintosh were provided by by Charles Staelin in 2025.  Please visit the [github issue tracker](https://github.com/cstaelin/LPSolver-Extension/issues?state=open) to submit comments, bug reports, or feature requests.


