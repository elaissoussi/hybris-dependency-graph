package ma.elaissoussi.ygraph.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import ma.elaissoussi.ygraph.core.ExtensionSearcher;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class DependenciesViewer extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public DependenciesViewer() 
	{
		 ExtensionSearcher searcher = new ExtensionSearcher();
		 String hybrisPath = "D:\\EL AISSOUSSI\\certification\\Hybris\\1-Hybris\\env\\source\\hybris.5.2.0\\hybris";
		 Map<String, List<String>> allExtensionDependencies = searcher.getAllExtensionDependencies(hybrisPath);
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			for(Map.Entry<String, List<String>> entry : allExtensionDependencies.entrySet())
			{
			  graph.insertVertex(parent, null, entry.getKey(), 0, 0, 100, 30);
			}
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}
}
