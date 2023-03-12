package legacy.fspn.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;

import legacy.formalism.analysis.MinMaxTimeSeries;
import legacy.formalism.analysis.Solver;
import legacy.formalism.features.Analyzable;
import legacy.fspn.model.FSPN;

public class FSPNSolver extends Solver {
	
	private boolean drop;

	public FSPNSolver(String workingDir, String toolName) {
		super(workingDir,toolName);
		this.drop = true;
	}
	
	public void dropON() {
		this.drop = true;
	}
	
	public void dropOFF() {
		this.drop = false;
	}

	@Override
	public void analyse(Analyzable a) throws Exception {
		AnalysisItem ai = (AnalysisItem) a;
		FSPN net = ai.getNet();
		String fill = ai.toSpecificFormat(null);
		
//		System.out.println(fill);
		Vector<String> filenames = net.getFileToGen();
		for (String fn: filenames) {
			File file = new File(fn);
        	file.delete();
		}

		// Checking
		String fname = super.checkDir(fill);
        // Run of the application
        String command = super.toolName + ' ';
        String dropString = (this.drop) ? " -x >/dev/null" : "";
        command += super.workingDir + fname + dropString;
        // Execution
        Runtime rt = Runtime.getRuntime();
        Process execPID = rt.exec(command);
        execPID.waitFor();
        
        int outcome = execPID.exitValue();
		if (outcome == 0) {
			int mn = net.getMeasureNumber();
			for(int i = 0; i < mn; i++) {
				FSPNMeasure	m = (FSPNMeasure) net.getMeasure(i);
				try{
					double t = 0;
					double min = 0;
					double max = 0;
					MinMaxTimeSeries res = new MinMaxTimeSeries();
					BufferedReader br = new BufferedReader(new FileReader(m.getFileName()));
					String tempString = br.readLine();
					while(tempString != null){
						StringTokenizer st = new StringTokenizer(tempString, "; ");
						t = Double.parseDouble(st.nextToken());
						min = Double.parseDouble(st.nextToken());
						max = Double.parseDouble(st.nextToken());
						res.add(t,min,max);
						tempString = br.readLine();
					}
					br.close();
					m.addResults(res);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}