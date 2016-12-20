package ma.elaissoussi.ygraph;

import java.io.IOException;
import java.util.Map;

import ma.elaissoussi.ygraph.core.ExtensionSearcher;
import ma.elaissoussi.ygraph.graph.DependenciesBuilder;
import ma.elaissoussi.ygraph.util.Property;

public class YGraph {

    public static void main(String[] args) throws IOException {
        final ExtensionSearcher searcher = new ExtensionSearcher();
        final Property prop = Property.getInstance();
        final String hybrisPath = prop.getPropertyByKey("ygraph.source.hybris.path");
        final Map allExtensionDependencies = searcher.getAllExtensionDependencies(hybrisPath);
        final DependenciesBuilder depbuilder = new DependenciesBuilder();
        final String dependecyGraph = depbuilder.buildDependecyGraph(allExtensionDependencies);
        System.out.println(dependecyGraph);

    }
}
