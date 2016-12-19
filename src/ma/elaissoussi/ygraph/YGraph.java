package ma.elaissoussi.ygraph;

import java.util.Map;

import ma.elaissoussi.ygraph.core.ExtensionSearcher;
import ma.elaissoussi.ygraph.graph.DependenciesBuilder;

public class YGraph
{
  public static void main(String[] args)
  {
    final ExtensionSearcher searcher = new ExtensionSearcher();
    final String hybrisPath = "Z:\\source\\hybris";
    final Map allExtensionDependencies = searcher.getAllExtensionDependencies(hybrisPath);
    final DependenciesBuilder depbuilder = new DependenciesBuilder();
    final String dependecyGraph = depbuilder.buildDependecyGraph(allExtensionDependencies);
    System.out.println(dependecyGraph);

  }
}
