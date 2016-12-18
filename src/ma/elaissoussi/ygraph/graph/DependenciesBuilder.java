package ma.elaissoussi.ygraph.graph;

import java.util.List;
import java.util.Map;

// dot -Tpdf hybris-dependency-graph.txt -o hext.pdf

public class DependenciesBuilder {

	public String buildDependecyGraph(Map<String, List<String>> allExtensionDependencies)
	{
		StringBuilder graphBuilder = new StringBuilder(); 
		graphBuilder.append("graph ");
		graphBuilder.append("{");
		for(Map.Entry<String, List<String>> entry : allExtensionDependencies.entrySet())
		{
			List<String> dependencies = entry.getValue();
			if(dependencies != null && dependencies.size() > 0)
			{
				graphBuilder.append(entry.getKey());
				graphBuilder.append(" -- ");
				
				graphBuilder.append("{ ");
				for (String dep : dependencies) 
				{
					graphBuilder.append(dep);
					graphBuilder.append(" ");
				}
				graphBuilder.append("}");
				graphBuilder.append(";");
				graphBuilder.append("\n");
			}
		}
		graphBuilder.append("}");
		
		return graphBuilder.toString() ; 
	}
	
}
