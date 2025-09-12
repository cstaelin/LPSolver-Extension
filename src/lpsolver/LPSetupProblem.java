/**
 * this class instantiates and solves a maximization problem passed by the user
 *
 * @author AFMac
 * @version 4.1.0
 * Updated for NetLogo v7.0 by Charles Staelin - September 2025
 */
package org.nlogo.extensions.lpsolver;

import lpsolve.*;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Reporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import org.nlogo.core.LogoList;
import java.util.Iterator;

public class LPSetupProblem implements Reporter {
    
    public static int MAXIMIZE = 0;
    public static int MINIMIZE = 1;
    private int solutionType = MAXIMIZE;
    
    // This consructor determines the type of problem being passed
    public LPSetupProblem(int slnType) {
        solutionType = slnType;
    }

    @Override
    public Syntax getSyntax() { 
        int[] right = new int[]{Syntax.NumberType(),
            Syntax.ListType(), Syntax.ListType(), Syntax.ListType()};
        int ret = Syntax.ListType();
        return SyntaxJ.reporterSyntax(right, ret);
    }

    @Override
    public Object report(Argument args[], Context context)
            throws ExtensionException {   
        
        try {
            //specify vars for the passed parameters
            LogoListBuilder objfn = new LogoListBuilder();
            LogoListBuilder LPparams = new LogoListBuilder();
            LogoListBuilder intfn = new LogoListBuilder();
            LogoListBuilder rtnSolnList = new LogoListBuilder();
            LogoListBuilder rtnShadList = new LogoListBuilder();
            LogoListBuilder rtnFinalList = new LogoListBuilder();
            int i;
            double y;
            
            int numVars = args[0].getIntValue();
            LPparams.add(args[1].get());
            objfn.add(args[2].get());
            intfn.add(args[3].get());

            LogoList LogListLPparams = args[1].getList();
            LogoList LogListObjFn = args[2].getList();
            LogoList LogListIntFn = args[3].getList();
            Iterator<?> it = LogListLPparams.javaIterator();
          
            /* Move through each sub-list of the passed list
             * Individual lists should spell out a particular parameter 
             * of the desired LP 
             * [ [obj func] [constraint 1] [contstraint 2] [...] ]
             */
            
            //generate the LP with number of variables passed by user
            LpSolve solver = LpSolve.makeLp(0, numVars);

            while (it.hasNext()) {  
                LogoList intList = (LogoList) it.next();
                String con = "";
                for (i = 1; i <= numVars; i++) {						 
                    y = ((Double) intList.get(i - 1));
                    con += " " + y;
                }

                solver.strAddConstraint(con, 
                        ((Double) intList.get(i - 1)).intValue(), 
                        ((Double) intList.get(i)));
            }
            //add in objective function
            String obj = "";
            for (i = 1; i <= LogListObjFn.size(); i++) {
                y = ((Double) LogListObjFn.get(i - 1));
                obj += " " + y;
            }
            solver.strSetObjFn(obj);

            //opportunity to set variables as integer
            boolean anyInts = false;
            for (i = 1; i <= LogListIntFn.size(); i++) {
                y = ((Double) LogListIntFn.get(i - 1));
                if (y == 1) {
                    solver.setInt(i, true);
                    anyInts = true;
                } else if (y == 2) {
                    solver.setBinary(i, true);
                } else {
                    solver.setInt(i, false);
                }
            }

            // reduce verbosity, and set the type of problem
            solver.setVerbose(3);
            if (solutionType == MAXIMIZE) {
                solver.setMaxim();
            } else {
                solver.setMinim();
            }

            // If there are integer variables, call preSolve.
            if (anyInts) {
                solver.setPresolve(
                        LpSolve.PRESOLVE_REDUCEGCD | LpSolve.PRESOLVE_SENSDUALS, 
                        solver.getPresolveloops());
            }
            // solve the problem getting the solution  and the shadow prices.
            solver.solve();
            double[] var = solver.getPtrVariables();
            y = solver.getObjective();
            int cntr = solver.getNorigRows() + numVars;
            double [][] sens = new double [cntr][cntr];
            sens = solver.getPtrSensitivityRhs();
            solver.deleteLp();
            
            // Now report the results as a list of llists
            // First the solution.
            for (i = 0; i < var.length; i++) {
                rtnSolnList.add(var[i]);
            }
            LogoList solutionList = rtnSolnList.toLogoList();
            // Then the shadow prices.
            for (i = 0; i < (cntr - numVars); i++) {
                rtnShadList.add(sens[0][i]);
            }
            LogoList shadowList = rtnShadList.toLogoList();
            rtnFinalList.add(y);
            rtnFinalList.add(solutionList);
            rtnFinalList.add(shadowList);
            
            return rtnFinalList.toLogoList();

        } catch (LogoException e) {
            throw new ExtensionException(e.getMessage());
        } catch (LpSolveException e) {
            throw new ExtensionException(e.toString());
        }
    }
}
